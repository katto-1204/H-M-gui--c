package clothingordersystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

class MainPage extends JFrame {
    private User user; // Now using a User object
    private JPanel mainPanel;
    private JPanel productPanel;
    private JLabel subtotalLabel;
    private double subtotal = 0.0;
    private ArrayList<Product> cart = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("0.00");
    // Product catalog
    private HashMap<String, ArrayList<Product>> productCatalog;

    public MainPage(User user) {
        this.user = user;
        
        // Initialize product catalog
        initializeProducts();
        
        // Setup frame
        setTitle("Clothing Order System - Main Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main layout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.RED);  // Set background color of main panel to red
        
        // Add header panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Add category panel on the left
        JPanel categoryPanel = createCategoryPanel();
        mainPanel.add(categoryPanel, BorderLayout.WEST);
        
        // Create product panel for the center
        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setBackground(Color.RED);  // Set background color of product panel to red
        JScrollPane scrollPane = new JScrollPane(productPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Create welcome message in product panel
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(Color.RED); // Set background color of welcome panel to red
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>"
                + "<h2>Welcome to the Clothing Order System!</h2>"
                + "<p>Select a category from the left to view products.</p></div></html>");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);  // Set text color to white
        welcomePanel.add(welcomeLabel);
        productPanel.add(welcomePanel);
        
        // Add cart panel at the bottom
        JPanel cartPanel = createCartPanel();
        mainPanel.add(cartPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        add(mainPanel);
        
        // Display the frame
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.RED);  // Set background color to red
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("H&M: CLOTHING ORDERING SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);  // Set text color to white
        
        // Create a panel for the right side with username and cart button
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(Color.RED);  // Set background color to red
        
        // Add cart button with icon
        JLabel userLabel = new JLabel("Welcome, " + user.getUsername());
        userLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        userLabel.setForeground(Color.WHITE);  // Set text color to white
        rightPanel.add(userLabel);
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(rightPanel, BorderLayout.EAST);
        return headerPanel;
    }

    private ImageIcon createCartIcon() {
        // Create a simple cart icon
        int size = 16;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        // Draw cart body
        g2d.drawRect(0, 8, 12, 6);
        // Draw cart wheels
        g2d.fillOval(2, 14, 3, 3);
        g2d.fillOval(8, 14, 3, 3);
        // Draw cart handle
        g2d.drawLine(12, 10, 15, 5);
        g2d.dispose();
        return new ImageIcon(image);
    }

    private void openCart() {
        // Update user cart before opening Cart window
        user.setCart(cart);
        // Open the cart window
        new Cart(user);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);  // Set button background color to black
        button.setForeground(Color.WHITE);  // Set text color to white
        button.setOpaque(true);  // Make the button opaque
        button.setBorderPainted(false);  // Remove border
        button.setFocusPainted(false);  // Remove focus outline
        button.setRolloverEnabled(true);  // Enable rollover effect
        // Change color on hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);  // Change to dark gray on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.BLACK);  // Reset to black when hover ends
            }
        });
    }

    private void initializeProducts() {
        productCatalog = new HashMap<>();
        // Tops
        ArrayList<Product> tops = new ArrayList<>();
        tops.add(new Product("T-Shirt", 19.99));
        tops.add(new Product("Dress Shirt", 39.99));
        tops.add(new Product("Sweater", 29.99));
        tops.add(new Product("Hoodie", 34.99));
        tops.add(new Product("Polo Shirt", 24.99));
        productCatalog.put("Tops", tops);
        // Bottoms
        ArrayList<Product> bottoms = new ArrayList<>();
        bottoms.add(new Product("Jeans", 45.99));
        bottoms.add(new Product("Shorts", 25.99));
        bottoms.add(new Product("Khakis", 35.99));
        bottoms.add(new Product("Sweatpants", 29.99));
        bottoms.add(new Product("Skirt", 32.99));
        productCatalog.put("Bottom", bottoms);
        // Shoes
        ArrayList<Product> shoes = new ArrayList<>();
        shoes.add(new Product("Sneakers", 59.99));
        shoes.add(new Product("Dress Shoes", 79.99));
        shoes.add(new Product("Sandals", 24.99));
        shoes.add(new Product("Boots", 89.99));
        shoes.add(new Product("Slippers", 19.99));
        productCatalog.put("Shoes", shoes);
    }
    
    private JPanel createCategoryPanel() {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
        categoryPanel.setBackground(Color.RED);  // Set background color to red
        categoryPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createTitledBorder("Categories")
        ));
        categoryPanel.setPreferredSize(new Dimension(150, 0));
        String[] categories = {"Tops", "Bottom", "Shoes"};
        for (String category : categories) {
            JButton categoryButton = new JButton(category);
            categoryButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, categoryButton.getPreferredSize().height));
            categoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            // Apply uniform button styling
            styleButton(categoryButton);
            categoryButton.addActionListener(e -> displayProductsForCategory(category));
            categoryPanel.add(categoryButton);
            categoryPanel.add(Box.createVerticalStrut(10));
        }
        return categoryPanel;
    }

    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(Color.RED);  // Set background color to red
        cartPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        subtotalLabel = new JLabel("Subtotal: $" + df.format(subtotal));
        subtotalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        subtotalLabel.setForeground(Color.WHITE);  // Set text color to white
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.RED);  // Set background color to red
        JButton viewCartButton = new JButton("View Cart");
        styleButton(viewCartButton);  // Apply button style
        viewCartButton.addActionListener(e -> openCart());
        JButton checkoutButton = new JButton("Checkout");
        styleButton(checkoutButton);  // Apply button style
        checkoutButton.addActionListener(e -> openCart());  // Implement checkout functionality
        
        buttonPanel.add(viewCartButton);
        buttonPanel.add(checkoutButton);
        cartPanel.add(subtotalLabel, BorderLayout.WEST);
        cartPanel.add(buttonPanel, BorderLayout.EAST);
        return cartPanel;
    }
    
    private void displayProductsForCategory(String category) {
    productPanel.removeAll();
    
    // Set background color of the productPanel to black
    productPanel.setBackground(Color.RED);

    // Category Title
    JLabel categoryTitle = new JLabel(category);
    categoryTitle.setFont(new Font("Arial", Font.BOLD, 20));
    categoryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    categoryTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
    categoryTitle.setForeground(Color.WHITE); // Text color white
    productPanel.add(categoryTitle);

    // Products
    ArrayList<Product> products = productCatalog.get(category);
    for (Product product : products) {
        // BIG BOX (Bordered Box for each product)
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS)); // Vertical Layout
        boxPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true)); // Box border
        boxPanel.setBackground(Color.BLACK); // Black background for the product box
        boxPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Adjusted Height

        // TOP: Item Name (centered)
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        nameLabel.setForeground(Color.WHITE); // Text color white
        boxPanel.add(nameLabel);

        // BOTTOM: Row with Price (left) and Add to Cart (right)
        JPanel bottomRow = new JPanel(new BorderLayout());
        bottomRow.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 20)); // Padding around bottom row
        bottomRow.setOpaque(false); // Transparent background to match box

        // LEFT: Price
        JLabel priceLabel = new JLabel("$" + df.format(product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(Color.WHITE); // Price text color white
        bottomRow.add(priceLabel, BorderLayout.WEST);

        // RIGHT: Add to Cart Button
        JButton addButton = new JButton("Add to Cart");
        styleButton(addButton); // Apply custom button style
        addButton.setBackground(Color.RED); // Set button background to red
        addButton.setForeground(Color.WHITE); // Set button text to white
        addButton.addActionListener(e -> addToCart(product));
        bottomRow.add(addButton, BorderLayout.EAST);

        // Add to boxPanel
        boxPanel.add(bottomRow);

        // Add the product box (with name + bottom row) to the main productPanel
        productPanel.add(Box.createVerticalStrut(10)); // Add space between items
        productPanel.add(boxPanel);
    }

    // Extra space at bottom
    productPanel.add(Box.createVerticalGlue());

    // Revalidate and repaint to reflect changes in the UI
    productPanel.revalidate();
    productPanel.repaint();
}


   private void addToCart(Product product) {
        Product cartProduct = new Product(product.getName(), product.getPrice());
        cart.add(cartProduct);
        subtotal += cartProduct.getPrice();
        updateSubtotal();
        JOptionPane.showMessageDialog(this,
            product.getName() + " added to your cart!",
            "Product Added",
            JOptionPane.INFORMATION_MESSAGE);
    }
    private void updateSubtotal() {
        subtotalLabel.setText("Subtotal: $" + df.format(subtotal));
    }
    private void checkout() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Your cart is empty. Please add items before checkout.",
                "Empty Cart",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        openCart();
    }
}