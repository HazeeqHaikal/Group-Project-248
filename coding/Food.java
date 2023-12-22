import java.util.*;
import java.io.*;

public class Food {
    private String foodName;
    private int quantity;
    private double price;
    private Date expiryDate;
    private double netWeight;

    public Food() {
        this.foodName = "";
        this.quantity = 0;
        this.price = 0.0;
        this.expiryDate = new Date();
        this.netWeight = 0.0;
    }

    public Food(String foodName, int quantity, double price, Date expiryDate, double netWeight) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
        this.netWeight = netWeight;
    }

    // setter
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    // getter
    public String getFoodName() {
        return foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public double getNetWeight() {
        return netWeight;
    }

    // calculate the total weight
    public double calculateTotalWeight() {
        return netWeight * quantity;
    }

    // calculate price after SST
    public double afterSST() {
        return price * 1.06;
    }

    // calculate the total price
    public double calculateTotalPrice() {
        return afterSST() * quantity;
    }

    // member or non-member
    public boolean isMember() {
        // read from file
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("data.txt"));
            String line = br.readLine();
            while (line != null) {
                boolean isMember = Boolean.parseBoolean(line.split(",")[2]);
                if (isMember) {
                    br.close();
                    return true;
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File " + e.getMessage() + " not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\nAt line: " + e.getStackTrace()[0].getLineNumber());
        }

        return false;
    }

    // check if today is their birthday
    public boolean isBirthday() {
        // read from file
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("data.txt"));
            // get current date
            Date currentDate = new Date();
            String line = br.readLine();
            while (line != null) {
                if (line.split(",")[1].equals(String.format("%td/%tm", currentDate, currentDate))) {
                    return true;
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File " + e.getMessage() + " not found.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\nAt line: " + e.getStackTrace()[0].getLineNumber());
        }

        return false;
    }

    // calculate the total price after discount
    // if member and birthday then the discount will be stacked
    public double discountedPrice() {
        if (isMember()) {
            return calculateTotalPrice() * 0.9;
        }

        if (isBirthday()) {
            return calculateTotalPrice() * 0.8;
        }

        return calculateTotalPrice();
    }

    // generate random foodID with a length of 6 combination string and letter
    public String generateFoodID() {
        String foodID = "";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 6;

        for (int i = 0; i < length; i++) {
            foodID += characters.charAt((int) (Math.random() * characters.length()));
        }

        return foodID;
    }

    // printer
    public String toString() {
        // format the date to dd/mm/yyyy
        String formattedDate = String.format("%td/%tm/%tY", expiryDate, expiryDate, expiryDate);
        return "Food Name: " + foodName + "\nQuantity: "
                + quantity + "\nPrice: " + String.format("%,.2f", price)
                + "\nExpiry Date: " + formattedDate + "\nNet Weight: "
                + String.format("%,.2f", netWeight) + "\n";
    }

}
