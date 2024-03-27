package classes.fileManip;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class ObjectWriter {
    public abstract String getObjectAsString();

    protected static String filePath;

    public static void writeObject(ObjectWriter objectWriter) {
        // Read existing content from the file
        StringBuilder existingContent = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                existingContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            // Handle the case when the file doesn't exist or cannot be read
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Append new content to the existing content
        existingContent.append(objectWriter.getObjectAsString()).append(System.lineSeparator());

        try {
            // Create a FileWriter object with the specified file path
            FileWriter myWriter = new FileWriter(filePath);
            BufferedWriter out = new BufferedWriter(myWriter);

            // Write to the file
            out.write(existingContent.toString());
            // Close the writer
            out.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
