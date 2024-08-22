package com.dbdemo;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {
    JTextField tf1;
    JPasswordField ps;
    JLabel l1, l2, errorMsg;
    JButton b, b2;

    public static void main(String[] args) {
        new LoginFrame();
    }

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

        l1=createLabel("USERNAME", 110);

        tf1 = new JTextField(20);
        tf1.setBounds(100, 160, 200, 25);
        tf1.setFont(new Font("Arial", Font.BOLD, 16));

        l2=createLabel("PASSWORD", 210);
        
        ps = new JPasswordField(20);
        ps.setFont(new Font("Arial", Font.BOLD, 16));
        ps.setBounds(100, 260, 200, 25);

        JCheckBox showPass = new JCheckBox("Show password");
        showPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPass.isSelected()) {
                    ps.setEchoChar((char) 0);
                } else {
                    ps.setEchoChar('•');
                }

            }
        });
        showPass.setFont(new Font("Arial", Font.BOLD, 15));
        showPass.setBounds(110, 310, 153, 21);
        showPass.setForeground(Color.white);
        showPass.setOpaque(false);
        showPass.setBorderPainted(false);
        showPass.setFocusPainted(false);
        bgLabel.add(showPass);

        b=createButton(150, 350, 70, 35, "Login");
        b.setBackground(Color.orange);

        b2 = new JButton();
        ImageIcon path = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\Back.png");
        b2.setIcon(path);
        b2.setBackground(Color.lightGray);
        b2.setBounds(25, 550, 60, 30);
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

    private JLabel createLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(100, y, 100, 50);

        return label;
    }

    private JButton createButton(int x, int y, int w, int h, String text){
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("b")) {

            String pass = new String(ps.getPassword());
            if (tf1.getText().equals("admin") && pass.equals("12345678")) {
                new AdminDashBoard();
                this.dispose();

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
