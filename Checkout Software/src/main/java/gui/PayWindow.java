package gui;

import javax.swing.*;
import java.awt.*;

public class PayWindow extends JFrame {

    private JPanel p1 = new JPanel(new BorderLayout());
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JLabel amount_label = new JLabel("AMOUNT");
    private JTextField amount_text = new JTextField(8);
    private JButton pay = new JButton("PAY");

    Color green = Color.green;
    Color  dark   = new Color(95, 94, 87);

    private float TOTAL;

    public PayWindow(float total_price) {

        super("PAY");
        setSize(250, 120);
        setLocationRelativeTo(null);

        this.TOTAL = total_price;

        pay.setBackground(green);
        p2.setBackground(dark);
        p3.setBackground(dark);
        amount_label.setForeground(Color.white);

        add(p1);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.SOUTH);
        p2.add(amount_label);
        p2.add(amount_text);
        p3.add(pay);

        pay.addActionListener(ev -> pay());

        setVisible(true);

    }

    private void pay() {
        String info = amount_text.getText();
        float amount = Float.parseFloat(info);

        if (amount > TOTAL) {
            float rem = amount - TOTAL;
            JOptionPane.showMessageDialog(this, "Payment complete!\nRemainder is of " + rem +" LEI.");
            this.dispose();
        }
        else if (amount == TOTAL) {
            JOptionPane.showMessageDialog(this, "Payment complete!");
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Not enough money!");
        }

    }

}
