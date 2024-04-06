package classes.cli;

import classes.fileManip.FileLineRemover;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static classes.fileManip.Reader.readCustomerData;

public class RemoveCustomer {
    private static String filePath = "src/main/java/docs/customers.txt";
    public static Component button(WindowBasedTextGUI textGUI){
        Button button = new Button("Delete", new Runnable() {
            @Override
            public void run() {
                Panel panel = new Panel(new GridLayout(2));
                Label title = new Label("Please select the id to delete");
//                title.setLayoutData(GridLayout.createLayoutData(
//                    GridLayout.Alignment.BEGINNING, // Horizontal alignment in the grid cell if the cell is larger than the component's preferred size
//                    GridLayout.Alignment.BEGINNING, // Vertical alignment in the grid cell if the cell is larger than the component's preferred size
//                    true,       // Give the component extra horizontal space if available
//                    false,        // Give the component extra vertical space if available
//                    1,                  // Horizontal span
//                    1));                  // Vertical span
                ComboBox<String> nameToRemove = new ComboBox<String>();
                try {
                    List<String[]> customers = readCustomerData();
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
                            List<String[]> customers = readCustomerData();
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
                BasicWindow window = new BasicWindow("Enter Customer Details");
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
