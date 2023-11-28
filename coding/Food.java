import java.util.Date;

public class Food {
    private String foodName;
    private int quantity;
    private double price;
    private Date expiryDate;
    private double netWeight;

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
