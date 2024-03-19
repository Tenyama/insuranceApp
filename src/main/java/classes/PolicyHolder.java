package classes;

import java.util.List;

public class PolicyHolder extends Customer{
    private List<Dependent> dependentList;
    public PolicyHolder() {
    }
    public PolicyHolder(List<Dependent> dependentList) {
        this.dependentList = dependentList;
    }
    public PolicyHolder(String id, String fullName, InsuranceCard card, List<Claim> listOfClaims, List<Dependent> dependentList) {
        super(id, fullName, card, listOfClaims);
        this.dependentList = dependentList;
    }

    public List<Dependent> getDependentList() {
        return dependentList;
    }
    public void addDependent(Dependent foo){
        dependentList.add(foo);
    }
}
