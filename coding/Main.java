import java.util.*;
import java.io.*;

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

        // String adminType = "admin";
        // String adminType = "user";

        // LinkedList to store Food objects
        LinkedList<Food> foodList = new LinkedList<Food>();
        // queue to store Food objects
        Queue<Food> foodQueue = new LinkedList<Food>();
        Queue<String> foodQueueString = new LinkedList<String>();
        // custom linkedlist to store Food objects
        LinkedListCustom foodListCustom = new LinkedListCustom();

        // welcome message
        System.out.println("Welcome to the Food Inventory System");
        System.out.println("====================================");

        int choice = 0;

        // while loop to keep the program running
        // while (choice != 3) {
        System.out.print("1. Login\n2. Register\n\nEnter your choice: ");
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

            System.out.println("Welcome, " + name);

            while (true) {
                if (userType.equalsIgnoreCase("admin")) {
                    // admin can update the food order list for the order queue
                    // which mean it will delete the last food object in the queue
                    // food order list will be stored in a text file
                    // and add it to the finishedOrder.txt file
                    System.out
                            .print("\n1. Update Food Order List\n2. View Food Order List\n3. Exit\n\nEnter your choice: ");
                    int adminChoice = intInput.nextInt();

                    // update food order list
                    if (adminChoice == 1) {
                        // read the food order list from the text file

                        FileHandling foodOrder = new FileHandling("foodOrder.txt");
                        String linesFoodOrder = foodOrder.read();
                        String[] foodOrderPerLine = linesFoodOrder.split("\n");

                        if (foodOrderPerLine.length == 0) {
                            System.out.println("There is no food order list.");
                            continue;
                        }

                        for (int i = 0; i < foodOrderPerLine.length; i++) {
                            String[] foodDetails = foodOrderPerLine[i].split(",");
                            String userID = foodDetails[0];
                            String orderID = foodDetails[1];

                            // store into queue
                            foodQueueString.add(userID + "," + orderID);
                        }

                        // display the food order list
                        System.out.printf("%-20s%-20s\n", "User ID", "Order ID");

                        Queue<String> temporary = new LinkedList<String>();

                        while (!foodQueueString.isEmpty()) {
                            String formattedString = foodQueueString.remove();
                            String[] foodDetails = formattedString.split(",");
                            String userID = foodDetails[0];
                            String orderID = foodDetails[1];

                            System.out.printf("%-20s%-20s\n", userID, orderID);

                            temporary.add(userID + "," + orderID);
                        }

                        // restore the queue
                        while (!temporary.isEmpty()) {
                            foodQueueString.add(temporary.remove());
                        }

                        // clear temporary queue
                        temporary.clear();

                        // delete the last food object in the queue
                        String finishedOrder = foodQueueString.remove();

                        // store the finished order into finishedOrder.txt
                        FileHandling finishedOrderFile = new FileHandling("finishedOrder.txt");
                        finishedOrderFile.write(finishedOrder);
                        finishedOrderFile.close();

                        // show the updated food order list
                        System.out.println("\nUpdated Food Order List");
                        System.out.printf("%-20s%-20s\n", "User ID", "Order ID");

                        while (!foodQueueString.isEmpty()) {
                            String formattedString = foodQueueString.remove();
                            String[] foodDetails = formattedString.split(",");
                            String userID = foodDetails[0];
                            String orderID = foodDetails[1];

                            System.out.printf("%-20s%-20s\n", userID, orderID);
                        }

                    } else if (adminChoice == 2) {
                        // view food order list
                        // read the food order list from the text file
                        FileHandling foodOrder = new FileHandling("foodOrder.txt");
                        String linesFoodOrder = foodOrder.read();
                        String[] foodOrderPerLine = linesFoodOrder.split("\n");

                        if (foodOrderPerLine.length == 0) {
                            System.out.println("There is no food order list.");
                            continue;
                        }

                        for (int i = 0; i < foodOrderPerLine.length; i++) {
                            String[] foodDetails = foodOrderPerLine[i].split(",");
                            String userID = foodDetails[0];
                            String orderID = foodDetails[1];

                            // store into queue
                            foodQueueString.add(userID + "," + orderID);
                        }

                        // display the food order list
                        System.out.printf("\n%-20s%-20s\n", "User ID", "Order ID");

                        Queue<String> temporary = new LinkedList<String>();

                        while (!foodQueueString.isEmpty()) {
                            String formattedString = foodQueueString.remove();
                            String[] foodDetails = formattedString.split(",");
                            String userID = foodDetails[0];
                            String orderID = foodDetails[1];

                            System.out.printf("%-20s%-20s\n", userID, orderID);

                            temporary.add(userID + "," + orderID);
                        }

                        // restore the queue
                        foodQueueString = temporary;

                        // clear temporary queue
                        temporary.clear();

                    } else if (adminChoice == 3) {
                        System.out.println("Thank you for using the Food Inventory System.");
                        break;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }

                } else {

                    char order = 'Y';

                    while (order == 'Y') {
                        // show the menu
                        System.out.printf("%-20s%-20s%-20s\n", "Food", "Price per Quantity", "Net Weight");
                        System.out.printf("%-20s%-20s%-20s\n", "1. Nasi Lemak", "RM 3.50", "400g");
                        System.out.printf("%-20s%-20s%-20s\n", "2. Satay", "RM 1.10", "150g");
                        System.out.printf("%-20s%-20s%-20s\n", "3. Laksa", "RM 6.00", "350g");
                        System.out.printf("%-20s%-20s%-20s\n", "4. Mee Goreng", "RM 5.00", "450g");
                        System.out.printf("%-20s%-20s%-20s\n", "5. Roti Canai", "RM 1.50", "200g");
                        System.out.printf("%-20s%-20s%-20s\n", "6. Nasi Ayam", "RM 6.50", "400g");
                        System.out.printf("%-20s%-20s%-20s\n", "7. Nasi Kerabu", "RM 7.00", "400g");
                        System.out.printf("%-20s%-20s%-20s\n", "8. Mee Udang", "RM 8.50", "450g");
                        System.out.printf("%-20s%-20s%-20s\n", "9. Nasi Dagang", "RM 7.50", "350g");
                        System.out.printf("%-20s%-20s%-20s\n", "10. Rojak", "RM 6.00", "250g");

                        // ask the user to enter the food they want to order
                        System.out.print("\nEnter the food you want to order: ");
                        int foodChoice = intInput.nextInt();

                        System.out.print("Enter the quantity: ");
                        int quantity = intInput.nextInt();

                        double price = 0;
                        double netWeight = 0;

                        // get the current date and time in seconds
                        Date currentDate = new Date();
                        long currentTime = currentDate.getTime();

                        Date expiryDate = new Date();

                        // set expiration date based on the food choice

                        // nasi lemak - 12 hours
                        // satay - 6 hours
                        // laksa - 24 hours
                        // mee goreng - 2 days
                        // roti canai - 8 hours
                        // nasi ayam - 12 hours
                        // nasi kerabu - 12 hours
                        // mee udang - 24 hours
                        // nasi dagang - 12 hours
                        // rojak - 2 hours

                        String foodName = "";

                        if (foodChoice == 1 || foodChoice == 6 || foodChoice == 7 || foodChoice == 9) {
                            if (foodChoice == 1) {
                                foodName = "Nasi Lemak";
                                price = 3.50;
                                netWeight = 400;
                            } else if (foodChoice == 6) {
                                foodName = "Nasi Ayam";
                                price = 6.50;
                                netWeight = 400;
                            } else if (foodChoice == 7) {
                                foodName = "Nasi Kerabu";
                                price = 7.00;
                                netWeight = 400;
                            } else if (foodChoice == 9) {
                                foodName = "Nasi Dagang";
                                price = 7.50;
                                netWeight = 350;
                            }
                            // 43200000 milliseconds = 12 hours
                            expiryDate.setTime(currentTime + (hour * 12));
                        } else if (foodChoice == 2) {
                            foodName = "Satay";
                            price = 1.10;
                            netWeight = 150;
                            // 21600000 miliseconds = 6 hours
                            expiryDate.setTime(currentTime + (hour * 6));
                        } else if (foodChoice == 3 || foodChoice == 8) {
                            if (foodChoice == 3) {
                                foodName = "Laksa";
                                price = 6.00;
                                netWeight = 350;
                            } else if (foodChoice == 8) {
                                foodName = "Mee Udang";
                                price = 8.50;
                                netWeight = 450;
                            }
                            // 86400000 miliseconds = 24 hours
                            expiryDate.setTime(currentTime + (hour * 24));
                        } else if (foodChoice == 4) {
                            foodName = "Mee Goreng";
                            price = 5.00;
                            netWeight = 450;
                            // 172800000 miliseconds = 48 hours
                            expiryDate.setTime(currentTime + (hour * 48));
                        } else if (foodChoice == 5) {
                            foodName = "Roti Canai";
                            price = 1.50;
                            netWeight = 200;
                            // 28800000 miliseconds = 8 hours
                            expiryDate.setTime(currentTime + (hour * 8));
                        } else if (foodChoice == 10) {
                            foodName = "Rojak";
                            price = 6.00;
                            netWeight = 250;
                            // 7200000 milliseconds = 2 hours
                            expiryDate.setTime(currentTime + (hour * 2));
                        }

                        price = price * quantity;

                        Food food = new Food(foodName, quantity, price, expiryDate, netWeight);

                        // store the food object into the queue
                        foodQueue.add(food);

                        System.out.println("Food added to the order list.");
                        System.out.print("Do you want to order more food (Y/N): ");
                        order = strInput.nextLine().charAt(0);
                        order = Character.toUpperCase(order);
                    }

                    // take from food queue and store into foodOrder.txt
                    FileHandling foodOrder = new FileHandling("foodOrder.txt");

                    while (!foodQueue.isEmpty()) {
                        Food food = foodQueue.remove();
                        String foodName = food.getFoodName();
                        int quantity = food.getQuantity();
                        double price = food.getPrice();
                        Date expiryDate = food.getExpiryDate();
                        double netWeight = food.getNetWeight();

                        // generate a string of random numbers and letters for the order ID
                        String orderID = food.generateFoodID();
                        String userID = login.getUserID();

                        // store the food object into the queue
                        foodOrder.write(userID + "," + orderID + "," + foodName + "," + quantity + "," + price + ","
                                + expiryDate + "," + netWeight);
                    }

                    // close the file
                    foodOrder.close();

                }
            }

        }

        // register a new account
        else if (choice == 2) {
            System.out.print("Enter your name: ");
            String name = strInput.nextLine();

            String password, passwordc = "";

            Account account = new Account(name, passwordc);

            // validate the password to meet the requirements
            while (!account.checkingStrength()) {
                System.out.print("Enter your password (at least 8 characters): ");
                password = strInput.nextLine();

                System.out.print("Confirm your password: ");
                passwordc = strInput.nextLine();

                // check if the password is the same as the confirmed password
                while (!passwordc.equals(password)) {
                    System.out.println("Password does not match.");
                    System.out.print("Confirm your password: ");
                    passwordc = strInput.nextLine();
                }

                account.setPassword(passwordc);

                // check if the password is strong
                if (!account.checkingStrength()) {
                    System.out.println("Password is not strong enough.");
                }

                // check if the password contains comma, if so, ask the user to enter again
                if (passwordc.contains(",")) {
                    System.out.println("Password cannot contain comma.");
                }
            }

            // birthday
            System.out.print("Enter your birthday (DD/MM/YYYY): ");
            String birthdate = strInput.nextLine();
            // validate the birthdate and make sure it is in the correct format, loop until
            // it is correct
            while (!birthdate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.print("Invalid birthdate. Please enter your birthday (DD/MM/YYYY): ");
                birthdate = strInput.nextLine();
            }

            boolean isMember = false;

            while (isMember == false) {

                System.out.print("Do you want to register for membership (Y/N) : ");
                char membership = strInput.nextLine().charAt(0);

                membership = Character.toUpperCase(membership);

                if (membership == 'Y') {
                    isMember = true;
                } else if (membership == 'N') {
                    isMember = false;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }

            }

            Account register = new Account(name, passwordc, birthdate, isMember);

            String userID = register.generatingUserID();

            // check if the registration is successful
            if (register.registering()) {
                System.out.println("Registration successful.");
                // save to text file data.txt
                FileHandling data = new FileHandling("data.txt");
                data.write(userID + "," + name + "," + passwordc + "," + birthdate + "," + isMember + ",user");
                data.close();

            } else {
                System.out.println("Registration failed.");
            }
        } else if (choice == 3) {
            System.out.println("Thank you for using the Food Inventory System.");
        } else {
            System.out.println("Invalid input. Please try again.");
        }
        // }

        // close the scanner
        strInput.close();
        intInput.close();

    }
}