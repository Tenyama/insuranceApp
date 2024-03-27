package classes.fileManip;

import classes.Customer.Dependent;
import classes.InsuranceCard;

public class InsuranceCardWriter extends ObjectWriter{
    private InsuranceCard insuranceCard;
    public InsuranceCardWriter(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
        this.filePath = "src/main/java/docs/insuranceCards.txt";
    }

    @Override
    public String getObjectAsString() {
        return insuranceCard.toString();
    }
}
