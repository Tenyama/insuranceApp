package classes.Customer;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.Claim;
import classes.InsuranceCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static classes.Customer.Utils.findIdByName;

public class PolicyHolder extends Customer{
    private List<Dependent> dependentList = new ArrayList<>();
    public PolicyHolder() throws IOException {
        super();
    }
    public PolicyHolder(String id) {
        super(id);
    }

    public List<Dependent> getDependentList() {
        return dependentList;
    }
    public void addDependent(String foo) throws IOException {
        Dependent dummy = new Dependent(findIdByName(foo), this);
        dependentList.add(dummy);
    }
    private List<String> abbreviatedDependentList() {
        List<String> dummy = new ArrayList<>();
        if (dependentList != null) {
            for (int i = 0; i < dependentList.size(); i++) {
                dummy.add(dependentList.get(i).getId());
            }
        }
        return dummy;
    }


    @Override
    public String toString() {
        return  super.toString() +
                ", dependentList=" + abbreviatedDependentList()
                + ", holder=null";
    }
}
