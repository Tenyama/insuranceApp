package classes.fileManip;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
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
