package classes.fileManip;

import classes.Claim;
import classes.Customer.Dependent;

public class ClaimWriter extends ObjectWriter{
    private Claim claim;
    public ClaimWriter(Claim claim) {
        this.claim = claim;
        this.filePath = "src/main/java/docs/claims.txt";
    }

    @Override
    public String getObjectAsString() {
        return claim.toString();
    }
}
