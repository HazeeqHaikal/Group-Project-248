import java.io.*;
import java.util.Scanner;

public class Account extends FileHandling {
    String username;
    String password;
    String birthdate;
    boolean isMember;
    static FileHandling data = new FileHandling();

    // default constructor
    public Account() {
        super();
        this.username = "";
        this.password = "";
        this.birthdate = "";
        this.isMember = false;
    }

    // this is for registration
    public Account(String username, String password, String birthdate, boolean isMember) throws IOException {
        super("data.txt");
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.isMember = isMember;
    }

    // this is for login
    public Account(String username, String password) throws IOException {
        super("data.txt");
        this.username = username;
        this.password = password;
        this.birthdate = "";
        this.isMember = false;
    }

    // getter and setter
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isMember() {
        return this.isMember;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }

    public boolean verifying() throws IOException {
        return verify(this.username, this.password);
    }

    public boolean checkingStrength() {
        return checkStrength(this.password);
    }

    public String generatingUserID() {
        return generateUserID();
    }

    public String getUserID() throws IOException {
        FileHandling data = new FileHandling("data.txt");
        String[] lines = data.read().split("\n");
        for (String line : lines) {
            // split the line into an array
            String[] arr = line.split(",");
            String user = arr[1];
            if (user.equals(this.username)) {
                return arr[0];
            }
        }

        return "";
    }

    // method to register a new account
    public void registers(String accountType) throws IOException {

        Scanner strInput = new Scanner(System.in);
        Scanner intInput = new Scanner(System.in);

        System.out.print("Please enter your username: ");
        String username = strInput.nextLine();

        System.out.print("Please enter your password: ");
        String password = strInput.nextLine();

        System.out.print("Confirm your password: ");
        String confirmPassword = strInput.nextLine();

        // check if the password matches
        while (!password.equals(confirmPassword) || !checkStrength(password)) {

            if (!checkStrength(password)) {
                System.out.println("Your password is not strong enough. Please try again.");
            } else {
                System.out.println("Your password does not match. Please try again.");
            }

            System.out.print("Please enter your password: ");
            password = strInput.nextLine();

            System.out.print("Confirm your password: ");
            confirmPassword = strInput.nextLine();
        }

        System.out.print("Please enter your birthdate (dd/mm/yyyy): ");
        String birthdate = strInput.nextLine();

        System.out.print("Are you a member? (Y/N): ");
        char member = intInput.next().charAt(0);
        boolean isMember = false;
        member = Character.toUpperCase(member);

        if (member == 'Y') {
            isMember = true;
        }

        // check if the username already exists
        data = new FileHandling("data.txt");
        String[] lines = data.read().split("\n");

        // check if null
        if (lines[0].equals("")) {
            System.out.println("You have successfully registered.");
        }

        for (String line : lines) {
            // split the line into an array
            String[] arr = line.split(",");
            String user = arr[1];

            // check if the username already exists
            if (user.equals(username)) {
                // close the input stream
                data.close();
                System.out.println("The username already exists. Please try again.");
                return;
            }
        }

        String userID = generateUserID();

        // write to file
        if (accountType.equals("admin")) {
            data.write(userID + "," + username + "," + password + "," + birthdate + "," + isMember + ",staff");
        } else if (accountType.equals("user")) {
            data.write(userID + "," + username + "," + password + "," + birthdate + "," + isMember + ",user");
        }

        data.close();

        System.out.println("You have successfully registered.");

        strInput.close();
        intInput.close();
    }

    // method to verify the username and password exists
    public static boolean verify(String username, String password) throws IOException {

        // read the file line by line
        data = new FileHandling("data.txt");
        String[] lines = data.read().split("\n");
        for (String line : lines) {
            // split the line into an array
            String[] arr = line.split(",");
            String user = arr[1];
            String pass = arr[2];

            // check if the username and password matches
            if (user.equals(username) && pass.equals(password)) {
                // close the input stream
                data.close();
                return true;
            }
        }

        return false;
    }

