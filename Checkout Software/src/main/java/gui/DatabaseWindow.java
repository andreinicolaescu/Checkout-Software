package gui;


import model.Product;
import services.CasaDeMarcat;
import services.Database;
import services.ProdusService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DatabaseWindow extends JFrame {

    private ProdusService produsService = new ProdusService();
    private CasaDeMarcat CASA = new CasaDeMarcat();

    Color red = new Color(255, 0, 0);
    Color  green   = new Color(0, 255,  0);
    Color  dark   = new Color(95, 94, 87);

    private JPanel p1 = new JPanel(new BorderLayout());
    private JPanel p2 = new JPanel(/*new FlowLayout(FlowLayout.LEFT)*/);

    //private JLabel e1 = new JLabel("NUME:");
    //private JLabel e2 = new JLabel("PRET:");

    //private JTextField t1 = new JTextField(10);
    //private JTextField t2 = new JTextField(10);

    private JButton b1 = new JButton("UPDATE");
    private JButton b2 = new JButton("UPLOAD");

    private DefaultListModel<Product> model = new DefaultListModel<Product>();
    private JList<Product> list = new JList<>(model);

    public DatabaseWindow(CasaDeMarcat CASA) {

        super("PRODUSE"); //TITLU

        //CASA.init();
        //CASA.showDatabase();
        //CASA.newClient();

        this.CASA = CASA;

        produsService.setDatabase(CASA.getDatabase());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null); //ALINIERE CENTRATA

        list.setBackground(dark);
        list.setForeground(Color.white);

        b1.setBackground(dark);
        b1.setForeground(Color.white);
        b2.setBackground(dark);
        b2.setForeground(Color.white);

        add(p1); //PE CENTRU
        add(p2, BorderLayout.SOUTH); //JOS

        //p2.add(e1);
        //p2.add(t1);
        //p2.add(e2);
        //p2.add(t2);
        p2.add(b1);
        p2.add(b2);

        b1.addActionListener(ev -> updateLocal());
        b2.addActionListener(ev -> uploadDatabase());
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
        });

        afisareProduse();

        setVisible(true);
    }

    /*
    private void adaugaProdus() {
        String nume = t1.getText();
        double pret = Double.parseDouble(t2.getText());
        //produsService.adaugaProdus(nume, pret);
        t1.setText(null);
        t2.setText(null);
        afisareProduse();
        JOptionPane.showMessageDialog(this,"Ai adaugat un produs!");
    }*/

    private void uploadDatabase() {

        produsService.clearDatabase();

        List<Product> produse = CASA.showDatabase();
        produse.stream().forEach(x -> produsService.adaugaProdus(x));

        JOptionPane.showMessageDialog(this,"SQL database updated!");

        CASA.writeLog("SQL_DATABASE_UPDATE", null);
    }

    private void afisareProduse() {
        model.clear();
        List<Product> produse = CASA.showDatabase();
        produse.forEach(p -> model.addElement(p));
    }

    /*
    private void deleteProdus(Product p) {
        produsService.delete(p);
        afisareProduse();
    }*/

    private void updateLocal() {
        CASA.updateLocal();
        afisareProduse();

        JOptionPane.showMessageDialog(this,"Updated locally!");
        CASA.writeLog("LOCAL_DATABASE_UPDATE", null);
    }

    public void setDatabase(CasaDeMarcat CASA) {
        this.CASA = CASA;
    }

}