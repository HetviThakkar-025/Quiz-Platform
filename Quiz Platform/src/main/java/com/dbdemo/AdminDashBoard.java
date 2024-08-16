package com.dbdemo;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AdminDashBoard extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5;
    DefaultTableModel model;

    public static void main(String[] args) {
        new AdminDashBoard();
    }

    public AdminDashBoard() {
        super("Admin DashBoard");
        setSize(850, 650);
        String s1 = "D:\\Project#2\\Quiz Platform\\qems\\adDb.png";
        String s2 = "D:\\Project#2\\Quiz Platform\\qems\\add new question.png";
        String s3 = "D:\\Project#2\\Quiz Platform\\qems\\all questions.png";
        String s4 = "D:\\Project#2\\Quiz Platform\\qems\\all user result.png";
        String s5 = "D:\\Project#2\\Quiz Platform\\qems\\Logout.png";

        JLabel bgLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File(s1)));
            bgLabel.setIcon(icon);
        } catch (IOException e) {
        }

        bgLabel.setLayout(null);
        add(bgLabel);

        b1 = new JButton();
        ImageIcon path = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\Back.png");
        b1.setIcon(path);
        b1.setBounds(25, 550, 60, 30);
        b1.setBackground(Color.lightGray);
        b1.setFocusable(false);
        b1.setFocusPainted(false);
        b1.setBorderPainted(false);

        b2 = createImage(s2, "Add New Question", 65, 150);
        b3 = createImage(s3, "      All Questions", 65, 240);
        b4 = createImage(s4, "  All User Results", 65, 340);
        b5 = createImage(s5, "           Log Out", 65, 440);

        bgLabel.add(b1);
        bgLabel.add(b2);
        bgLabel.add(b3);
        bgLabel.add(b4);
        bgLabel.add(b5);

        b1.addActionListener(this);
        b1.setActionCommand("b1");
        b2.addActionListener(this);
        b2.setActionCommand("b2");
        b3.addActionListener(this);
        b3.setActionCommand("b3");
        b4.addActionListener(this);
        b4.setActionCommand("b4");
        b5.addActionListener(this);
        b5.setActionCommand("b5");

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("b1")) {
            this.dispose();
            new LoginFrame();
        } else if (e.getActionCommand().equals("b2")) {
            new NewQuestions();
        } else if (e.getActionCommand().equals("b3")) {
            new QuestionService();
        } else if (e.getActionCommand().equals("b4")) {
            new Leaderboard();
        } else if (e.getActionCommand().equals("b5")) {
            this.dispose();
            try {
                Main.main(null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private static JButton createImage(String path, String text, int x, int y) {
        JButton b = new JButton(text);

        try {
            ImageIcon icon = new ImageIcon(path);
            b.setIcon(icon);
        } catch (Exception e) {
        }

        b.setBounds(x, y, 300, 60);
        b.setBackground(Color.cyan);
        b.setFocusable(false);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setFont(new Font("Arial", Font.BOLD, 16));

        return b;
    }
}
