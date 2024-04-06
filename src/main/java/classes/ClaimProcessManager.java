package classes;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.Claim;
import classes.fileManip.ClaimWriter;
import classes.fileManip.ObjectWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ClaimProcessManager {
    public void add();
    public void update(String idToUpdate, Claim dummy) throws IOException;
    public void delete(String name);
    public String getOne(List<String> data);
    public List<String[]> getAll() throws IOException;
}
