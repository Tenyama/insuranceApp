package classes.cli.Card;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.fileManip.FileLineRemover;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static classes.fileManip.Reader.readFile;

public class RemoveCard {
    private static String filePath = "src/main/java/docs/insuranceCards.txt";
    public static Component button(WindowBasedTextGUI textGUI){
        Button button = new Button("Delete", new Runnable() {
            @Override
            public void run() {
                Panel panel = new Panel(new GridLayout(2));
                Label title = new Label("Please select the card to delete");
                ComboBox<String> cardToRemove = new ComboBox<String>();
                try {
                    List<String[]> customers = readFile(2);
                    for (String[] data : customers) {
                        cardToRemove.addItem(data[0]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                panel.addComponent(title);
                panel.addComponent(cardToRemove);
                Button saveButton = new Button("Confirm", new Runnable() {
                    @Override
                    public void run() {
                        String name = cardToRemove.getSelectedItem();
                        FileLineRemover.removeLineById(filePath, name);
                        MessageDialog.showMessageDialog(textGUI,"Object Deleted", "The object was deleted successfully.");
                    }
                });
                panel.addComponent(saveButton);
                //Add to the Window
                BasicWindow window = new BasicWindow("Delete Customer Details");
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
