package com.dbdemo;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame("Online Quiz Platform");
        f.setSize(850, 650);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String image = "D:\\Project#2\\Quiz Platform\\qems\\quiz.png";
        JPanel p = bgPanel(image);
        f.setContentPane(p);

        JButton b1, b2, b3;

        String i1 = "D:\\Project#2\\Quiz Platform\\qems\\index admin.png";
        String i2 = "D:\\Project#2\\Quiz Platform\\qems\\index student.png";
        String i3 = "D:\\Project#2\\Quiz Platform\\qems\\Close.png";

        b1 = createImage(i1, " Admin", 130, 20);
        b2 = createImage(i2, " User", 330, 20);
        b3 = createImage(i3, "  Exit", 530, 20);

        p.add(b1);
        p.add(b2);
        p.add(b3);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
            }

        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserLogin();
            }

        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.setLayout(null);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
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

    private static JButton createImage(String path, String text, int x, int y) {
        JButton b = new JButton(text);

        try {
            ImageIcon icon = new ImageIcon(path);
            b.setIcon(icon);
        } catch (Exception e) {
        }

        b.setBounds(x, y, 150, 65);

        b.setFont(new Font("Arial", Font.BOLD, 16));

        return b;
    }
}
