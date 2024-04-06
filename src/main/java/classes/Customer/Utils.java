package classes.Customer;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static classes.fileManip.Reader.listDependent;
import static classes.fileManip.Reader.readFile;

public class Utils {
    public static String findIdByName(String name) throws IOException {
        String id = "";
        try {
            List<String[]> dependents = readFile(1);
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
