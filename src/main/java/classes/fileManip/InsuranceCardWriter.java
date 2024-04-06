package classes.fileManip;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
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
