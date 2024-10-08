package com.dbdemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class QuestionService extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new QuestionService();
    }

    JButton b1, b2, b3, b4;
    MyHashMap<Integer, question> qMap;

    public QuestionService() {
        super("Questions Service");
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

        bgLabel.add(b1);

        String[] columnNames = { "Question ID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.DARK_GRAY);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(Color.YELLOW);
        table.setSelectionForeground(Color.RED);

        // Set table header properties
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.GRAY);
        header.setForeground(Color.WHITE);

        qMap = new MyHashMap<>();

        try {
            ResultSet rs = DatabaseConnection.getQuestions();
            while (rs.next()) {
                int id = rs.getInt("quesID");
                String quesText = rs.getString("quesText");
                String option1 = rs.getString("option1");
                String option2 = rs.getString("option2");
                String option3 = rs.getString("option3");
                String option4 = rs.getString("option4");
                String answer = rs.getString("answer");
                question q = new question(id, quesText, option1, option2, option3, option4, answer);
                qMap.put(id, q);
                model.addRow(new Object[] { id, quesText, option1, option2, option3, option4, answer });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching questions from database: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            // e.printStackTrace();
        }

        String i1 = "D:\\Project#2\\Quiz Platform\\qems\\search.png";
        String i2 = "D:\\Project#2\\Quiz Platform\\qems\\update.png";
        String i3 = "D:\\Project#2\\Quiz Platform\\qems\\delete.png";

        b2 = createImage(i1, "Search", 150, 510);
        b3 = createImage(i2, "Update", 350, 510);
        b4 = createImage(i3, "Delete", 550, 510);

        bgLabel.add(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 30, 750, 450);
        bgLabel.add(scrollPane);

        bgLabel.add(b2);
        bgLabel.add(b3);
        bgLabel.add(b4);

        b1.addActionListener(this);
        b1.setActionCommand("b1");
        b2.addActionListener(this);
        b2.setActionCommand("b2");
        b3.addActionListener(this);
        b3.setActionCommand("b3");
        b4.addActionListener(this);
        b4.setActionCommand("b4");

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("b1")) {
            this.dispose();
            new AdminDashBoard().setVisible(true);
        } else if (e.getActionCommand().equals("b2")) {
            new Search(qMap);
            this.dispose();
        } else if (e.getActionCommand().equals("b3")) {
            new update(qMap);
            this.dispose();
        } else if (e.getActionCommand().equals("b4")) {
            new delete(qMap);
            this.dispose();
        }
    }

    private static JButton createImage(String path, String text, int x, int y) {
        JButton b = new JButton(text);

        try {
            ImageIcon icon = new ImageIcon(path);
            b.setIcon(icon);
        } catch (Exception e) {
        }

        b.setBounds(x, y, 135, 30);
        b.setBackground(Color.yellow);
        b.setFocusable(false);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setFont(new Font("Arial", Font.BOLD, 16));

        return b;
    }
}

class Search extends JFrame implements ActionListener {
    JButton b1, b2;
    JLabel l1, l2;
    JTextField t1;
    JTextArea ta;
    MyHashMap<Integer, question> qMap;

    public Search(MyHashMap<Integer, question> qMap) {
        super("Search Question");
        this.qMap = qMap;
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

        l1=createLabel("Enter id: ", 6);
        
        t1 = new JTextField(20);
        t1.setBounds(130, 42, 80, 25);
        t1.setFont(new Font("Arial", Font.BOLD, 16));

        b2 = new JButton();
        ImageIcon i = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\search.png");
        b2.setIcon(i);
        b2.setBounds(213, 45, 20, 23);
        b2.setBackground(Color.lightGray);
        b2.setFocusable(false);
        b2.setFocusPainted(false);
        b2.setBorderPainted(false);

        l2=createLabel("Results: ", 60);
        
        ta = new JTextArea(10, 20);
        ta.setFont(new Font("Arial", Font.BOLD, 16));
        ta.setBounds(40, 138, 720, 350);
        ta.setBorder(BorderFactory.createLineBorder(Color.black, 4));

        bgLabel.add(b1);
        bgLabel.add(l1);
        bgLabel.add(t1);
        bgLabel.add(b2);
        bgLabel.add(l2);
        bgLabel.add(ta);

        b1.addActionListener(this);
        b1.setActionCommand("b1");
        b2.addActionListener(this);
        b2.setActionCommand("b2");

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setBounds(25, y, 100, 100);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("b1")) {
            this.dispose();
            new QuestionService();
        } else if (e.getActionCommand().equals("b2")) {
            try {
                int id = Integer.parseInt(t1.getText().trim());
                question q = qMap.get(id);

                if (q != null) {
                    ta.setText("Question ID: " + q.getId() + "\n\n" + "Question: " + q.getQues() + "\n\n" + "Option 1: "
                            + q.getO1() + "\n\n" + "Option 2: " + q.getO2() + "\n\n" + "Option 3: " + q.getO3() + "\n\n"
                            + "Option 4: " + q.getO4() + "\n\n" + "Answer: " + q.getAns());
                } else {
                    ta.setText("No question found with this id: " + id);
                }
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer ID", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                t1.setText("");
            }
        }
    }
}

