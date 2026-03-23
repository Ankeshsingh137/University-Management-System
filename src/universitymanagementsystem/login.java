package universitymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {

    JButton btnLogin, btnCancel;
    JTextField tfusername;
    JPasswordField tfpassword;
    JPanel leftPanel, rightPanel;
    JLabel imageLabel, lblHeading;

    login() {
        setSize(850, 500);
        setLocationRelativeTo(null);
        setUndecorated(true); // Modern Borderless Look
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        // --- 1. LEFT PANEL (BLUE ANIMATED PANEL) ---
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(30, 144, 255)); // Dodger Blue
        leftPanel.setBounds(-400, 0, 400, 500); // Start position (Hidden Left)
        leftPanel.setLayout(null);
        add(leftPanel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(i2));
        imageLabel.setBounds(75, 100, 250, 250);
        leftPanel.add(imageLabel);

        JLabel lblTag = new JLabel("University Management");
        lblTag.setForeground(Color.WHITE);
        lblTag.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        lblTag.setBounds(100, 360, 250, 30);
        leftPanel.add(lblTag);

        // --- 2. RIGHT PANEL (LOGIN FORM) ---
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(850, 0, 450, 500); // Start position (Hidden Right)
        rightPanel.setLayout(null);
        add(rightPanel);

        lblHeading = new JLabel("MEMBER LOGIN");
        lblHeading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblHeading.setForeground(new Color(50, 50, 50));
        lblHeading.setBounds(100, 60, 300, 40);
        rightPanel.add(lblHeading);

        // Username
        JLabel uUser = new JLabel("USERNAME");
        uUser.setBounds(60, 150, 100, 20);
        rightPanel.add(uUser);

        tfusername = new JTextField();
        tfusername.setBounds(60, 175, 330, 35);
        tfusername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(30, 144, 255)));
        rightPanel.add(tfusername);

        // Password
        JLabel uPass = new JLabel("PASSWORD");
        uPass.setBounds(60, 230, 100, 20);
        rightPanel.add(uPass);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(60, 255, 330, 35);
        tfpassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(30, 144, 255)));
        rightPanel.add(tfpassword);

        // Buttons
        btnLogin = new JButton("SIGN IN");
        btnLogin.setBounds(60, 340, 150, 45);
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(this);
        rightPanel.add(btnLogin);

        btnCancel = new JButton("EXIT");
        btnCancel.setBounds(240, 340, 150, 45);
        btnCancel.setBackground(new Color(231, 76, 60));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        rightPanel.add(btnCancel);

        setVisible(true);

        // START SIDE-TO-SIDE ANIMATION
        playEntranceAnimation();
    }

    private void playEntranceAnimation() {
        new Thread(() -> {
            for (int i = 0; i <= 400; i += 10) {
                try {
                    Thread.sleep(15);
                    leftPanel.setBounds(-400 + i, 0, 400, 500); // Left Panel slides In
                    rightPanel.setBounds(850 - i - 50, 0, 450, 500); // Right Panel slides In
                    repaint();
                } catch (Exception e) {}
            }
        }).start();
    }

    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == btnLogin) {
        String user = tfusername.getText();
        String pass = new String(tfpassword.getPassword());

        try {
            conn c = new conn();
            String q = "select * from login where username = ? and password = ?";
            PreparedStatement pstmt = c.c.prepareStatement(q);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                setVisible(false);
                // Dashboard se pehle Welcome Screen khulegi
                new WelcomeScreen(user); 
            } else {
                JOptionPane.showMessageDialog(null, "❌ Invalid Username/Password");
            }
        } catch (Exception e) { e.printStackTrace(); }
    } else {
        System.exit(0);
    }
}


    public static void main(String[] args) {
        new login();
    }
}
