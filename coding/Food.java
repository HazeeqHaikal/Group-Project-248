import java.util.*;
import java.io.*;

public class Food {
    private String foodName;
    private int quantity;
    private double price;
    private Date expiryDate;
    private double netWeight;
    private String orderID;
    private String userID;
    private boolean isFinished;

    public Food() {
        this.foodName = "";
        this.quantity = 0;
        this.price = 0.0;
        this.expiryDate = new Date();
        this.netWeight = 0.0;
        this.orderID = "";
        this.userID = "";
    }

    public Food(String foodName, int quantity, double price, Date expiryDate, double netWeight) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
        this.netWeight = netWeight;
    }

    // for adding food to foodMenu.txt
    public Food(String foodName, double price, double netWeight) {
        this.foodName = foodName;
        this.price = price;
        this.netWeight = netWeight;
    }

    // for adding food to foodOrder.txt
    public Food(String userID, String orderID, String foodName, int quantity, double price, double netWeight,
            boolean isFinished) {
        this.userID = userID;
        this.orderID = orderID;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
        this.netWeight = netWeight;
        this.isFinished = isFinished;
    }

    // copy constructor
    public Food(Food food) {
        this.foodName = food.foodName;
        this.quantity = food.quantity;
        this.price = food.price;
        this.expiryDate = food.expiryDate;
        this.netWeight = food.netWeight;
        this.orderID = food.orderID;
        this.userID = food.userID;
        this.isFinished = food.isFinished;
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

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
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

    public String getOrderID() {
        return orderID;
    }

    public String getUserID() {
        return userID;
    }

    public boolean getIsFinished() {
        return isFinished;
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
    public boolean isMember() throws IOException {
        // read from file

        FileHandling data = new FileHandling("data.txt");
        String linesData = data.read();
        String[] dataPerLine = linesData.split("\n");

        if (dataPerLine.length == 0) {
            System.out.println("There is no data.");
            return false;
        }

        for (int i = 0; i < dataPerLine.length; i++) {
            String[] dataDetails = dataPerLine[i].split(",");
            boolean isMember = Boolean.parseBoolean(dataDetails[2]);

            if (isMember) {
                return true;
            }
        }

        return false;
    }

    // check if today is their birthday
    public boolean isBirthday() throws IOException {

        FileHandling data = new FileHandling("data.txt");
        String linesData = data.read();
        String[] dataPerLine = linesData.split("\n");

        if (dataPerLine.length == 0) {
            System.out.println("There is no data.");
            return false;
        }

        for (int i = 0; i < dataPerLine.length; i++) {
            String[] dataDetails = dataPerLine[i].split(",");
            String birthday = dataDetails[3];

            // get current date
            Date currentDate = new Date();

            if (birthday.equals(String.format("%td/%tm", currentDate, currentDate))) {
                return true;
            }
        }

        return false;
    }

    // calculate the total price after discount
    // if member and birthday then the discount will be stacked
    public double discountedPrice() throws IOException {
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

    // determine the food
    public void determineFood(int foodChoice, int quantity) throws IOException {

        // read the price and net weight from the text file
        FileHandling foodMenu = new FileHandling("foodMenu.txt");
        String linesFoodMenu = foodMenu.read();
        String[] foodMenuPerLine = linesFoodMenu.split("\n");
        String[] foodDetails = foodMenuPerLine[foodChoice - 1].split(",");
        double price = Double.parseDouble(foodDetails[1]);
        double netWeight = Double.parseDouble(foodDetails[2]);

        // set the quantity
        setQuantity(quantity);

        // set the food name
        setFoodName(foodDetails[0]);

        // set the price and net weight
        setPrice(price);
        setNetWeight(netWeight);

        setOrderID(generateFoodID());

        // return the food name
    }

    // display food menu from foodMenu.txt
    public int displayFoodMenu() throws IOException {
        FileHandling foodMenu = new FileHandling("foodMenu.txt");
        String linesFoodMenu = foodMenu.read();

        if (linesFoodMenu.equals("")) {
            System.out.println("There is no food in the menu.");
            return 0;
        }

        String[] foodMenuPerLine = linesFoodMenu.split("\n");

        int i = 0;
        // format into table
        for (int j = 0; j < 70; j++) {
            System.out.print("-");
        }
        System.out.println();

        System.out.printf("|%-5s|%-20s|%-20s|%-20s|\n", "No.", "Food Name", "Price (RM)", "Net Weight (gram)");

        for (int j = 0; j < 70; j++) {
            System.out.print("-");
        }

        System.out.println();

        for (i = 0; i < foodMenuPerLine.length; i++) {
            String[] foodDetails = foodMenuPerLine[i].split(",");
            String foodName = foodDetails[0];
            double price = Double.parseDouble(foodDetails[1]);
            double netWeight = Double.parseDouble(foodDetails[2]);

            System.out.printf("|%-5d|%-20s|%-20s|%-20s|\n", (i + 1), foodName, String.format("%,.2f", price),
                    String.format("%,.2f", netWeight));
        }

        for (int j = 0; j < 70; j++) {
            System.out.print("-"); // print 80 dashes
        }

        System.out.println();

        return i;
    }

    // method to count the number of menu in the food menu
    public int countMenu() throws IOException {
        FileHandling foodMenu = new FileHandling("foodMenu.txt");
        String linesFoodMenu = foodMenu.read();

        if (linesFoodMenu.equals("")) {
            return 0;
        }

        String[] foodMenuPerLine = linesFoodMenu.split("\n");

        return foodMenuPerLine.length;
    }

    // printer
    public String toString() {
        // format the date to dd/mm/yyyy
        return "Food Name: " + foodName + "\nQuantity: "
                + quantity + "\nPrice: RM " + String.format("%,.2f", price)
                + "\nNet Weight: " + String.format("%,.2f", netWeight) + " gram\n";
    }

}
