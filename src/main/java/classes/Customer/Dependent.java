package classes.Customer;

import classes.Claim;
import classes.InsuranceCard;

import java.io.IOException;
import java.util.List;

public class Dependent extends Customer{
    private PolicyHolder holder;

    public Dependent(PolicyHolder holder) throws IOException {
        super();
        this.holder = holder;
    }

    public Dependent(String fullName, InsuranceCard card, List<Claim> listOfClaims, PolicyHolder holder) throws IOException {
        super(fullName, card, listOfClaims);
        this.holder = holder;
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
