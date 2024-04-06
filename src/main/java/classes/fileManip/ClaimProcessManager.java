package classes.fileManip;
/**
 * @author Le Thien Son - s3977955
 */
import classes.Claim;
import classes.fileManip.ClaimWriter;
import classes.fileManip.ObjectWriter;

import java.io.IOException;
import java.util.List;

public interface ClaimProcessManager {
    public void add();
    public void update();
    public void delete(String name);
    public String getOne(List<String> data);
    public List<String[]> getAll() throws IOException;
}
