package classes;

import classes.fileManip.ClaimWriter;
import classes.fileManip.ObjectWriter;

/**
 * @author Le Thien Son - s3977955
 */


public class ClaimProcessManager {
    public void add(Claim foo){
        ObjectWriter.writeObject(new ClaimWriter(foo));
    }
    public void update(Claim foo){

    }
    public void delete(Claim foo){

    }
    public void getOne(){}
    public void getAll(){}
}
