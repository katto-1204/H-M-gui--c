package clothingordersystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        // Setup frame
        setTitle("Clothing Order System - Login");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the background color of the frame to red
        getContentPane().setBackground(Color.RED);

        // Create main panel with centered layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.RED); // Panel background also red
        GridBagConstraints gbc = new GridBagConstraints();

        // Title label
        JLabel titleLabel = new JLabel("H&M");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));  // Scale up font size
        titleLabel.setForeground(Color.WHITE);  // Make text white
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // center text inside label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 30, 10); // top space increased
        panel.add(titleLabel, gbc);

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setForeground(Color.WHITE);  // White color for label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(usernameLabel, gbc);

        // Username field
        usernameField = new JTextField(15);
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        usernameField.setBackground(Color.WHITE);  // White input field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 10);
        panel.add(usernameField, gbc);

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);  // White color for label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(passwordLabel, gbc);

        // Password field
        passwordField = new JPasswordField(15);
        passwordField.setHorizontalAlignment(JTextField.LEFT);
        passwordField.setBackground(Color.WHITE);  // White input field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 10);
        panel.add(passwordField, gbc);

        // Sign In button (white background, black text)
        JButton signInButton = new JButton("Sign In");
        signInButton.setBackground(Color.WHITE);  // White background for button
        signInButton.setForeground(Color.BLACK);  // Black text for button
        signInButton.setFocusPainted(false);
        signInButton.setBorderPainted(false);
        signInButton.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(signInButton, gbc);

        // Button click action
        signInButton.addActionListener(e -> signIn());

        // Enter key triggers login too
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signIn();
                }
            }
        };
        usernameField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);

        // Add panel to frame
        add(panel);

        // Show frame
        setVisible(true);
    }

    private void signIn() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Close login window
        dispose();

        // Open main page (pass User object)
        new MainPage(new User(username, password));
    }
}
