package classes.Customer;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.Claim;
import classes.InsuranceCard;

import java.io.IOException;
import java.util.List;

public class Dependent extends Customer{
    private PolicyHolder holder;

    public Dependent(String holder) throws IOException {
        super();
        this.holder = new PolicyHolder(Utils.findIdByName(holder));
    }

    public Dependent(String id, PolicyHolder holder) {
        super(id);
        this.holder = holder;
    }

    @Override
    public String toString() {
        return super.toString() + ", dependentList=null" +
                ", holder=" + holder.getId();
    }
}
