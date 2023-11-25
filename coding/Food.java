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

}
