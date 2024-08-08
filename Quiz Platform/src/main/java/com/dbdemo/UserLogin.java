package com.dbdemo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserLogin extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new UserLogin();
    }

    JTextField tf1;
    JPasswordField ps;
    JLabel l1, l2, errorMsg;
    JButton b, b2, b3;

    public UserLogin() {
        super("User Login");
        setSize(850, 650);
        String image = "D:\\Sem2 Project\\qems\\loggg.png";

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
        ps.setFont(new Font("Arial", Font.BOLD, 14));
        ps.setEchoChar('*');
        ps.setBounds(100, 260, 200, 25);

        b = new JButton("Sign in");
        b.setBounds(100, 330, 90, 35);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(Color.orange);

        b3 = new JButton("Sign up");
        b3.setBounds(210, 330, 90, 35);
        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBackground(Color.orange);

        b2 = new JButton();
        ImageIcon path = new ImageIcon("D:\\Sem2 Project\\qems\\Back.png");
        b2.setIcon(path);
        b2.setBounds(25, 550, 60, 30);
        b2.setBackground(Color.lightGray);

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
        bgLabel.add(b3);
        bgLabel.add(errorMsg);

        b.setActionCommand("signin");
        b2.setActionCommand("back");
        b3.setActionCommand("signup");
        b.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("signin")) {
            String uname = tf1.getText();
            String pass = new String(ps.getPassword());

            if (uname.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ResultSet rs = DatabaseConnection.getUsers();

                    while (rs.next()) {
                        String name = rs.getString("username");
                        String pas = rs.getString("password");

                        if (uname.equals(name) && pass.equals(pas)) {
                            new UserDashboard();
                        } else {
                            errorMsg.setText("Invalid username or password. Enter again");
                            tf1.setText("");
                            ps.setText("");
                        }
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Error checking user in database", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("signup")) {
            String uname = tf1.getText();
            String pass = new String(ps.getPassword());

            if (uname.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Connection con = DatabaseConnection.getConnection();
                    String query = "insert into userdetails(username,password) values(?,?)";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, uname);
                    pst.setString(2, pass);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(this, "User added successfully");
                    tf1.setText("");
                    ps.setText("");
                    new UserDashboard();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Error adding user to database", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("back")) {
            this.dispose();
            try {
                Main.main(null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
