/**
 * @author Le Thien Son - s3977955
 */
import classes.Dependent;
import classes.PolicyHolder;
import classes.fileManip.CustomerWriter;
import classes.fileManip.MaxIdFinder;
import classes.fileManip.Reader;

import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App{
    public static void main(String[] args) throws IOException {
//        Home.displayHomeScreen();
        PolicyHolder son = new PolicyHolder();
        son.setFullName("Le Thien Son");
        System.out.println(son.getId());
        CustomerWriter.createCustomer(son);
        Dependent phuc = new Dependent(son);
        System.out.println(phuc.getId());
        CustomerWriter.createCustomer(phuc);
    }
}
