import java.util.*;
import java.io.*;

// linkedlist must have removal, searching, updating and traversal
// optional: sorting, insertion, merging, reversing

public class Main {
    public static void main(String[] args) throws Exception {
        // 2 Scanners for String and Integer
        Scanner strInput = new Scanner(System.in);
        Scanner intInput = new Scanner(System.in);

        String adminType = "admin";

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
        while (choice != 3) {
            System.out.print("1. Login\n2. Register\n3. Exit\nEnter your choice: ");
            choice = intInput.nextInt();

            // login
            if (choice == 1) {
                System.out.print("Enter your username: ");
                String username = strInput.nextLine();

                System.out.print("Enter your password: ");
                String password = strInput.nextLine();

                // validate the username and password from the text file
                if (!Account.verify(username, password)) {
                    System.out.println("Login failed.");
                    break;
                }

                System.out.println("Login successful.");

                // check if the user is admin or not
                if (adminType.equalsIgnoreCase("admin")) {
                    // admin can update the food order list for the order queue
                    // which mean it will delete the last food object in the queue
                    // food order list will be stored in a text file
                    // and add it to the finishedOrder.txt file
                    System.out.println("Welcome Admin");
                    System.out
                            .print("1. Update Food Order List\n2. View Food Order List\n3. Exit\nEnter your choice: ");
                    int adminChoice = intInput.nextInt();

                    // update food order list
                    if (adminChoice == 1) {
                        // read the food order list from the text file
                        BufferedReader br = new BufferedReader(new FileReader("foodOrder.txt"));
                        String line = br.readLine();
                        // read all the food order list from the text file and store it in a queue
                        while (line != null) {
                            String[] foodDetails = line.split(",");
                            String userID = foodDetails[0];
                            String orderID = foodDetails[1];

                            // store into queue
                            foodQueueString.add(userID + "," + orderID);

                            line = br.readLine();
                        }

                        // close the buffered reader
                        br.close();

                        // display the food order list
                        System.out.println("Food Order List");
                        System.out.println("User ID\t\t\tOrder ID");

                        Queue<String> temporary = new LinkedList<String>();

                        while (!foodQueueString.isEmpty()) {
                            String formattedString = foodQueueString.remove();
                            String[] foodDetails = formattedString.split(",");
                            String userID = foodDetails[0];
                            String orderID = foodDetails[1];

                            System.out.println(userID + "\t\t\t" + orderID);

                            temporary.add(userID + "," + orderID);
                        }

                        // restore the queue
                        foodQueueString = temporary;

                        // clear temporary queue
                        temporary.clear();

                        // delete the last food object in the queue
                        String finishedOrder = foodQueueString.remove();

                        // store the finished order into finishedOrder.txt
                        BufferedWriter bw = new BufferedWriter(new FileWriter("finishedOrder.txt", true));
                        bw.write(finishedOrder);

                        // close the buffered writer
                        bw.close();

                        System.out.println("Food order list updated.");

                        // show the updated food order list
                        System.out.println("Updated Food Order List");
                        System.out.println("User ID\t\t\tOrder ID");
                        while (!foodQueueString.isEmpty()) {
                            String formattedString = foodQueueString.remove();
                            String[] foodDetails = formattedString.split(",");
                            String userID = foodDetails[0];
                            String orderID = foodDetails[1];

                            System.out.println(userID + "\t\t\t" + orderID);
                        }

                    }

                } else {
                    System.out.println("Welcome User");
                    // show the menu
                    System.out.println("MENU");
                    System.out.println("Food\t\t\tPrice per Quantity\t\t\tNet Weight");
                    System.out.println("1.Nasi Lemak\t\t\tRM:3.50\t\t\t400g");
                    System.out.println("2.Satay\t\t\tRM:1.10\t\t\t150g");
                    System.out.println("3.Laksa\t\t\tRM:6.00\t\t\t350g");
                    System.out.println("4.Mee Goreng\t\t\tRM:5.00\t\t\t450g");
                    System.out.println("5.Roti Canai\t\t\tRM1.50\t\t\t200g");
                    System.out.println("6.Nasi Ayam\t\t\tRM:6.50t\t\t\t400g");
                    System.out.println("7.Nasi Kerabu\t\t\tRM:7.00\t\t\t400g");
                    System.out.println("8.Mee Udang\t\t\tRM:8.50\t\t\t450g");
                    System.out.println("9.Nasi Dagang\t\t\tRM:7.50\t\t\t350g");
                    System.out.println("10.Rojak\t\t\tRM:6.00\t\t\t250g");

                    // ask the user to enter the food they want to order
                    System.out.print("Enter the food you want to order: ");
                    int foodChoice = intInput.nextInt();

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

                    // making time calculation easier
                    int ms = 1;
                    int sec = 1000 * ms;
                    int min = 60 * sec;
                    int hour = 60 * min;

                    if (foodChoice == 1 || foodChoice == 6 || foodChoice == 7 || foodChoice == 9) {
                        // nasi lemak, nasi ayam, nasi kerabu, nasi dagang
                        // 43200000 milliseconds = 12 hours
                        expiryDate.setTime(currentTime + (hour * 12));
                    } else if (foodChoice == 2) {
                        // satay
                        // 21600000 miliseconds = 6 hours
                        expiryDate.setTime(currentTime + (hour * 6));
                    } else if (foodChoice == 3 || foodChoice == 8) {
                        // laksa, mee udang
                        // 86400000 miliseconds = 24 hours
                        expiryDate.setTime(currentTime + (hour * 24));
                    } else if (foodChoice == 4) {

                        // 172800000 miliseconds = 48 hours
                        expiryDate.setTime(currentTime + (hour * 48));
                    } else if (foodChoice == 5) {
                        // roti canai
                        // 28800000 miliseconds = 8 hours
                        expiryDate.setTime(currentTime + (hour * 8));
                    } else if (foodChoice == 10) {
                        // rojak
                        // 7200000 milliseconds = 2 hours
                        expiryDate.setTime(currentTime + (hour * 2));
                    }

                    // use queue to store the food objects into txt file

                    // generate a string of random numbers and letters for the order ID
                    String orderID = "";
                    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                    int length = 6;

                    // create a random object
                    Random rand = new Random();

                    // generate a string of random numbers and letters for the order ID
                    for (int i = 0; i < length; i++) {
                        orderID += characters.charAt(rand.nextInt(characters.length()));
                    }

                    // store the food object into the queue
                    // foodQueue.add(new Food(foodName, quantity, price, expiryDate, netWeight));

                    // add into the foodOrder.txt file
                    BufferedWriter bw = new BufferedWriter(new FileWriter("foodOrder.txt", true));
                    // bw.write(username + "," + orderID + "\n");

                }

            }

            // register a new account
            else if (choice == 2) {
                System.out.print("Enter your name: ");
                String name = strInput.nextLine();

                // boolean isStrong = false;
                String password, passwordc = "";

                // validate the password to meet the requirements
                while (Account.checkStrength(passwordc) == false) {
                    System.out.print("Enter your password: ");
                    password = strInput.nextLine();

                    System.out.print("Confirm your password: ");
                    passwordc = strInput.nextLine();

                    // check if the password is the same as the confirmed password
                    while (!passwordc.equals(password)) {
                        System.out.println("Password does not match.");
                        System.out.print("Confirm your password: ");
                        passwordc = strInput.nextLine();
                    }

                    // check if the password is strong
                    if (Account.checkStrength(passwordc) == false) {
                        System.out.println("Password is not strong enough.");
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

                // generate a string of random numbers and letters for the user ID
                String userID = "";
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                int length = 6;

                // create a random object
                Random rand = new Random();

                // generate a string of random numbers and letters for the user ID
                for (int i = 0; i < length; i++) {
                    userID += characters.charAt(rand.nextInt(characters.length()));
                }

                boolean isRegistered = Account.register(name, passwordc, birthdate, isMember);

                if (isRegistered) {
                    System.out.println("Registration successful.");
                    // save to text file data.txt
                    BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", true));
                    bw.write(userID + "," + name + "," + passwordc + "," + birthdate + "," + isMember + "\n");
                } else {
                    System.out.println("Registration failed.");
                }
            } else if (choice == 3) {
                System.out.println("Thank you for using the Food Inventory System.");
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        // close the scanner
        strInput.close();
        intInput.close();

    }
}