    // method to check strength of password
    public static boolean checkStrength(String password) {
        // check if the password is at least 8 characters long
        if (password.length() < 8) {
            return false;
        }

        // check if the password contains at least one uppercase letter
        boolean hasUppercase = !password.equals(password.toLowerCase());
        if (!hasUppercase) {
            return false;
        }

        // check if the password contains at least one lowercase letter
        boolean hasLowercase = !password.equals(password.toUpperCase());
        if (!hasLowercase) {
            return false;
        }

        // check if the password contains at least one number
        boolean hasNumber = password.matches(".*\\d.*");
        if (!hasNumber) {
            return false;
        }

        // check if the password contains at least one special character
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
        if (!hasSpecial) {
            return false;
        }

        return true;
    }

    // generate random string for userID
    public static String generateUserID() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public void viewOrder(String userID, boolean finished) throws IOException {
        FileHandling foodOrder = new FileHandling("foodOrder.txt");
        String[] linesFoodOrder = foodOrder.readLines();

        // use circular linked list
        Circular foodOrderLL = new Circular();

        for (String line : linesFoodOrder) {
            String[] arr = line.split(",");
            String userIDFoodOrder = arr[0];
            String foodName = arr[2];
            int quantity = Integer.parseInt(arr[3]);
            double price = Double.parseDouble(arr[4]);
            double netWeight = Double.parseDouble(arr[5]);
            boolean isFinished = Boolean.parseBoolean(arr[6]);

            if (userIDFoodOrder.equals(userID) && isFinished == finished) {
                Food food = new Food(foodName, price, netWeight);
                food.setQuantity(quantity);
                foodOrderLL.add(food);
            }
        }

        // check if the list is empty
        if (foodOrderLL.isEmpty() && finished) {
            System.out.println("There is no finished order.");
            return;
        } else if (foodOrderLL.isEmpty() && !finished) {
            System.out.println("There is no unfinished order.");
            return;
        }

        for (int i = 0; i < 106; i++) {
            System.out.print("-");
        }

        System.out.println();

        System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|\n", "Food Name", "Quantity", "Price", "Net Weight",
                "Total Price");

        for (int i = 0; i < 106; i++) {
            System.out.print("-");
        }

        System.out.println();

        while (!foodOrderLL.isEmpty()) {
            Food food = (Food) foodOrderLL.removeFromFront();
            System.out.printf("|%-20s|%-20d|%-20.2f|%-20.2f|%-20.2f|\n", food.getFoodName(), food.getQuantity(),
                    food.getPrice(), food.getNetWeight(), food.getPrice() * food.getQuantity());
        }

        for (int i = 0; i < 106; i++) {
            System.out.print("-");
        }

        System.out.println("\n");

    }

    // calculate total price of finished order
    public double calculateTotalPrice(String userID) throws IOException {
        FileHandling foodOrder = new FileHandling("foodOrder.txt");
        String[] linesFoodOrder = foodOrder.readLines();

        double totalPrice = 0;

        for (String line : linesFoodOrder) {
            String[] arr = line.split(",");
            String userIDFoodOrder = arr[0];
            String foodName = arr[2];
            int quantity = Integer.parseInt(arr[3]);
            double price = Double.parseDouble(arr[4]);
            double netWeight = Double.parseDouble(arr[5]);
            boolean isFinished = Boolean.parseBoolean(arr[6]);

            if (userIDFoodOrder.equals(userID) && isFinished) {
                Food food = new Food(foodName, price, netWeight);
                food.setQuantity(quantity);
                totalPrice += food.getPrice() * food.getQuantity();
            }
        }

        return totalPrice;
    }

    // toString
    public String toString() {
        return "Username: " + username + "\nPassword: " + password + "\nBirthdate: " + birthdate + "\nIs Member: "
                + isMember + "\n";
    }
}