class delete extends JFrame implements ActionListener {
    JButton b1, b2, b3;
    JLabel l1, l2;
    JTextField t1;
    JTextArea ta;
    MyHashMap<Integer, question> qMap;

    public delete(MyHashMap<Integer, question> qMap) {
        super("Delete Question");
        this.qMap = qMap;
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

        l1=createLabel("Enter id: ", 6);
        
        t1 = new JTextField(20);
        t1.setBounds(130, 42, 80, 25);
        t1.setFont(new Font("Arial", Font.BOLD, 16));

        b2 = new JButton();
        ImageIcon i = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\search.png");
        b2.setIcon(i);
        b2.setBounds(213, 45, 20, 23);
        b2.setBackground(Color.lightGray);
        b2.setFocusable(false);
        b2.setFocusPainted(false);
        b2.setBorderPainted(false);

        l2=createLabel("Results: ", 60);

        ta = new JTextArea(10, 20);
        ta.setFont(new Font("Arial", Font.BOLD, 16));
        ta.setBounds(40, 138, 720, 350);
        ta.setBorder(BorderFactory.createLineBorder(Color.black, 4));

        b3 = new JButton("Delete");
        b3.setBounds(310, 515, 90, 30);
        b3.setFont(new Font("Arial", Font.BOLD, 16));
        b3.setBackground(Color.green);
        b3.setFocusable(false);
        b3.setFocusPainted(false);
        b3.setBorderPainted(false);

        bgLabel.add(b1);
        bgLabel.add(l1);
        bgLabel.add(t1);
        bgLabel.add(b2);
        bgLabel.add(l2);
        bgLabel.add(ta);
        bgLabel.add(b3);

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

    private JLabel createLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setBounds(25, y, 100, 100);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = 0;
        if (e.getActionCommand().equals("b1")) {
            this.dispose();
            new QuestionService();
        } else if (e.getActionCommand().equals("b2")) {
            try {
                id = Integer.parseInt(t1.getText().trim());
                question q = qMap.get(id);

                if (q != null) {
                    ta.setText("Question ID: " + q.getId() + "\n\n" + "Question: " + q.getQues() + "\n\n" + "Option 1: "
                            + q.getO1() + "\n\n" + "Option 2: " + q.getO2() + "\n\n" + "Option 3: " + q.getO3() + "\n\n"
                            + "Option 4: " + q.getO4() + "\n\n" + "Answer: " + q.getAns());
                } else {
                    ta.setText("No question found with this id: " + id);
                }
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer ID", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                t1.setText("");
            }
        } else if (e.getActionCommand().equals("b3")) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure want to delete?", "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                try {
                    id = Integer.parseInt(t1.getText().trim());
                    Connection con = DatabaseConnection.getConnection();
                    String query = "delete from questions where quesid=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setInt(1, id);
                    int r = pst.executeUpdate();
                    if (r > 0) {
                        qMap.remove(id);
                        JOptionPane.showMessageDialog(this, "Deleted Successfully");
                        ta.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                }
            }
        }
    }
}

class update extends JFrame implements ActionListener {
    JButton b1, b2, b3;
    JLabel l1, l2, l3, l4, l5, l6, l7, msg;
    JTextField t1, t2, t3, t4, t5, t6;
    JTextArea ta;
    MyHashMap<Integer, question> qMap;

