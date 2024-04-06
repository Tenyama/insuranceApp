package classes.cli.Claim;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.Claim;
import classes.fileManip.FileLineRemover;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static classes.fileManip.Reader.readFile;

public class RemoveClaim {
    static Claim dummy = null;
    public static Component button(WindowBasedTextGUI textGUI) throws IOException {
        dummy = new Claim();
        Button button = new Button("Delete", new Runnable() {
            @Override
            public void run() {
                Panel panel = new Panel(new GridLayout(2));
                Label title = new Label("Please select the claim to delete");
                ComboBox<String> claimToRemove = new ComboBox<String>();
                try {
                    List<String[]> claimData = dummy.getAll();
                    for (String[] data : claimData) {
                        claimToRemove.addItem(data[0]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                panel.addComponent(title);
                panel.addComponent(claimToRemove);
                Button saveButton = new Button("Confirm", new Runnable() {
                    @Override
                    public void run() {
                        String name = claimToRemove.getSelectedItem();
                        dummy.delete(name);
                        MessageDialog.showMessageDialog(textGUI,"Object Deleted", "The object was deleted successfully.");
                    }
                });
                panel.addComponent(saveButton);
                //Add to the Window
                BasicWindow window = new BasicWindow("Delete Claim");
                window.setCloseWindowWithEscape(true);
                window.setHints(Arrays.asList(Window.Hint.CENTERED));
                window.setComponent(panel);
                // Add the window to the GUI
                textGUI.addWindowAndWait(window);
            }
        });
        return button;
    }
}
