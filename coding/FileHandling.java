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

    public void write(String text) {
        writer.println(text);
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

    public void close() throws IOException {
        reader.close();
        writer.close();
    }

}