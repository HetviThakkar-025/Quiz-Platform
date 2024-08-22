package com.dbdemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewQuestions extends JFrame implements ActionListener {
    JButton b1, b2, b3;
    JLabel l1, l2, l3, l4, l5, l6, msg;
    JTextField t1, t2, t3, t4, t5;
    JTextArea ta;

    public static void main(String[] args) {
        new NewQuestions();
    }

    public NewQuestions() {
        super("Add New Questions");
        setSize(850, 650);
        String image = "D:\\Project#2\\Quiz Platform\\qems\\index bgg.png";

        JLabel bgLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File(image)));
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

        l1 = createLabel("QUESTION", 50, 20);

        ta = new JTextArea(8, 500);
        ta.setFont(new Font("Arial", Font.BOLD, 16));
        ta.setBounds(50, 85, 650, 55);
        ta.setBorder(BorderFactory.createLineBorder(Color.black, 4));

        l2 = createLabel("OPTION 1", 90, 140);
        t1 = createTextField(90, 205);

        l3 = createLabel("OPTION 2", 410, 140);
        t2 = createTextField(410, 205);

        l4 = createLabel("OPTION 3", 90, 240);
        t3 = createTextField(90, 305);

        l5 = createLabel("OPTION 4", 410, 240);
        t4 = createTextField(410, 305);

        l6 = createLabel("ANSWER", 320, 330);
        
        t5 = new JTextField(20);
        t5.setFont(new Font("Arial", Font.BOLD, 16));
        t5.setBounds(260, 395, 200, 25);
        t5.setBorder(BorderFactory.createLineBorder(Color.blue, 4));

        b2 = createButton("ADD", 240);
        b3 = createButton("RESET", 390);

        msg = new JLabel();
        msg.setForeground(Color.RED);
        msg.setBounds(290, 500, 200, 90);

        bgLabel.add(b1);
        bgLabel.add(l1);
        bgLabel.add(ta);
        bgLabel.add(l2);
        bgLabel.add(t1);
        bgLabel.add(l3);
        bgLabel.add(t2);
        bgLabel.add(l4);
        bgLabel.add(t3);
        bgLabel.add(l5);
        bgLabel.add(t4);
        bgLabel.add(l6);
        bgLabel.add(t5);
        bgLabel.add(b2);
        bgLabel.add(b3);
        bgLabel.add(msg);

        b1.addActionListener(this);
        b1.setActionCommand("b1");
        b2.addActionListener(this);
        b2.setActionCommand("b2");
        b3.addActionListener(this);
        b3.setActionCommand("b3");

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text, int x) {
        JButton button = new JButton(text);
        button.setBounds(x, 470, 90, 30);
        button.setBackground(Color.green);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        return button;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 100, 90);

        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField tf = new JTextField(20);
        tf.setFont(new Font("Arial", Font.BOLD, 16));
        tf.setBounds(x, y, 200, 25);
        tf.setBorder(BorderFactory.createLineBorder(Color.red, 4));

        return tf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("b1")) {
            this.dispose();
            new AdminDashBoard();
        } else if (e.getActionCommand().equals("b2")) {
            String q = ta.getText();
            String o1 = t1.getText();
            String o2 = t2.getText();
            String o3 = t3.getText();
            String o4 = t4.getText();
            String ans = t5.getText();

            if (q.isEmpty() || o1.isEmpty() || o2.isEmpty() || o3.isEmpty() || o4.isEmpty() || ans.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Connection con = DatabaseConnection.getConnection();
                    String query = "insert into questions(quesText,option1,option2,option3,option4,answer) values (?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, q);
                    ps.setString(2, o1);
                    ps.setString(3, o2);
                    ps.setString(4, o3);
                    ps.setString(5, o4);
                    ps.setString(6, ans);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Question added successfully");
                    ta.setText("");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(this, "Error adding question to database", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("b3")) {
            ta.setText("");
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            JOptionPane.showMessageDialog(this, "Fields have been reset");
        }
    }
}
