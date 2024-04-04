package classes.fileManip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileLineRemover {
    public static void removeLineById(String filePath, String idToRemove) {
        try {
            File inputFile = new File(filePath);
            File tempFile = new File("src/main/java/docs/temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // Check if the current line contains the ID to remove
                if (currentLine.contains(idToRemove)) {
                    continue; // Skip this line
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.err.println("Failed to delete the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Failed to rename the temporary file.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Usage example
        removeLineById("src/main/java/docs/claims.txt", "f-0000000002");
    }
}
