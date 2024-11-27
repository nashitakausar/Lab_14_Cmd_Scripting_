import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class FileScan {
    public static void main (String[] args) {
        File file;

        if (args.length > 0) {
            file = new File(args[0]);
            System.out.println("Looking for file at: " + file.getAbsolutePath());
            if (!file.exists()) {
                System.out.println("File not found: " + file.getAbsolutePath());
                return;
            }
        } else {
            JFileChooser fileChooser = new JFileChooser("src");
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file has been selected, exiting program.");
                return;
            }
        }

        try {
            processFile(file);
        } catch (IOException e) {
            System.out.println("Error trying to read the file: " + e.getMessage());
        }
    }

    private static void processFile(File file) throws IOException {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (Scanner scanner = new Scanner(file)) {
            System.out.println("File content: ");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);

                lineCount++;

                String[] words = line.trim().split("\\s+");
                wordCount += words.length;
                charCount += line.length();
            }
        }

        System.out.println("\n--> Summary Report <--");
        System.out.println("Name of file: " + file.getName());
        System.out.println("Number of lines: " + lineCount);
        System.out.println("Number of words: " + wordCount);
        System.out.println("Number of characters: " + charCount);
    }
}