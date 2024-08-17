package com.dbdemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Leaderboard extends JFrame implements ActionListener {
    DefaultTableModel model;
    JScrollPane scrollPane;
    JButton back;

    /*public static void main(String[] args) {
        new Leaderboard();
    }*/

    public Leaderboard() {
        super("LeaderBoard");
        setSize(850, 650);

        String s1 = "D:\\Project#2\\Quiz Platform\\qems\\index bgg.png";

        JLabel bgLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File(s1)));
            bgLabel.setIcon(icon);
        } catch (IOException e) {
        }

        bgLabel.setLayout(null);
        add(bgLabel);

        String[] columnNames = { "Username", "Score" };
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setBackground(Color.LIGHT_GRAY);
        table.setOpaque(true);
        table.setForeground(Color.DARK_GRAY);
        table.setGridColor(Color.black);
        table.setSelectionBackground(Color.YELLOW);
        table.setSelectionForeground(Color.RED);

        // Set table header properties
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.darkGray);
        header.setForeground(Color.WHITE);

        try {
            ResultSet rs = DatabaseConnection.getScore();
            while (rs.next()) {

                String username = rs.getString("username");
                int rank = rs.getInt("score");

                model.addRow(new Object[] { username, rank });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching score from database: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        bgLabel.add(table);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 50, 650, 480);
        bgLabel.add(scrollPane);

        back = new JButton();
        ImageIcon path = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\Back.png");
        back.setIcon(path);
        back.setBounds(25, 550, 60, 30);
        back.setBackground(Color.lightGray);
        back.setFocusable(false);
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        bgLabel.add(back);

        back.addActionListener(this);
        back.setActionCommand("back");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new AdminDashBoard();
    }
}
