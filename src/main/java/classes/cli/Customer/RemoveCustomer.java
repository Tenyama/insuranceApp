package classes.cli.Customer;

import classes.fileManip.FileLineRemover;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static classes.fileManip.Reader.readFile;

public class RemoveCustomer {
    private static String filePath = "src/main/java/docs/customers.txt";
    public static Component button(WindowBasedTextGUI textGUI){
        Button button = new Button("Delete", new Runnable() {
            @Override
            public void run() {
                Panel panel = new Panel(new GridLayout(2));
                Label title = new Label("Please select the id to delete");
                ComboBox<String> nameToRemove = new ComboBox<String>();
                try {
                    List<String[]> customers = readFile(1);
                    for (String[] data : customers) {
                        nameToRemove.addItem(data[1]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                panel.addComponent(title);
                panel.addComponent(nameToRemove);
                Button saveButton = new Button("Confirm", new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String name = nameToRemove.getSelectedItem();
                            String idToRemove = "";
                            List<String[]> customers = readFile(1);
                            for (String[] data : customers) {
                                if(Objects.equals(data[1], name)){
                                    idToRemove = data[0];
                                }
                            }
                            FileLineRemover.removeLineById(filePath, idToRemove);
                            MessageDialog.showMessageDialog(textGUI,"Object Deleted", "The object was deleted successfully.");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
