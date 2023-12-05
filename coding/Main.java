import java.util.*;
import java.io.*;

// linkedlist must have removal, searching, updating and traversal
// optional: sorting, insertion, merging, reversing

public class Main {
    public static void main(String[] args) {
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

            if (choice == 1) {
                System.out.print("Enter your username: ");
                String username = strInput.nextLine();

                System.out.print("Enter your password: ");
                String password = strInput.nextLine();

                // TODO: validate the username and password from the text file
            } else if (choice == 2) {
                System.out.print("Enter your name: ");
                String name = strInput.nextLine();

                boolean isStrong = false;
                String password, passwordc = "";

                // validate the password to meet the requirements
                while (!isStrong) {
                    System.out.print("Enter your password: ");
                    password = strInput.nextLine();

                    System.out.print("Confirm your password: ");
                    passwordc = strInput.nextLine();

                    // check if the password is the same as the confirmed password
                    if (!passwordc.equals(password)) {
                        System.out.println("Password does not match.");
                    }

                    // check if the password is strong
                    // else if (password.length() < 8) {
                    // System.out.println("Password must be at least 8 characters long.");
                    // }

                    // use regex to check if the password contains at least 1 uppercase, 1
                    // lowercase and 1 digit and 8 characters long
                    else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                        System.out.println(
                                "Password must contain at least 1 uppercase, 1 lowercase, 1 digit and 8 characters long.");
                    }

                    // if all the conditions are met, the password is strong
                    else {
                        isStrong = true;
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

                // save the user's information to the text file
                try {
                    // output to file
                    PrintWriter pw = new PrintWriter(new FileWriter("data.txt", true));

                    // write to file
                    pw.println(name + "," + passwordc + "," + birthdate + "," + isMember);

                    // close the output stream
                    pw.close();
                } catch (IOException e) {
                    System.out.println("File " + e.getMessage() + " not found.");
                } catch (Exception e) {
                    System.out
                            .println("Error: " + e.getMessage() + "\nAt line: " + e.getStackTrace()[0].getLineNumber());
                }
            }

            // initialize the bufferedReader and printWriter
            BufferedReader br;
            PrintWriter pw;
            try {
                // Read from file
                br = new BufferedReader(new FileReader("input.txt"));

                // output to file
                pw = new PrintWriter(new FileWriter("output.txt"));

                // close the input and output stream
                br.close();
                pw.close();
            } catch (IOException e) {
                System.out.println("File " + e.getMessage() + " not found.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\nAt line: " + e.getStackTrace()[0].getLineNumber());
            }
        }

        // close the scanner
        strInput.close();
        intInput.close();

    }
}