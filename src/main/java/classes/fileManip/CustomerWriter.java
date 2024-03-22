package classes.fileManip;

import classes.Dependent;
import classes.PolicyHolder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CustomerWriter {
    public static void createCustomer(PolicyHolder dummy) {
        String content = dummy.toString();

        // Read existing content from the file
        StringBuilder existingContent = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/docs/customers.txt"));
            for (String line : lines) {
                existingContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            // Handle the case when the file doesn't exist or cannot be read
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Append new content to the existing content
        existingContent.append(content).append(System.lineSeparator());

        try {
            // Specify the file path directly
            String filePath = "src/main/java/docs/customers.txt";
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
    public static void createCustomer(Dependent dummy) {
        String content = dummy.toString();

        // Read existing content from the file
        StringBuilder existingContent = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/docs/customers.txt"));
            for (String line : lines) {
                existingContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            // Handle the case when the file doesn't exist or cannot be read
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Append new content to the existing content
        existingContent.append(content).append(System.lineSeparator());

        try {
            // Specify the file path directly
            String filePath = "src/main/java/docs/customers.txt";
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
