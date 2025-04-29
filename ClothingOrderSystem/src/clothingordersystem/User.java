package clothingordersystem;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Product> cart;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<>();
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public ArrayList<Product> getCart() {
        return cart;
    }
    
    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
}
