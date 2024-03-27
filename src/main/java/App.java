/**
 * @author Le Thien Son - s3977955
 */
import classes.Customer.Dependent;
import classes.Customer.PolicyHolder;
import classes.InsuranceCard;
import classes.fileManip.*;

import java.io.IOException;

public class App{
    public static void main(String[] args) throws IOException {
////        Home.displayHomeScreen();
//        PolicyHolder son = new PolicyHolder();
//        son.setFullName("Le Thien Son");
//        ObjectWriter.writeObject(new PolicyHolderWriter(son));
////        CustomerWriter.createCustomer(son);
//        Dependent phuc = new Dependent(son);
////        System.out.println(phuc.getId());
////        CustomerWriter.createCustomer(phuc);
//        ObjectWriter.writeObject(new DependentWriter(phuc));
        InsuranceCard bidv = new InsuranceCard.Builder().build();
        System.out.println(bidv.toString());
        ObjectWriter.writeObject(new InsuranceCardWriter(bidv));
    }
}
