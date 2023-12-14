import java.io.*;

public class Staff extends FileHandling {

    private String username;
    private String password;

    // default constructor
    public Staff() {
        this.username = "";
        this.password = "";
    }

    // normal constructor
    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getter
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    // setter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // set food price
    public void setFoodPrice(Food food, double price) {
        food.setPrice(price);
    }

    // set food quantity
    public void setFoodQuantity(Food food, int quantity) {
        food.setQuantity(quantity);
    }

    // toString
    public String toString() {
        return "Username: " + username + "\nPassword: " + password;
    }
}
