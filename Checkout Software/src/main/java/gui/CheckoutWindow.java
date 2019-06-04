package gui;

import model.Product;
import services.CasaDeMarcat;
import services.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CheckoutWindow extends JFrame {

    Color red = new Color(255, 0, 0);
    Color  green   = new Color(0, 255,  0);
    Color  dark   = new Color(95, 94, 87);

    private CasaDeMarcat CASA = new CasaDeMarcat();
    private float TOTAL;

    private JPanel p1 = new JPanel(new BorderLayout());
    private JPanel p2 = new JPanel(/*new FlowLayout(FlowLayout.LEFT)*/);
    private JPanel p3 = new JPanel();

    private JLabel e1 = new JLabel("ID :");
    private JLabel multiplier_label = new JLabel("x");

    private JTextField t1 = new JTextField(3);
    private JTextField multiplier_text = new JTextField(2);

    private JButton button_scan = new JButton("SCAN");
    private JButton button_remove = new JButton("REMOVE");
    private JButton button_total = new JButton("TOTAL");
    private JButton button_complete = new JButton("COMPLETE");

    private DefaultListModel<Product> model = new DefaultListModel<Product>();
    private JList<Product> list = new JList<>(model);

    public CheckoutWindow(CasaDeMarcat CASA) {

        super("CHECKOUT"); //TITLU

        //CASA.init();

        this.CASA = CASA;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null); //ALINIERE CENTRATA

        button_scan.setBackground(green);
        button_remove.setBackground(red);
        button_total.setBackground(dark);
        button_total.setForeground(Color.white);
        button_complete.setBackground(dark);
        button_complete.setForeground(Color.white);
        list.setBackground(dark);
        list.setForeground(Color.white);

        add(p1); //PE CENTRU
        add(p2, BorderLayout.SOUTH); //JOS
        add(p3, BorderLayout.NORTH);


        p2.add(e1);
        p2.add(t1);
        p2.add(multiplier_label);
        p2.add(multiplier_text);
        p2.add(button_scan);
        p2.add(button_remove);

        p3.add(button_total);
        p3.add(button_complete);

        p1.add(list);

        button_scan.addActionListener(ev -> adaugaProdus());
        button_remove.addActionListener(ev -> removeProduct());
        button_total.addActionListener(ev -> total());
        button_complete.addActionListener(ev -> complete());
        /*b2.addActionListener(ev -> uploadDatabase());
        p1.add(list);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Product p = list.getSelectedValue();
                    if (p != null) {
                        //deleteProdus(p);
                    }
                }
            }
        });*/


        afisareProduse();

        setVisible(true);
    }

    private void adaugaProdus() {
        String info = t1.getText(); //ID-ul
        String m = multiplier_text.getText();
        int multiplier;
        int curr_ID = Integer.parseInt(info);
        if (m.equals("")) multiplier = 1;
        else multiplier = Integer.parseInt(m);
        //produsService.adaugaProdus(nume, pret);
        t1.setText(null);
        multiplier_text.setText(null);

        for (int i=0; i<multiplier; i++) {
            CASA.scanProductById(curr_ID);
        }

        afisareProduse();
        //JOptionPane.showMessageDialog(this,"Ai adaugat un produs!");
    }

    private void removeProduct() {
        String info = t1.getText(); //ID-ul
        String m = multiplier_text.getText();
        int curr_ID = Integer.parseInt(info);
        int multiplier;
        if (m.equals("")) multiplier = 1;
        else multiplier = Integer.parseInt(m);

        t1.setText(null);
        multiplier_text.setText(null);

        if (multiplier <= CASA.getQuantity(curr_ID)) {
            for (int i=0; i<multiplier; i++) {
                CASA.removeProductById(curr_ID);
            }
        }
        else {
            /*
            for (int i=0; i<CASA.getQuantity(curr_ID); i++) {
                CASA.removeProductById(curr_ID);
            }*/
            JOptionPane.showMessageDialog(this, "EROARE!\nDoriti sa stergeti prea multe produse.");
        }

        afisareProduse();
    }

    private void afisareProduse() {
        model.clear();
        List<Product> produse = CASA.showCart();
        produse.forEach(p -> model.addElement(p));
    }

    private void total() {
        float total = CASA.totalPrice();
        this.TOTAL = total;
        button_total.setText(String.valueOf(total));

        new PayWindow(TOTAL);
    }

    private void complete() {
        CASA.newClient();
        afisareProduse();
        button_total.setText("TOTAL");
        CASA.writeLog("ORDER_FIN", null);
    }

    public void setDatabase(CasaDeMarcat CASA) {
        this.CASA = CASA;
    }

}
