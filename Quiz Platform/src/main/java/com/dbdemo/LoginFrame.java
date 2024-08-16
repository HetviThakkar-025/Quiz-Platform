package com.dbdemo;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {
    JTextField tf1;
    JPasswordField ps;
    JLabel l1, l2, errorMsg;
    JButton b, b2;

    private static final String username = "admin";
    private static final String password = "12345678";

    public LoginFrame() {
        super("Admin Login");
        setSize(850, 650);
        String image = "D:\\Project#2\\Quiz Platform\\qems\\loggg.png";

        JLabel bgLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File(image)));
            bgLabel.setIcon(icon);
        } catch (IOException e) {
        }

        // Set the layout to null for absolute positioning
        bgLabel.setLayout(null);
        add(bgLabel);
        l1 = new JLabel("USERNAME");
        l1.setForeground(Color.WHITE);
        l1.setBounds(100, 110, 100, 50);
        tf1 = new JTextField(20);
        tf1.setBounds(100, 160, 200, 25);
        tf1.setFont(new Font("Arial", Font.BOLD, 16));
        l2 = new JLabel("PASSWORD");
        l2.setForeground(Color.WHITE);
        l2.setBounds(100, 210, 100, 50);
        ps = new JPasswordField(20);
        ps.setEchoChar('*');
        ps.setBounds(100, 260, 200, 25);

        b = new JButton("Login");
        b.setBounds(150, 330, 70, 35);
        b.setBackground(Color.orange);
        b.setFocusable(false);
        b.setFocusPainted(false);
        b.setBorderPainted(false);

        b2 = new JButton();
        ImageIcon path = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\Back.png");
        b2.setIcon(path);
        b2.setBounds(25, 550, 60, 30);
        b2.setBackground(Color.lightGray);
        b2.setFocusable(false);
        b2.setFocusPainted(false);
        b2.setBorderPainted(false);

        errorMsg = new JLabel();
        errorMsg.setForeground(Color.RED);
        errorMsg.setBounds(80, 390, 300, 25);
        errorMsg.setFont(new Font("Arial", Font.PLAIN, 14));

        bgLabel.add(l1);
        bgLabel.add(tf1);
        bgLabel.add(l2);
        bgLabel.add(ps);
        bgLabel.add(b);
        bgLabel.add(b2);
        bgLabel.add(errorMsg);

        b.setActionCommand("b");
        b2.setActionCommand("b2");
        b.addActionListener(this);
        b2.addActionListener(this);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("b")) {

            String pass = new String(ps.getPassword());
            if (tf1.getText().equals("admin") && pass.equals("12345678")) {
                new AdminDashBoard();

            } else {
                errorMsg.setText("Invalid username or password. Enter again");
                tf1.setText("");
                ps.setText("");
            }
        } else {
            this.dispose();
            try {
                Main.main(null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
