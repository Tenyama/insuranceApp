/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.Claim;
import classes.Customer.Dependent;
import classes.Customer.PolicyHolder;

import classes.InsuranceCard;
import classes.cli.Home;
import classes.fileManip.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static classes.fileManip.Reader.readFile;

public class App{
    public static void main(String[] args) throws IOException {
        Home.displayHomeScreen();
    }
}
