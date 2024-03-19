package classes;

import java.util.List;

public class Dependent extends Customer{
    public Dependent() {
    }
    public Dependent(String id, String fullName, InsuranceCard card, List<Claim> listOfClaims) {
        super(id, fullName, card, listOfClaims);
    }

}
