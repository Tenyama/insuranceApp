package classes.fileManip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaxIdFinder {
    public static int getMaxCustomerId(String filePath) throws IOException {
        Pattern pattern = null;
        if(filePath.contains("customers.txt")){
            pattern = Pattern.compile("id=c-(\\d+)");
        } else if (filePath.contains("claims.txt")) {
            pattern = Pattern.compile("id=f-(\\d+)");
        }
        int maxId = Integer.MIN_VALUE; // Initialize maxId with the smallest possible integer value

        // Read lines from the file
        for (String line : Files.readAllLines(Paths.get(filePath))) {
            String id = extractId(line, pattern);
            if (id != null) {
                int idValue = Integer.parseInt(id);
                if (idValue > maxId) {
                    maxId = idValue;
                }
            }
        }
        if (maxId == Integer.MIN_VALUE){
            return 0;
        } else {
            return maxId;
        }
    }

    private static String extractId(String line, Pattern pattern) {
        // Define the pattern for matching IDs
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            // Extract and return the ID part
            return matcher.group(1);
        }
        return null; // Return null if no match found
    }
}