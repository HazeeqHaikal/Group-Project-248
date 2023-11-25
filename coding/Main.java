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

        // close the scanner
        strInput.close();
        intInput.close();

    }
}