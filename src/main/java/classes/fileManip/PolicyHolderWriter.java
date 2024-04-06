package classes.fileManip;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.Customer.PolicyHolder;

public class PolicyHolderWriter extends ObjectWriter{
    private PolicyHolder policyHolder;
    public PolicyHolderWriter(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
        this.filePath = "src/main/java/docs/customers.txt";
    }

    @Override
    public String getObjectAsString() {
        return policyHolder.toString();
    }
}
