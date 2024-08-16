package com.dbdemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserDashboard extends JFrame implements ActionListener {
    static JPanel mainPanel, headPanel, leftPanel;
    static JLabel headLabel;
    static JButton left1, left2, left3, play;
    static JTextArea instructions;
    DefaultTableModel model;
    static JScrollPane scrollPane;
    Color customColor;
    int userId;
    String username;

    /*
     * public static void main(String[] args) {
     * new UserDashboard();
     * }
     */

    public UserDashboard(int userId, String username) {
        this.userId = userId;
        this.username = username;
        initUI();
    }

    public UserDashboard() {
    }

    private void initUI() {
        setName("User Dashboard");
        setSize(850, 650);

        String image = "D:\\Project#2\\Quiz Platform\\qems\\index bgg.png";
        mainPanel = bgPanel(image);
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        headPanel = new JPanel();
        customColor = new Color(32, 51, 234);
        headPanel.setBackground(customColor);
        headPanel.setBounds(0, 0, 850, 70);
        headPanel.setLayout(new BorderLayout());

        headLabel = new JLabel("USER DASHBOARD", SwingConstants.CENTER);
        headLabel.setForeground(Color.white);
        headLabel.setFont(new Font("Serif", Font.BOLD, 34));
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel = new JPanel();
        customColor = new Color(64, 84, 228);
        leftPanel.setBackground(customColor);
        leftPanel.setBounds(0, 70, 160, 600);
        leftPanel.setLayout(null);

        left1 = highlightedButtons("Play Quiz");
        left2 = highlightedButtons("Leaderboard");
        left3 = highlightedButtons("Log Out");

        left1.setBounds(12, 30, 130, 30);
        left2.setBounds(12, 100, 130, 30);
        left3.setBounds(12, 170, 130, 30);

        headPanel.add(headLabel, BorderLayout.CENTER);
        mainPanel.add(headPanel, BorderLayout.NORTH);

        leftPanel.add(left1);
        leftPanel.add(left2);
        leftPanel.add(left3);
        mainPanel.add(leftPanel);

        instructions = new JTextArea("Welcome to the quiz!\n\n"
                + "Please read the following instructions carefully:\n\n"
                + "1. There are 10 questions in the Quiz.\n"
                + "2. Each question marks one point.\n"
                + "3. You have a limited time to complete the quiz.\n"
                + "4. Click 'Next' to proceed to the next question.\n"
                + "5. You cannot go back to previous questions.\n"
                + "6. Good luck!");

        instructions.setWrapStyleWord(true);
        instructions.setLineWrap(true);
        instructions.setOpaque(false);
        instructions.setEditable(false);
        instructions.setFocusable(false);
        instructions.setForeground(Color.white);
        instructions.setFont(new Font("Serif", Font.PLAIN, 20));
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructions.setBounds(200, 100, 600, 400);

        mainPanel.add(instructions);
        instructions.setVisible(false);

        play = new JButton("PLAY");
        play.setFont(new Font("Arial", Font.BOLD, 20));
        play.setBackground(Color.green);
        play.setForeground(Color.black);
        play.setBorderPainted(false);
        play.setFocusPainted(false);
        play.setFocusable(false);
        play.setBounds(600, 500, 120, 30);

        play.setVisible(false);
        mainPanel.add(play);

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

        mainPanel.add(table);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(250, 120, 500, 400);
        mainPanel.add(scrollPane);
        scrollPane.setVisible(false);

        left1.setActionCommand("left1");
        left1.addActionListener(this);
        left2.setActionCommand("left2");
        left2.addActionListener(this);
        left3.setActionCommand("left3");
        left3.addActionListener(this);
        play.setActionCommand("play");
        play.addActionListener(this);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static JButton highlightedButtons(String text) {
        JButton left = new JButton(text);
        Color normalColor = new Color(64, 84, 228);
        Color hoverColor = new Color(0, 153, 255);
        Color pressedColor = new Color(0, 102, 204);

        left.setFont(new Font("Arial", Font.BOLD, 16));
        left.setForeground(Color.WHITE);
        left.setBackground(normalColor); // Highlight color
        left.setBorderPainted(false);
        left.setFocusPainted(false);
        left.setFocusable(false);

        left.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                left.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                left.setBackground(normalColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                left.setBackground(pressedColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                left.setBackground(hoverColor);
            }
        });

        return left;
    }

    private static JPanel bgPanel(String fp) {
        return new JPanel() {
            private Image bgImage;

            {
                try {
                    bgImage = ImageIO.read(new File(fp));
                } catch (IOException e) {
                }
            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("left1")) {
            instructions.setVisible(true);
            play.setVisible(true);
            scrollPane.setVisible(false);
        }

        else if (e.getActionCommand().equals("left2")) {
            instructions.setVisible(false);
            play.setVisible(false);
            scrollPane.setVisible(true);
        }

        else if (e.getActionCommand().equals("play")) {
            new PlayQuiz(userId, username);
        }

        else if (e.getActionCommand().equals("left3")) {
            this.dispose();
            new UserLogin();
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
