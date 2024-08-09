package com.dbdemo;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayQuiz extends JFrame implements ActionListener {
    JPanel mainPanel, startPanel;
    JLabel timerLabel, startLabel, questionLabel;
    JButton start;
    Color customColor;
    Timer timer;
    int remaining, queCounter = 1;

    public static void main(String[] args) {
        new PlayQuiz();
    }

    public PlayQuiz() {
        super("Play Quiz");
        setSize(850, 650);

        String image = "D:\\Sem2 Project\\qems\\index bgg.png";
        mainPanel = bgPanel(image);
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        startPanel = new JPanel();
        customColor = new Color(64, 84, 228);
        startPanel.setBackground(customColor);
        startPanel.setLayout(null);
        startPanel.setBounds(300, 250, 180, 80);

        startLabel = new JLabel("Start Quiz?");
        startLabel.setFont(new Font("Arial", Font.BOLD, 12));
        startLabel.setForeground(Color.black);
        startLabel.setBounds(10, 5, 90, 20);
        start = new JButton("START");
        start.setBackground(Color.green);
        start.setBounds(50, 35, 75, 25);

        startPanel.add(startLabel);
        startPanel.add(start);
        mainPanel.add(startPanel);

        timerLabel = new JLabel("Time remaining: 05:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setBounds(570, 20, 250, 50);
        timerLabel.setForeground(Color.white);
        mainPanel.add(timerLabel);

        questionLabel = new JLabel("QUESTION:  "+queCounter);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setBounds(30, 20, 250, 50);
        questionLabel.setForeground(Color.white);
        mainPanel.add(questionLabel);

        start.addActionListener(this);
        start.setActionCommand("start");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
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

    private void startTimer() {
        remaining = 5 * 60;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (remaining > 0) {
                    remaining--;
                    updateTimer();
                } else {
                    timer.cancel();
                    endQuiz();
                }
            }

        }, 0, 1000);
    }

    private void updateTimer() {
        int min = remaining / 60;
        int sec = remaining % 60;
        timerLabel.setText(String.format("Time remaining: %02d:%02d", min, sec));
    }

    private void endQuiz() {
        JOptionPane.showMessageDialog(this, "Time is up! The quiz has ended.", "Quiz Ended",
                JOptionPane.INFORMATION_MESSAGE);
        // Add logic here to handle the end of the quiz
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
            startPanel.setVisible(false);
            startTimer();
        }
    }
}
