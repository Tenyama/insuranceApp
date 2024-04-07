package classes.fileManip;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;


public class Reader {
    public static List<String[]> readFile(int type) throws IOException {
        String filePath = "";
        int keyNums = 0;
        switch (type){
            case 1:
                filePath = "src/main/java/docs/customers.txt";
                keyNums = 6;
                break;
            case 2:
                filePath = "src/main/java/docs/insuranceCards.txt";
                keyNums = 4;
                break;
            case 3:
                filePath = "src/main/java/docs/claims.txt";
                keyNums = 9;
        }
        List<String[]> Data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // Assuming the format is consistent
                String[] data = new String[keyNums]; // Assuming there are 6 fields in each line
                for (int i = 0; i < keyNums; i++) {
                    data[i] = "";
                }
                int iterator = 0;
                for (String part : parts) {
                    String[] keyValue = part.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();
                        if(key.equals("dependentList")) {
                            if (value.endsWith("]")) {
                                data[4] = value.equals("null") ? "" : value;
                            } else {
                                data[4] = value.equals("null") ? "" : value + ",...]";
                            }
                        } else if (key.equals("documents")) {
                            if (value.length() > 8) {
                                data[5] = value.substring(0, 5) + "...]";
                            }
                        } else if (key.equals("listOfClaims")) {
                            if (value.endsWith("]")) {
                                data[3] = value.equals("null") ? "" : value;
                            } else {
                                data[3] = value.equals("null") ? "" : value + ",...]";
                            }
                        } else {
                            data[iterator] = value.equals("null") ? "" : value;
                        }
                        iterator++;
                    }
                }
                Data.add(data);
            }
        }
        return Data;
    }

    public static List<String[]> listDependent() throws IOException {
        List<String[]> customerData = readFile(1);
        List<String[]> resultList = new ArrayList<>();
        for (String[] data : customerData) {
            if (data[4].isEmpty() && data[5].isEmpty()) {
                resultList.add(data);
            }
         }
        return resultList;
    }
    public static List<String[]> listHolder() throws IOException {
        List<String[]> customerData = readFile(1);
        List<String[]> resultList = new ArrayList<>();
        for (String[] data : customerData) {
            if ((!data[4].isEmpty() && data[5].isEmpty()) || (data[4].isEmpty() && data[5].isEmpty())) {
                resultList.add(data);
            }
        }
        return resultList;
    }
}
