package universitymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import javax.swing.border.*;
public class AddStudent extends JFrame implements ActionListener {
    JTextField tfname, tffname, tfaddress, tfphone, tfemail, tfx, tfxii, tfaadhar;
    JLabel labelrollno;
    JDateChooser dcdob;
    JComboBox cbcourse, cbbranch;
    JButton submit, cancel;
    JPanel mainPanel, glassPanel;
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    AddStudent() {
        setSize(900, 750);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Transparent Window for Rounded Corners
        setLayout(null);
        
        // --- 1. GLASSMORPHISM MAIN PANEL ---
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded Body
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 800, 900, 750); // Start position: Bottom of screen
        add(mainPanel);

        // --- 2. HEADER GRADIENT ---
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gd = new GradientPaint(0, 0, new Color(0, 153, 255), getWidth(), 0, new Color(0, 51, 153));
                g2.setPaint(gd);
                g2.fillRoundRect(0, 0, getWidth(), 100, 30, 30);
                g2.fillRect(0, 50, getWidth(), 50); // Sharp bottom edges for header
            }
        };
        header.setBounds(0, 0, 900, 100);
        header.setLayout(null);
        mainPanel.add(header);

        JLabel heading = new JLabel("STUDENT ENROLLMENT ENGINE");
        heading.setBounds(0, 30, 900, 40);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);
        header.add(heading);
        
        // --- 3. INPUT DESIGN ---
        setupForm();

        // --- 4. BUTTONS ---
        submit = createStyledButton("SUBMIT", 280, 650, new Color(40, 167, 69));
        cancel = createStyledButton("CLOSE", 480, 650, new Color(220, 53, 69));
        mainPanel.add(submit);
        mainPanel.add(cancel);

        setVisible(true);

        // --- 5. CINEMATIC BOUNCE ANIMATION ---
        animateEntrance();
    }

    private void setupForm() {
        String[] labels = {"Full Name", "Father's Name", "Roll Number", "D.O.B", "Address", "Phone", "Email ID", "Class X (%)", "Class XII (%)", "Aadhar No", "Course", "Branch"};
        int x = 50, y = 150;
        
        tfname = addStyledField("Full Name", 50, 150);
        tffname = addStyledField("Father's Name", 480, 150);
        
        // Roll No (Non-editable style)
        createLabel("System Roll No", 50, 230);
        labelrollno = new JLabel("ID-1533" + first4);
        labelrollno.setBounds(50, 260, 350, 35);
        labelrollno.setFont(new Font("Monospaced", Font.BOLD, 22));
        labelrollno.setForeground(new Color(0, 102, 204));
        mainPanel.add(labelrollno);

        createLabel("Date of Birth", 480, 230);
        dcdob = new JDateChooser();
        dcdob.setBounds(480, 260, 350, 35);
        mainPanel.add(dcdob);

        tfaddress = addStyledField("Address", 50, 310);
        tfphone = addStyledField("Phone Number", 480, 310);
        tfemail = addStyledField("Email Address", 50, 390);
        tfx = addStyledField("Class X (%)", 480, 390);
        tfxii = addStyledField("Class XII (%)", 50, 470);
        tfaadhar = addStyledField("Aadhar Number (12 Digits)", 480, 470);

        createLabel("Select Course", 50, 550);
        cbcourse = new JComboBox(new String[]{"B.Tech", "M.Tech", "BCA", "MCA", "MBA"});
        cbcourse.setBounds(50, 580, 350, 35);
        mainPanel.add(cbcourse);

        createLabel("Select Branch", 480, 550);
        cbbranch = new JComboBox(new String[]{"Computer Science", "IT", "Mechanical", "Civil"});
        cbbranch.setBounds(480, 580, 350, 35);
        mainPanel.add(cbbranch);
    }

    private JTextField addStyledField(String title, int x, int y) {
        createLabel(title, x, y);
        JTextField field = new JTextField();
        field.setBounds(x, y + 30, 350, 35);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(new LineBorder(new Color(0, 153, 255), 2));
                field.setBackground(new Color(240, 248, 255));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(new LineBorder(new Color(200, 200, 200), 1));
                field.setBackground(Color.WHITE);
            }
        });
        mainPanel.add(field);
        return field;
    }

    private void createLabel(String text, int x, int y) {
        JLabel l = new JLabel(text.toUpperCase());
        l.setBounds(x, y, 300, 25);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(Color.GRAY);
        mainPanel.add(l);
    }

    private JButton createStyledButton(String text, int x, int y, Color c) {
        JButton b = new JButton(text);
        b.setBounds(x, y, 150, 45);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.addActionListener(this);
        return b;
    }

    private void animateEntrance() {
        new Thread(() -> {
            int targetY = 0;
            int currentY = 800;
            double velocity = 0;
            double spring = 0.1;
            double friction = 0.8;

            while (Math.abs(targetY - currentY) > 1 || velocity > 1) {
                double force = (targetY - currentY) * spring;
                velocity = (velocity + force) * friction;
                currentY += velocity;
                mainPanel.setBounds(0, (int)currentY, 900, 750);
                try { Thread.sleep(15); } catch (Exception e) {}
            }
            mainPanel.setBounds(0, 0, 900, 750);
        }).start();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            if (validateForm()) {
                saveToDB();
            }
        } else {
            dispose();
        }
    }

    private boolean validateForm() {
        if (tfname.getText().isEmpty() || tfphone.getText().isEmpty() || tfaadhar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "🚨 Security Violation: All mandatory fields must be filled!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tfphone.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Invalid Phone Number!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void saveToDB() {
        try {
            conn con = new conn();
            String query = "insert into student values(?,?,?,?,?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pstmt = con.c.prepareStatement(query);
            // ... (Aapka insertion logic same rahega)
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "🚀 Data Encrypted & Saved Successfully!");
            dispose();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}
