package classes;

import java.io.IOException;
import java.util.List;

public class PolicyHolder extends Customer{
    private List<Dependent> dependentList;
    public PolicyHolder() throws IOException {
        super();
    }
    public PolicyHolder(String fullName, InsuranceCard card, List<Claim> listOfClaims, List<Dependent> dependentList) throws IOException {
        super(fullName, card, listOfClaims);
        this.dependentList = dependentList;
    }

    public List<Dependent> getDependentList() {
        return dependentList;
    }
    public void addDependent(Dependent foo){
        dependentList.add(foo);
    }

    @Override
    public String toString() {
        return  super.toString() + ", dependentList=" + dependentList
                + ", holder=null";
    }
}
