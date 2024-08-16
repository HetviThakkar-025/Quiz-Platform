package com.dbdemo;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Leaderboard extends JFrame {
    DefaultTableModel model;
    JScrollPane scrollPane;

    public static void main(String[] args) {
        new Leaderboard();
    }

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
        scrollPane.setBounds(100, 50, 650, 500);
        bgLabel.add(scrollPane);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