    public update(MyHashMap<Integer, question> qMap) {
        super("Update Question");
        this.qMap = qMap;
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

        l7 = new JLabel("Enter id: ");
        l7.setForeground(Color.WHITE);
        l7.setBounds(25, 6, 100, 100);
        l7.setFont(new Font("Arial", Font.BOLD, 20));

        t6 = new JTextField(20);
        t6.setBounds(130, 42, 80, 25);
        t6.setFont(new Font("Arial", Font.BOLD, 16));

        b3 = new JButton();
        ImageIcon i = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\search.png");
        b3.setIcon(i);
        b3.setBounds(213, 45, 20, 23);
        b3.setBackground(Color.lightGray);
        b3.setFocusable(false);
        b3.setFocusPainted(false);
        b3.setBorderPainted(false);

        b1 = new JButton();
        ImageIcon path = new ImageIcon("D:\\Project#2\\Quiz Platform\\qems\\Back.png");
        b1.setIcon(path);
        b1.setBounds(25, 550, 60, 30);
        b1.setBackground(Color.lightGray);
        b1.setFocusable(false);
        b1.setFocusPainted(false);
        b1.setBorderPainted(false);

        l1 = createLabel("QUESTION", 50, 60);
        
        ta = new JTextArea(8, 500);
        ta.setFont(new Font("Arial", Font.BOLD, 16));
        ta.setBounds(50, 125, 650, 55);
        ta.setBorder(BorderFactory.createLineBorder(Color.black, 4));


        l2 = createLabel("OPTION 1", 90, 180);
        t1 = createTextField(90, 245);

        l3 = createLabel("OPTION 2", 410, 180);
        t2 = createTextField(410, 245);

        l4 = createLabel("OPTION 3", 90, 280);
        t3 = createTextField(90, 345);

        l5 = createLabel("OPTION 4", 410, 280);
        t4 = createTextField(410, 345);

        l6 = createLabel("ANSWER", 320, 370);
        
        t5 = new JTextField(20);
        t5.setFont(new Font("Arial", Font.BOLD, 16));
        t5.setBounds(260, 435, 200, 25);
        t5.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));

        b2 = new JButton("Update");
        b2.setBounds(310, 515, 90, 30);
        b2.setFont(new Font("Arial", Font.BOLD, 16));
        b2.setBackground(Color.green);
        b2.setFocusable(false);
        b2.setFocusPainted(false);
        b2.setBorderPainted(false);

        msg = new JLabel();
        msg.setForeground(Color.RED);
        msg.setBounds(290, 500, 200, 90);

        bgLabel.add(l7);
        bgLabel.add(t6);
        bgLabel.add(b3);
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
        bgLabel.add(msg);
        bgLabel.add(b2);

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

    private JButton createButton(String text, int x){
        JButton button = new JButton(text);
        button.setBounds(x,470, 90, 30);
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
        tf.setBounds(x, y,200, 25);
        tf.setBorder(BorderFactory.createLineBorder(Color.red, 4));

        return tf;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int id = 0;
        if (e.getActionCommand().equals("b1")) {
            this.dispose();
            new QuestionService();
        } else if (e.getActionCommand().equals("b2")) {
            id = Integer.parseInt(t6.getText().trim());
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure want to update?", "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                String q = ta.getText();
                String o1 = t1.getText();
                String o2 = t2.getText();
                String o3 = t3.getText();
                String o4 = t4.getText();
                String ans = t5.getText();
                try {
                    Connection con = DatabaseConnection.getConnection();
                    String query = "update questions set quesText = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, answer = ? where quesid = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, q);
                    ps.setString(2, o1);
                    ps.setString(3, o2);
                    ps.setString(4, o3);
                    ps.setString(5, o4);
                    ps.setString(6, ans);
                    ps.setInt(7, id);
                    int r = ps.executeUpdate();
                    if (r > 0) {
                        // qMap.remove(id);
                        JOptionPane.showMessageDialog(this, "Updated question successfully");
                        ta.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    ta.setText("");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                }

                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(this, "Error adding question to database", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("b3")) {
            try {
                id = Integer.parseInt(t6.getText().trim());
                question q = qMap.get(id);

                if (q != null) {
                    ta.setText(q.getQues());
                    t1.setText(q.getO1());
                    t2.setText(q.getO2());
                    t3.setText(q.getO3());
                    t4.setText(q.getO4());
                    t5.setText(q.getAns());
                } else {
                    ta.setText("No question found with this id: " + id);
                }
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer ID", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                t6.setText("");
            }
        }
    }
}
