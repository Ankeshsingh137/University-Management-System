package universitymanagementsystem;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JFrame {

    JLabel lblWelcome, lblUser, lblLoading;
    JProgressBar progressBar;

    public WelcomeScreen(String username) {
        // Full Screen Setup
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        getContentPane().setBackground(new Color(15, 15, 50)); 
        setLayout(null);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        // 1. Welcome Text (Starts small for Zoom Effect)
        lblWelcome = new JLabel("WELCOME TO UNIVERSITY MANAGEMENT SYSTEM", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Serif", Font.BOLD, 10));
        lblWelcome.setForeground(new Color(0, 200, 255));
        lblWelcome.setBounds(0, screenHeight / 2 - 150, screenWidth, 80);
        add(lblWelcome);

        // 2. User Info
        lblUser = new JLabel("USER: " + username.toUpperCase(), SwingConstants.CENTER);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 35));
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(0, screenHeight / 2 - 50, screenWidth, 60);
        lblUser.setVisible(false); // Initially hidden
        add(lblUser);

        // 3. Progress Bar with Percentage
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(screenWidth / 2 - 300, screenHeight / 2 + 100, 600, 35);
        progressBar.setBackground(new Color(50, 50, 80));
        progressBar.setForeground(new Color(0, 255, 150)); // Neon Green
        progressBar.setBorderPainted(false);
        progressBar.setStringPainted(true); // Shows percentage
        progressBar.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(progressBar);

        // 4. Dynamic Loading Sub-text
        lblLoading = new JLabel("Initializing Secure Connection...", SwingConstants.CENTER);
        lblLoading.setFont(new Font("Tahoma", Font.ITALIC, 16));
        lblLoading.setForeground(Color.LIGHT_GRAY);
        lblLoading.setBounds(0, screenHeight / 2 + 150, screenWidth, 30);
        add(lblLoading);

        setVisible(true);

        // --- ANIMATION THREAD (Total ~6 Seconds) ---
        new Thread(() -> {
            try {
                // Phase 1: Zoom-In Animation (2.5 Sec)
                for (int i = 10; i <= 50; i++) {
                    Thread.sleep(50);
                    lblWelcome.setFont(new Font("Serif", Font.BOLD, i));
                    // Smoothly increase window transparency
                    this.setOpacity(Math.min((i - 10) / 40.0f, 1.0f));
                }

                lblUser.setVisible(true); // Reveal Username

                // Phase 2: Loading & Percentage (3.5 Sec)
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(35);
                    progressBar.setValue(i);
                    progressBar.setString(i + "% Completed");
                    
                    // Update Status Text based on Percentage
                    if (i == 20) lblLoading.setText("Establishing Database Handshake...");
                    if (i == 50) lblLoading.setText("Fetching Secure User Token...");
                    if (i == 85) lblLoading.setText("Launching System Dashboard...");
                }

                Thread.sleep(800); // Hold at 100%

                // Phase 3: Smooth Fade-Out
                for (float i = 1f; i >= 0f; i -= 0.05f) {
                    this.setOpacity(i);
                    Thread.sleep(25);
                }

                setVisible(false);
                new Project(); // Redirect to Dashboard

            } catch (Exception e) {
                e.printStackTrace();
                new Project();
            }
        }).start();
    }

    // Main Method to run the file independently
    public static void main(String[] args) {
        // Run on the Event Dispatch Thread for Swing safety
        SwingUtilities.invokeLater(() -> {
            new WelcomeScreen("Admin"); 
        });
    }
}
