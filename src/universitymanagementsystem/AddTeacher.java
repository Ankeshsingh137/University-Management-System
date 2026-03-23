package universitymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import javax.swing.border.*;

public class AddTeacher extends JFrame implements ActionListener {
    
    JTextField tfname, tffname, tfaddress, tfphone, tfemail, tfx, tfxii, tfaadhar;
    JLabel labelempId;
    JDateChooser dcdob;
    JComboBox cbcourse, cbbranch;
    JButton submit, cancel;
    JPanel mainPanel;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    AddTeacher() {
        setSize(900, 750);
        setLocationRelativeTo(null);
        setUndecorated(true); // Modern Borderless Look
        setBackground(new Color(0, 0, 0, 0)); // Transparent background for rounded corners
        setLayout(null);
        
        // --- 1. MAIN ROUNDED PANEL ---
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 800, 900, 750); // Start position for animation
        add(mainPanel);

        // --- 2. GRADIENT HEADER ---
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gd = new GradientPaint(0, 0, new Color(41, 128, 185), getWidth(), 0, new Color(109, 213, 250));
                g2.setPaint(gd);
                g2.fillRoundRect(0, 0, getWidth(), 100, 40, 40);
                g2.fillRect(0, 60, getWidth(), 40);
            }
        };
        header.setBounds(0, 0, 900, 100);
        header.setLayout(null);
        mainPanel.add(header);

        JLabel heading = new JLabel("FACULTY ENROLLMENT SYSTEM");
        heading.setBounds(0, 35, 900, 40);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);
        header.add(heading);
        
        // --- 3. INPUT FIELDS SETUP ---
        setupUI();

        // --- 4. BUTTONS WITH MODERN STYLE ---
        submit = createButton("SAVE DETAILS", 250, 650, new Color(46, 204, 113));
        cancel = createButton("CLOSE", 480, 650, new Color(231, 76, 60));
        mainPanel.add(submit);
        mainPanel.add(cancel);

        setVisible(true);

        // --- 5. ENTRANCE BOUNCE ANIMATION ---
        startSpringAnimation();
    }

    private void setupUI() {
        // Left Column
        tfname = addStyledField("Teacher Name", 50, 130);
        labelempId = addStaticLabel("Employee ID", "EMP-" + first4, 50, 210);
        tfaddress = addStyledField("Residential Address", 50, 290);
        tfemail = addStyledField("Official Email ID", 50, 370);
        tfxii = addStyledField("Class XII (%)", 50, 450);
        
        createLabel("Qualification", 50, 530);
        cbcourse = new JComboBox(new String[]{"B.Tech", "M.Tech", "Ph.D", "MBA", "MCA", "M.Sc"});
        cbcourse.setBounds(50, 560, 350, 35);
        mainPanel.add(cbcourse);

        // Right Column
        tffname = addStyledField("Father's Name", 480, 130);
        
        createLabel("Date of Birth", 480, 210);
        dcdob = new JDateChooser();
        dcdob.setBounds(480, 240, 350, 35);
        mainPanel.add(dcdob);

        tfphone = addStyledField("Mobile Number", 480, 290);
        tfx = addStyledField("Class X (%)", 480, 370);
        tfaadhar = addStyledField("Aadhar Number (12 Digits)", 480, 450);
        
        createLabel("Department", 480, 530);
        cbbranch = new JComboBox(new String[]{"Computer Science", "IT", "Mechanical", "Civil", "Electronics"});
        cbbranch.setBounds(480, 560, 350, 35);
        mainPanel.add(cbbranch);
    }

    private JTextField addStyledField(String title, int x, int y) {
        createLabel(title, x, y);
        JTextField field = new JTextField();
        field.setBounds(x, y + 30, 350, 35);
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(41, 128, 185)));
                field.setBackground(new Color(245, 249, 255));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
                field.setBackground(Color.WHITE);
            }
        });
        mainPanel.add(field);
        return field;
    }

    private JLabel addStaticLabel(String title, String value, int x, int y) {
        createLabel(title, x, y);
        JLabel lbl = new JLabel(value);
        lbl.setBounds(x, y + 30, 350, 35);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 20));
        lbl.setForeground(new Color(41, 128, 185));
        mainPanel.add(lbl);
        return lbl;
    }

    private void createLabel(String text, int x, int y) {
        JLabel l = new JLabel(text.toUpperCase());
        l.setBounds(x, y, 300, 25);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(new Color(100, 100, 100));
        mainPanel.add(l);
    }

    private JButton createButton(String text, int x, int y, Color c) {
        JButton b = new JButton(text);
        b.setBounds(x, y, 180, 45);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.addActionListener(this);
        return b;
    }

    private void startSpringAnimation() {
        new Thread(() -> {
            double y = 800, velocity = 0, targetY = 0;
            while (Math.abs(targetY - y) > 0.5 || Math.abs(velocity) > 0.5) {
                double force = (targetY - y) * 0.1;
                velocity = (velocity + force) * 0.8;
                y += velocity;
                mainPanel.setBounds(0, (int)y, 900, 750);
                try { Thread.sleep(15); } catch (Exception e) {}
            }
            mainPanel.setBounds(0, 0, 900, 750);
        }).start();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            if (validateFields()) {
                saveData();
            }
        } else {
            dispose();
        }
    }

    private boolean validateFields() {
        if (tfname.getText().isEmpty() || tfphone.getText().isEmpty() || tfaadhar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "🚨 ERROR: All fields are mandatory!", "Security Check", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tfphone.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Enter a valid 10-digit Phone Number", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void saveData() {
        try {
            conn con = new conn();
            String query = "insert into teacher values(?,?,?,?,?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pstmt = con.c.prepareStatement(query);
            pstmt.setString(1, tfname.getText());
            pstmt.setString(2, tffname.getText());
            pstmt.setString(3, labelempId.getText());
            pstmt.setString(4, ((JTextField) dcdob.getDateEditor().getUiComponent()).getText());
            pstmt.setString(5, tfaddress.getText());
            pstmt.setString(6, tfphone.getText());
            pstmt.setString(7, tfemail.getText());
            pstmt.setString(8, tfx.getText());
            pstmt.setString(9, tfxii.getText());
            pstmt.setString(10, tfaadhar.getText());
            pstmt.setString(11, (String)cbcourse.getSelectedItem());
            pstmt.setString(12, (String)cbbranch.getSelectedItem());
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "🚀 Faculty Data Secured & Uploaded Successfully!");
            dispose();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        new AddTeacher();
    }
}
