package com.dbdemo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.plaf.metal.MetalIconFactory;

public class PlayQuiz extends JFrame implements ActionListener {
    JPanel mainPanel, startPanel, questionPanel, o1Panel, o2Panel, o3Panel, o4Panel;
    JLabel timerLabel, startLabel, questionLabel;
    JRadioButton o1, o2, o3, o4;
    JButton next;
    JTextArea questionText;
    ButtonGroup optionsGroup;
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

        String image = "D:\\Project#2\\Quiz Platform\\qems\\index bgg.png";
        mainPanel = bgPanel(image);
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        startPanel = new JPanel();
        customColor = new Color(0, 122, 255);
        startPanel.setBackground(customColor);
        startPanel.setLayout(null);
        startPanel.setBounds(310, 250, 200, 100);

        startLabel = new JLabel("Start Quiz?");
        startLabel.setFont(new Font("Arial", Font.BOLD, 16));
        startLabel.setForeground(Color.black);
        startLabel.setBounds(10, 8, 90, 20);
        start = new JButton("START");
        start.setBackground(Color.green);
        start.setFocusable(false);
        start.setBounds(57, 47, 80, 30);

        startPanel.add(startLabel);
        startPanel.add(start);
        mainPanel.add(startPanel);

        timerLabel = new JLabel("Time remaining: 05:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setBounds(570, 20, 250, 50);
        timerLabel.setForeground(Color.white);
        mainPanel.add(timerLabel);

        questionLabel = new JLabel("QUESTION:  " + queCounter);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setBounds(30, 20, 250, 50);
        questionLabel.setForeground(Color.white);
        mainPanel.add(questionLabel);

        questionPanel = customOptionPanel(80, 100, 650, 90);
        questionPanel.setLayout(null);

        questionText = new JTextArea();
        questionText.setWrapStyleWord(true);
        questionText.setLineWrap(true);
        questionText.setOpaque(false);
        questionText.setEditable(false);
        questionText.setFocusable(false);
        questionText.setForeground(Color.black);
        questionText.setFont(new Font("Serif", Font.BOLD, 20));
        questionText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        questionText.setBounds(20, 10, 550, 65);

        questionPanel.add(questionText);
        mainPanel.add(questionPanel);

        o1Panel = customOptionPanel(140, 240, 190, 60);
        o2Panel = customOptionPanel(480, 240, 190, 60);
        o3Panel = customOptionPanel(140, 380, 190, 60);
        o4Panel = customOptionPanel(480, 380, 190, 60);

        o1 = customRadioButtons();
        o2 = customRadioButtons();
        o3 = customRadioButtons();
        o4 = customRadioButtons();

        o1.setBounds(15, -25, 130, 100);
        o2.setBounds(15, -25, 130, 100);
        o3.setBounds(15, -25, 130, 100);
        o4.setBounds(15, -25, 130, 100);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(o1);
        optionsGroup.add(o2);
        optionsGroup.add(o3);
        optionsGroup.add(o4);

        o1Panel.add(o1);
        o2Panel.add(o2);
        o3Panel.add(o3);
        o4Panel.add(o4);

        mainPanel.add(o1Panel);
        mainPanel.add(o2Panel);
        mainPanel.add(o3Panel);
        mainPanel.add(o4Panel);

        next = new JButton();
        ImageIcon i = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\Next.png");
        Image img = i.getImage();
        Image resizedImg = img.getScaledInstance(55, 40, Image.SCALE_SMOOTH);
        next.setIcon(new ImageIcon(resizedImg));
        next.setBounds(590, 500, 100, 60);
        next.setFocusPainted(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.setOpaque(false);
        mainPanel.add(next);

        start.addActionListener(this);
        start.setActionCommand("start");
        next.setActionCommand("next");
        next.addActionListener(this);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static JPanel customOptionPanel(int x, int y, int w, int h) {
        JPanel panel = new JPanel();
        panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 255, 75));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 90, 90);
            }
        };
        panel.setBounds(x, y, w, h);
        panel.setOpaque(false);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return panel;
    }

    private static JRadioButton customRadioButtons() {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setOpaque(false);
        radioButton.setBackground(new Color(19, 0, 181));
        radioButton.setForeground(Color.black);
        radioButton.setFocusPainted(false);
        radioButton.setContentAreaFilled(false);
        radioButton.setBorderPainted(false);
        radioButton.setIcon(MetalIconFactory.getRadioButtonIcon());
        radioButton.setFont(new Font("Serif", Font.PLAIN, 18));
        radioButton.setFocusable(false);

        return radioButton;
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

    boolean isEndQuizCalled = true;

    private void startTimer() {
        remaining = 5 * 60;

        Thread timer = new Thread(() -> {
            try {
                while (remaining > 0) {
                    Thread.sleep(1000);
                    remaining--;

                    if (isEndQuizCalled) {
                        updateTimer();
                    }
                }
                if (remaining == 0) {
                    endQuiz();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        timer.start();
    }

    private void updateTimer() {
        int min = remaining / 60;
        int sec = remaining % 60;
        timerLabel.setText(String.format("Time remaining: %02d:%02d", min, sec));
    }

    final int MAX_QUESTIONS = 10;

    private void endQuiz() {
        isEndQuizCalled = false;
        if (timerLabel.getText().equals("Time remaining: 00:00")) {
            JOptionPane.showMessageDialog(this, "Time is up! The quiz has ended.", "Quiz Ended",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            timerLabel.setText("Time remaining: 00:00");
            questionLabel.setText("QUESTION:  " + MAX_QUESTIONS);
            JOptionPane.showMessageDialog(this, "The quiz has ended.", "Quiz Ended",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    int queCount = 0;
    Set<Integer> checkID = new HashSet<>();

    private void setQuestions() {
        if (queCount >= MAX_QUESTIONS) {
            endQuiz();
            return;
        }
        try {
            Connection con = DatabaseConnection.getConnection();

            while (true) {
                String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int queId = rs.getInt("quesid");

                    if (!checkID.contains(queId)) {
                        checkID.add(queId);
                        queCount++;

                        String ques = rs.getString("questext");
                        String op1 = rs.getString("option1");
                        String op2 = rs.getString("option2");
                        String op3 = rs.getString("option3");
                        String op4 = rs.getString("option4");

                        questionText.setText(ques);
                        o1.setText(op1);
                        o2.setText(op2);
                        o3.setText(op3);
                        o4.setText(op4);

                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
            startPanel.setVisible(false);
            startTimer();
            setQuestions();
        } else if (e.getActionCommand().equals("next")) {
            queCounter++;

            if (queCounter > 10) {
                endQuiz();
            }

            questionLabel.setText("QUESTION:  " + queCounter);
            setQuestions();
        }
    }
}

/*
 * import java.util.HashSet;
 * import java.util.Set;
 * 
 * public class PlayQuiz {
 * 
 * private Set<Integer> selectedQuestionIds = new HashSet<>();
 * private int questionCount = 0;
 * private final int MAX_QUESTIONS = 10;
 * 
 * private void setQuestions() {
 * if (questionCount >= MAX_QUESTIONS) {
 * System.out.println("All questions have been asked.");
 * return;
 * }
 * 
 * try {
 * Connection con = DatabaseConnection.getConnection();
 * String query = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";
 * PreparedStatement pst = con.prepareStatement(query);
 * ResultSet rs = pst.executeQuery();
 * 
 * while (rs.next()) {
 * int questionId = rs.getInt("id"); // Assuming 'id' is the primary key column
 * for the questions table
 * 
 * if (selectedQuestionIds.contains(questionId)) {
 * // Skip this question as it has already been asked
 * continue;
 * }
 * 
 * selectedQuestionIds.add(questionId);
 * questionCount++;
 * 
 * String ques = rs.getString("questext");
 * String op1 = rs.getString("option1");
 * String op2 = rs.getString("option2");
 * String op3 = rs.getString("option3");
 * String op4 = rs.getString("option4");
 * 
 * questionText.setText(ques);
 * o1.setText(op1);
 * o2.setText(op2);
 * o3.setText(op3);
 * o4.setText(op4);
 * 
 * break;
 * }
 * } catch (Exception ex) {
 * ex.printStackTrace();
 * }
 * }
 * }
 */