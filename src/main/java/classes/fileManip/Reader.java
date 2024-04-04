package classes.fileManip;

import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;


public class Reader {
//    public static List<Map<String, String>> readCustomerData() throws IOException {
//        List<Map<String, String>> customerDataList = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/docs/customers.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                Map<String, String> customerData = parseCustomerLine(line);
//                if (!customerData.isEmpty()) {
//                    customerDataList.add(customerData);
//                }
//            }
//        }
//
//        return customerDataList;
//    }
//    private static Map<String, String> parseCustomerLine(String line) {
//        Map<String, String> customerData = new LinkedHashMap<>();
//        Pattern pattern = Pattern.compile("(\\w+)='(.*?)'");
//        Matcher matcher = pattern.matcher(line);
//
//        while (matcher.find()) {
//            customerData.put(matcher.group(1), matcher.group(2));
//        }
//
//        return customerData;
//    }
public static List<String[]> readCustomerData() throws IOException {
    List<String[]> customerData = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/docs/customers.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", "); // Assuming the format is consistent
            String[] data = new String[6]; // Assuming there are 6 fields in each line
            for (int i = 0; i < 6; i++) {
                data[i] = "";
            }
            for (String part : parts) {
                String[] keyValue = part.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    switch (key) {
                        case "id":
                            data[0] = value;
                            break;
                        case "fullName":
                            data[1] = value.equals("null") ? "" : value;
                            break;
                        case "card":
                            data[2] = value.equals("null") ? "" : value;
                            break;
                        case "listOfClaims":
                            data[3] = value.equals("null") ? "" : value;
                            break;
                        case "dependentList":
                            data[4] = value.equals("null") ? "" : value;
                            break;
                        case "holder":
                            data[5] = value.equals("null") ? "" : value;
                            break;
                    }
                }
            }
            customerData.add(data);
        }
    }

    return customerData;
}
}
