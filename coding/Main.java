import java.util.*;
import java.io.*;

// linkedlist must have removal, searching, updating and traversal
// optional: sorting, insertion, merging, reversing

public class Main {
    public static void main(String[] args) throws Exception {
        // 2 Scanners for String and Integer
        Scanner strInput = new Scanner(System.in);
        Scanner intInput = new Scanner(System.in);

        // LinkedList to store Food objects
        LinkedList<Food> foodList = new LinkedList<Food>();
        // queue to store Food objects
        Queue<Food> foodQueue = new LinkedList<Food>();
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
                if (Account.verify(username, password)) {
                    System.out.println("Login successful.");
                } else {
                    System.out.println("Login failed.");
                }
                // if (verify(username, password)) {
                // System.out.println("Login successful.");
                // }

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

                boolean isRegistered = Account.register(name, passwordc, birthdate, isMember);

                if (isRegistered) {
                    System.out.println("Registration successful.");
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