import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

// linkedlist must have removal, searching, updating and traversal
// optional: sorting, insertion, merging, reversing

public class Main {

    public static final int ms = 1;
    public static final int sec = ms * 1000;
    public static final int min = sec * 60;
    public static final int hour = min * 60;

    public static void main(String[] args) throws Exception {
        // 2 Scanners for String and Integer
        Scanner strInput = new Scanner(System.in);
        Scanner intInput = new Scanner(System.in);

        // LinkedList to store Food objects
        LinkedList<Food> foodList = new LinkedList<Food>();
        // LinkedListCustom foodListCustom = new LinkedListCustom();
        QueueCustom foodQueueCustom = new QueueCustom();
        Food food = new Food();

        // welcome message
        System.out.println("Welcome to the Food Inventory System");
        System.out.println("====================================");

        int choice = 0;

        // login or register
        System.out.print("1. Login\n2. Register\n3. Exit\n\nEnter your choice: ");
        choice = intInput.nextInt();

        System.out.println();

        // login
        if (choice == 1) {
            System.out.print("Enter your username: ");
            String username = strInput.nextLine();

            System.out.print("Enter your password: ");
            String password = strInput.nextLine();

            Account login = new Account(username, password);

            // validate the username and password from the text file
            if (!login.verifying()) {
                System.out.println("\nLogin failed.");
                return;
            }

            System.out.println("\nLogin successful.\n");

            // check if the user is admin or not
            // get the user type from the text file
            String userType = "";
            String name = "";
            FileHandling data = new FileHandling("data.txt");
            String lines = data.read();
            String[] dataPerLine = lines.split("\n");
            for (int i = 0; i < dataPerLine.length; i++) {
                String[] dataPerComma = dataPerLine[i].split(",");
                name = dataPerComma[1];
                String passwordData = dataPerComma[2];
                userType = dataPerComma[5];

                if (username.equals(name) && password.equals(passwordData)) {
                    break;
                }
            }

            System.out.println("Welcome, " + name + ".\n");

            while (true) {
                if (userType.equalsIgnoreCase("admin")) {
                    Staff staff = new Staff();

                    System.out
                            .print("1. Update Food Order List\n2. View Finished Food Order List\n3. Add Food\n4. View Food Menu\n5. Delete Food Menu\n6. Sort Food Menu By Price\n7. Register New Admin\n8. Exit\n\nEnter your choice: ");
                    int adminChoice = intInput.nextInt();

                    System.out.println();

                    // update food order list
                    if (adminChoice == 1) {
                        staff.updateFood();
                    }

                    else if (adminChoice == 2)
                        staff.viewFinishedOrder();

                    else if (adminChoice == 3) {
                        System.out.print("Enter the food name: ");
                        String foodName = strInput.nextLine();

                        System.out.print("Enter the price (RM): ");
                        double price = intInput.nextDouble();

                        System.out.print("Enter the net weight (gram): ");
                        double netWeight = intInput.nextDouble();

                        food = new Food(foodName, price, netWeight);

                        staff.addFood(food);

                        System.out.println("Food added successfully.\n");

                    }

                    else if (adminChoice == 4) {
                        food.displayFoodMenu();
                    }

                    else if (adminChoice == 5) {
                        System.out.print(
                                "1. Delete from front\n2. Delete from back\n3. Delete from middle\n4. Delete all\n5. Delete by index\n\nEnter your choice: ");
                        int deleteChoice = intInput.nextInt();

                        System.out.println();

                        if (deleteChoice >= 1 && deleteChoice <= 5)
                            staff.removeFood(deleteChoice);
                    }

                    else if (adminChoice == 6) {
                        staff.sortFoodByPrice();
                        System.out.println("Food menu sorted successfully.\n");
                    }

                    else if (adminChoice == 7) {
                        Account register = new Account();
                        register.registers("admin");
                    }

                    else if (adminChoice == 8) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }

                } else {

                    System.out.print(
                            "1. Order food\n2. View finished order\n3. View unfinished order\n4. View total price\n5. Exit\n\nEnter your choice: ");
                    int userChoice = intInput.nextInt();

                    System.out.println();

                    Account account = new Account();

                    if (userChoice == 1) {
                        while (true) {
                            int length = food.countMenu();

                            food.displayFoodMenu();

                            System.out.print("\n\nEnter the food you want to order (1 - " + length + "): ");
                            int foodChoice = intInput.nextInt();

                            if (foodChoice < 1 || foodChoice > length) {
                                System.out.println("\nThere is no food with that number. Please try again.\n");
                                continue;
                            }

                            System.out.print("Enter the quantity: ");
                            int quantity = intInput.nextInt();

                            Food orderedFood = new Food();
                            orderedFood.determineFood(foodChoice, quantity);

                            // store the food object into the queue
                            foodQueueCustom.enqueue(orderedFood);

                            System.out.print("\n1. Order more food\n2. Proceed to checkout\n\nEnter your choice: ");
                            int orderChoice = intInput.nextInt();

                            if (orderChoice == 1) {
                                continue;
                            } else if (orderChoice == 2) {
                                break;
                            } else {
                                System.out.println("Invalid input. Please try again.");
                            }
                        }

                        // take from food queue and store into foodOrder.txt
                        FileHandling foodOrder = new FileHandling("foodOrder.txt");
                        String linesFoodOrder = foodOrder.read();

                        foodOrder.clear("foodOrder.txt");

                        String newOrder = "";

                        // use custom queue
                        while (!foodQueueCustom.isEmpty()) {
                            food = (Food) foodQueueCustom.dequeue();
                            String foodName = food.getFoodName();
                            int quantity = food.getQuantity();
                            double price = food.getPrice();
                            double netWeight = food.getNetWeight();

                            // generate a string of random numbers and letters for the order ID
                            String orderID = food.generateFoodID();
                            String userID = login.getUserID();

                            newOrder += userID + "," + orderID + "," + foodName + "," + quantity + ","
                                    + String.format("%,.2f", price) + "," + netWeight + "," + false + "\n";
                        }

                        newOrder += linesFoodOrder;

                        // trim the last \n to prevent new spaces being created every time new order is
                        // added
                        newOrder = newOrder.substring(0, newOrder.length() - 1);

                        foodOrder.write(newOrder);

                        // close the file
                        foodOrder.close();
                    } else if (userChoice == 2) {
                        account.viewOrder(login.getUserID(), true);

                    } else if (userChoice == 3) {
                        account.viewOrder(login.getUserID(), false);

                    } else if (userChoice == 4) {
                        double totalPrice = account.calculateTotalPrice(login.getUserID());
                        System.out.printf("Total price of finished order: RM %,.2f\n\n", totalPrice);
                    } else if (userChoice == 5) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }

                System.out.println("Press enter to continue...");
                strInput.nextLine();
            } // end of while loop

            System.out.println("Thank you for using the Food Inventory System.");
        }

        // register a new account
        else if (choice == 2) {
            Account register = new Account();
            register.registers("user");
        } else if (choice == 3) {
            System.out.println("Thank you for using the Food Inventory System.");
        } else {
            System.out.println("Invalid input. Please try again.");
        }

        // close the scanner
        strInput.close();
        intInput.close();

    }
}