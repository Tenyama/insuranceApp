package classes.fileManip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNumberGenerator {
    public static long randTen() {
        Set<String> existingNumbers = readExistingNumbers("src/main/java/docs/insuranceCards.txt");
        Random random = new Random();
        long randomNumber;
        boolean exists;
        randomNumber = random.nextInt(1000000000) * 10L + random.nextInt(10); // Generate a random 10-digit number
        exists = existingNumbers.contains(Long.toString(randomNumber));
        if(exists){
            randTen();
        }
        return randomNumber;
    }

    public static Set<String> readExistingNumbers(String fileName) {
        Set<String> existingNumbers = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Assuming each line contains a single number
                existingNumbers.add(parts[0]); // Add the number to the set
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return existingNumbers;
    }
}
