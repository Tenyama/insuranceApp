package classes.fileManip;

import classes.Customer.Dependent;

public class DependentWriter extends ObjectWriter{
    private Dependent dependent;
    public DependentWriter(Dependent dependent) {
        this.dependent = dependent;
        this.filePath = "src/main/java/docs/customers.txt";
    }

    @Override
    public String getObjectAsString() {
        return dependent.toString();
    }
}
