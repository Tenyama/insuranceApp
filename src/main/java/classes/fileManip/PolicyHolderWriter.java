package classes.fileManip;

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
