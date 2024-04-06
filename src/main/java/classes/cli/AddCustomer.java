package classes.cli;

import classes.Customer.PolicyHolder;
import classes.fileManip.ObjectWriter;
import classes.fileManip.PolicyHolderWriter;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static classes.fileManip.Reader.listDependent;
import static classes.fileManip.Reader.readCustomerData;

public class AddCustomer {
    public static Component button(WindowBasedTextGUI textGUI){
        Button button = new Button("Add", new Runnable() {
            @Override
            public void run() {
                // Implement the logic to add an object to the file
                // For example:
                // Create text boxes
                TextBox fullNameTextBox = new TextBox(new TerminalSize(20,1));
                TextBox cardTextBox = new TextBox(new TerminalSize(20,1));
                ComboBox<String> dependentTextBox = new ComboBox<String>();
                ComboBox<String> holderTextBox = new ComboBox<String>();

                //ComboBoxes
                try {
                    List<String[]> dependents = listDependent();
                    for (String[] data : dependents) {
                        dependentTextBox.addItem(data[1]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Create labels for text boxes
                Label fullNameLabel = new Label("Full Name:");
                Label cardLabel = new Label("Card:");
                Label dependentLabel = new Label("Dependent List:");
                Label holderLabel = new Label("Policy Holder:");

                // Add components to a container (e.g., Panel)
                Panel panel = new Panel();
                panel.setLayoutManager(new GridLayout(2)); // Use GridLayout to arrange components

                panel.addComponent(fullNameLabel);
                panel.addComponent(fullNameTextBox);
                panel.addComponent(cardLabel);
                panel.addComponent(cardTextBox);
                panel.addComponent(dependentLabel);
                panel.addComponent(dependentTextBox);
                panel.addComponent(holderLabel);
                panel.addComponent(holderTextBox);

                String fullName = fullNameTextBox.getText();
                String card = cardTextBox.getText();
                String dependent = dependentTextBox.getText();
                String holder = holderTextBox.getText();

                // Create a button to save the content of text boxes
                Button saveButton = new Button("Save", new Runnable() {
                    @Override
                    public void run() {
                        String fullName = fullNameTextBox.getText();
                        String card = cardTextBox.getText();
                        String dependent = dependentTextBox.getSelectedItem();
                        String holder = holderTextBox.getText();

                        try {
                            PolicyHolder dummy = new PolicyHolder();
                            dummy.setFullName(fullName);
                            dummy.addDependent(dependent);
                            ObjectWriter.writeObject(new PolicyHolderWriter(dummy));
                            MessageDialog.showMessageDialog(textGUI,"Object Added", "The object was added successfully.");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                panel.addComponent(saveButton);
                // Create a window to contain the panel
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
