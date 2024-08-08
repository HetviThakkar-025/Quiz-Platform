package com.dbdemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserDashboard extends JFrame {
    JPanel mainPanel, headPanel, leftPanel;
    JLabel headLabel;
    JButton left1, left2, left3;
    Color customColor;

    public static void main(String[] args) {
        new UserDashboard();
    }

    public UserDashboard() {
        super("User Dashboard");
        setSize(850, 650);

        String image = "D:\\Sem2 Project\\qems\\index bgg.png";
        mainPanel = bgPanel(image);
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        headPanel = new JPanel();
        customColor = new Color(32, 51, 234);
        headPanel.setBackground(customColor);
        headPanel.setPreferredSize(new Dimension(850, 70));
        headPanel.setLayout(new BorderLayout());

        headLabel = new JLabel("USER DASHBOARD", SwingConstants.CENTER);
        headLabel.setForeground(Color.white);
        headLabel.setFont(new Font("Serif", Font.BOLD, 34));
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel = new JPanel();
        customColor = new Color(64, 84, 228);
        leftPanel.setBackground(customColor);
        leftPanel.setPreferredSize(new Dimension(160, 500));
        leftPanel.setLayout(null);

        left1 = highlightedButtons("Categories");
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
        mainPanel.add(leftPanel, BorderLayout.WEST);
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
}
