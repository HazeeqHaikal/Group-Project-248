import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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

    // put food into linked list custom
    // change the last food to be finished
    // write back to foodOrder.txt
    public void updateFood() throws IOException {
        FileHandling foodOrder = new FileHandling("foodOrder.txt");
        String linesFoodOrder = foodOrder.read();

        if (linesFoodOrder.equals("")) {
            System.out.println("There is no food order.");
            return;
        }

        String[] foodOrderPerLine = linesFoodOrder.split("\n");

        Circular foodOrderLL = new Circular();

        // check if all food is finished
        boolean isAllFinished = true;

        for (int i = 0; i < foodOrderPerLine.length; i++) {
            String[] foodOrderDetails = foodOrderPerLine[i].split(",");
            String userID = foodOrderDetails[0];
            String orderID = foodOrderDetails[1];
            String foodName = foodOrderDetails[2];
            int quantity = Integer.parseInt(foodOrderDetails[3]);
            double price = Double.parseDouble(foodOrderDetails[4]);
            double netWeight = Double.parseDouble(foodOrderDetails[5]);
            boolean isFinished = Boolean.parseBoolean(foodOrderDetails[6]);

            if (!isFinished) {
                isAllFinished = false;
            }

            Food food = new Food(userID, orderID, foodName, quantity, price, netWeight, isFinished);

            foodOrderLL.insertAtBack(food);
        }

        if (isAllFinished) {
            System.out.println("All food is finished.");
            return;
        }

        // change the last food to be finished that is not finished
        // traverse from the back
        for (int i = foodOrderLL.getSize() - 1; i >= 0; i--) {
            Food food = (Food) foodOrderLL.get(i);

            if (!food.getIsFinished()) {
                food.setIsFinished(true);
                break;
            }
        }
        // Food food = (Food) foodOrderLL.removeFromBack();
        // food.setIsFinished(true);
        // foodOrderLL.insertAtFront(food);

        // write back to foodOrder.txt
        foodOrder.setFile("foodOrder.txt");

        // clear the file
        foodOrder.clear("foodOrder.txt");

        // sort the food by finished and unfinished
        // the order of the unfinished must stay the same
        // push down the finished food to the bottom
        for (int i = 0; i < foodOrderLL.getSize(); i++) {
            for (int j = i + 1; j < foodOrderLL.getSize(); j++) {
                Food food1 = (Food) foodOrderLL.get(i);
                Food food2 = (Food) foodOrderLL.get(j);

                if (food1.getIsFinished() && !food2.getIsFinished()) {
                    foodOrderLL.swap(i, j);
                }
            }
        }

        while (!foodOrderLL.isEmpty()) {
            Food food2 = (Food) foodOrderLL.removeFromFront();
            foodOrder.write(food2.getUserID() + "," + food2.getOrderID() + "," + food2.getFoodName() + "," +
                    food2.getQuantity() + "," + String.format("%,.2f", food2.getPrice()) + "," +
                    food2.getNetWeight() + "," + food2.getIsFinished());
        }

        foodOrder.close();

    }

    // view finished order in finishedOrder.txt
    public void viewFinishedOrder() throws Exception {
        FileHandling finishedOrder = new FileHandling("foodOrder.txt");
        String linesFinishedOrder = finishedOrder.read();

        // use circular linked list to store finished order
        // CircularLinkedList finishedOrderCLL = new CircularLinkedList();
        Circular finishedOrderCLL = new Circular();

        if (linesFinishedOrder.equals("")) {
            System.out.println("There is no finished order.");
            return;
        }

        String[] finishedOrderPerLine = linesFinishedOrder.split("\n");

        for (int i = 0; i < finishedOrderPerLine.length; i++) {
            String[] finishedOrderDetails = finishedOrderPerLine[i].split(",");
            String userID = finishedOrderDetails[0];
            String orderID = finishedOrderDetails[1];
            String foodName = finishedOrderDetails[2];
            int quantity = Integer.parseInt(finishedOrderDetails[3]);
            double price = Double.parseDouble(finishedOrderDetails[4]);
            double netWeight = Double.parseDouble(finishedOrderDetails[5]);
            boolean isFinished = Boolean.parseBoolean(finishedOrderDetails[6]);

            if (isFinished) {
                Food food = new Food(userID, orderID, foodName, quantity, price, netWeight, isFinished);
                finishedOrderCLL.insertAtBack(food);
            }
        }
    }

    // add new food
    public void addFood(Food food) throws Exception {
        FileHandling foodMenu = new FileHandling("foodMenu.txt");
        // String linesFoodMenu = foodMenu.read();
        String foodName = food.getFoodName();
        double price = food.getPrice();
        double netWeight = food.getNetWeight();
        foodMenu.write(foodName + "," + String.format("%,.2f", price) + "," + netWeight);
        foodMenu.close();
    }

    // remove food
    // 1 - remove food from front
    // 2 - remove food at the end
    // 3 - remove food from middle
    // 4 - remove all food
    public void removeFood(int choice) throws Exception {

        FileHandling foodMenu = new FileHandling("foodMenu.txt");
        String linesFoodMenu = foodMenu.read();

        Circular foodMenuLL = new Circular();

        if (linesFoodMenu.equals("")) {
            System.out.println("There is no food in the menu.");
            return;
        }

        String[] foodMenuPerLine = linesFoodMenu.split("\n");

        for (int i = 0; i < foodMenuPerLine.length; i++) {
            String[] foodDetails = foodMenuPerLine[i].split(",");
            String foodName = foodDetails[0];
            double price = Double.parseDouble(foodDetails[1]);
            double netWeight = Double.parseDouble(foodDetails[2]);

            Food food = new Food(foodName, price, netWeight);

            foodMenuLL.insertAtBack(food);
        }

        Scanner intInput = new Scanner(System.in);

        switch (choice) {
            case 1:
                foodMenuLL.removeFromFront();
                break;
            case 2:
                foodMenuLL.removeFromBack();
                break;
            case 3:
                foodMenuLL.removeFromMiddle();
                break;
            case 4:
                foodMenuLL.removeAll();
                break;
            case 5:
                System.out.print("Enter the index (1 - " + foodMenuLL.getSize() + "): ");
                choice = intInput.nextInt();
                foodMenuLL.remove(choice - 1);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        // write back to foodMenu.txt
        foodMenu.setFile("foodMenu.txt");

        // clear the file
        foodMenu.clear("foodMenu.txt");

        while (!foodMenuLL.isEmpty()) {
            Food food = (Food) foodMenuLL.removeFromFront();
            foodMenu.write(
                    food.getFoodName() + "," + String.format("%,.2f", food.getPrice()) + "," +
                            food.getNetWeight());
        }

        foodMenu.close();
    }

    // sort food by price
    public void sortFoodByPrice() throws Exception {
        FileHandling foodMenu = new FileHandling("foodMenu.txt");
        String linesFoodMenu = foodMenu.read();

        // LinkedListCustom foodMenuLL = new LinkedListCustom();
        Circular foodMenuLL = new Circular();

        if (linesFoodMenu.equals("")) {
            System.out.println("There is no food in the menu.");
            return;
        }

        String[] foodMenuPerLine = linesFoodMenu.split("\n");

        for (int i = 0; i < foodMenuPerLine.length; i++) {
            String[] foodDetails = foodMenuPerLine[i].split(",");
            String foodName = foodDetails[0];
            double price = Double.parseDouble(foodDetails[1]);
            double netWeight = Double.parseDouble(foodDetails[2]);

            Food food = new Food(foodName, price, netWeight);

            foodMenuLL.insertAtBack(food);
        }

        // sort by price using bubble sort from LinkedListCustom and sort lowest to
        // highest
        for (int i = 0; i < foodMenuLL.getSize(); i++) {
            for (int j = i + 1; j < foodMenuLL.getSize(); j++) {
                Food food1 = (Food) foodMenuLL.get(i);
                Food food2 = (Food) foodMenuLL.get(j);

                if (food1.getPrice() > food2.getPrice()) {
                    foodMenuLL.swap(i, j);
                }
            }
        }

        // write back to foodMenu.txt
        foodMenu.setFile("foodMenu.txt");

        // clear the file
        foodMenu.clear("foodMenu.txt");

        while (!foodMenuLL.isEmpty()) {
            Food food = (Food) foodMenuLL.removeFromFront();
            foodMenu.write(
                    food.getFoodName() + "," + String.format("%,.2f", food.getPrice()) + "," +
                            food.getNetWeight());
        }

        foodMenu.close();

    }

    // toString
    public String toString() {
        return "Username: " + username + "\nPassword: " + password;
    }
}
