import java.io.*;

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

    // method to verify the username and password exists
    public static boolean verify(String username, String password) throws IOException {

        // read the file line by line
        String[] lines = data.read().split("\n");
        for (String line : lines) {
            // split the line into an array
            String[] arr = line.split(",");
            String user = arr[0];
            String pass = arr[1];

            // check if the username and password matches
            if (user.equals(username) && pass.equals(password)) {
                // close the input stream
                data.close();
                return true;
            }
        }

        return false;
    }

    // method to register a new account
    public static boolean register(String username, String password, String birthdate, boolean isMember)
            throws IOException {

        // check if the username already exists
        String[] lines = data.read().split("\n");
        for (String line : lines) {
            // split the line into an array
            String[] arr = line.split(",");
            String user = arr[0];

            // check if the username already exists
            if (user.equals(username)) {
                // close the input stream
                data.close();
                return false;
            }
        }

        // write to file
        data.write(username + "," + password + "," + birthdate + "," + isMember);

        return true;
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

    // toString
    public String toString() {
        return "Username: " + username + "\nPassword: " + password + "\nBirthdate: " + birthdate + "\nIs Member: "
                + isMember + "\n";
    }
}
