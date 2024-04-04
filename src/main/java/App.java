/**
 * @author Le Thien Son - s3977955
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

import static classes.fileManip.Reader.readCustomerData;


public class App{
    public static void main(String[] args) throws IOException {
//        Home.displayHomeScreen();
        InsuranceCard bidv = new InsuranceCard();
        PolicyHolder son = new PolicyHolder();
        son.setFullName("Mortimer Doggington");
        son.setCard(bidv);
        ObjectWriter.writeObject(new PolicyHolderWriter(son));
////        CustomerWriter.createCustomer(son);
//        Dependent phuc = new Dependent(son);
////        System.out.println(phuc.getId());
////        CustomerWriter.createCustomer(phuc);
//        ObjectWriter.writeObject(new DependentWriter(phuc));
        Claim bruh = new Claim.Builder().claimAmount(69.234).claimDate(LocalDate.of(2023,10,26)).insuredPerson(son).status("NEW").build();
        ObjectWriter.writeObject(new ClaimWriter(bruh));
    }
}
