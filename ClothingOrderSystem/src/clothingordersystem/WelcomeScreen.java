package clothingordersystem;
import javax.swing.*;
import java.awt.*;

class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
        // Frame setup
        setTitle("Welcome");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        // Main panel
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Title label
        JLabel titleLabel = new JLabel("H&M");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(titleLabel, gbc);
        // Subtitle label
        JLabel subtitleLabel = new JLabel("Clothing Order System");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 20, 10);
        panel.add(subtitleLabel, gbc);
        // Start Shopping Button (black with white text)
        JButton startButton = new JButton("START SHOPPING");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setOpaque(true);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(startButton, gbc);
        // Button Action
        startButton.addActionListener(e -> {
            dispose(); // close Welcome screen
            new LoginScreen(); // open MainPage screen

        });
        // Add panel to frame
        add(panel);
        setVisible(true);
    }
}

