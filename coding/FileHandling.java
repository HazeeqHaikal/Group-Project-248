import java.io.*;

public class FileHandling {
    private BufferedReader reader;
    private PrintWriter writer;

    public FileHandling() {
    }

    public FileHandling(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
        writer = new PrintWriter(new FileWriter(fileName, true));
    }

    public void setFile(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
        writer = new PrintWriter(new FileWriter(fileName, true));
    }

    public void write(String text) {
        writer.println(text);
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
    }

    // read all lines
    public String read() throws IOException {
        String text = "";
        String line = reader.readLine();
        while (line != null) {
            text += line + "\n";
            line = reader.readLine();
        }

        return text;
    }

    public String[] readLines() throws IOException {
        String[] lines = read().split("\n");
        return lines;
    }

    public void emptyFiles() throws IOException {
        String text = read();
        if (text.equals("")) {
            System.out.println("The file is empty.");
        }
    }

    // clear the content of the file
    public void clear(String fileName) throws IOException {
        writer = new PrintWriter(new FileWriter(fileName));
        writer.print("");
    }

}