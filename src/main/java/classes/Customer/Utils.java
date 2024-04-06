package classes.Customer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static classes.fileManip.Reader.listDependent;
import static classes.fileManip.Reader.readCustomerData;

public class Utils {
    public static String findIdByName(String name) throws IOException {
        String id = "";
        try {
            List<String[]> dependents = readCustomerData();
            for (String[] data : dependents) {
                if(Objects.equals(data[1], name)) {
                    id = data[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
}
