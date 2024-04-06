package classes.Customer;

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
    public PolicyHolder(String fullName, InsuranceCard card, List<Claim> listOfClaims, List<Dependent> dependentList) throws IOException {
        super(fullName, card, listOfClaims);
        this.dependentList = dependentList;
    }

    public PolicyHolder(String fullName, InsuranceCard card, List<Dependent> dependentList) {
        super(fullName, card);
        this.dependentList = dependentList;
    }

    public List<Dependent> getDependentList() {
        return dependentList;
    }
    public void addDependent(Dependent foo){
        dependentList.add(foo);
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
