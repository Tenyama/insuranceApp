package classes.cli.Customer;
/**
 * @author Le Thien Son - s3977955
 */
import classes.Customer.Dependent;
import classes.Customer.PolicyHolder;
import classes.InsuranceCard;
import classes.fileManip.DependentWriter;
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
import java.util.Objects;

import static classes.fileManip.Reader.*;

public class AddCustomer {
    public static Component button(WindowBasedTextGUI textGUI){
        Button button = new Button("Add", new Runnable() {
            @Override
            public void run() {
                // Implement the logic to add an object to the file
                // For example:
                // Create text boxes
                TextBox fullNameTextBox = new TextBox(new TerminalSize(20,1));
                ComboBox<String> cardTextBox = new ComboBox<String>();
                CheckBoxList<String> dependentTextBox = new CheckBoxList<String>(new TerminalSize(30, 5));
                ComboBox<String> holderTextBox = new ComboBox<String>();

                //CheckBoxes
                try {
                    List<String[]> dependents = listDependent();
                    for (String[] data : dependents) {
                        dependentTextBox.addItem(data[1]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //ComboBox
                holderTextBox.addItem("NONE");
                try {
                    List<String[]> holders = listHolder();
                    for (String[] data : holders) {
                        holderTextBox.addItem(data[1]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                cardTextBox.addItem(("NONE"));
                try {
                    List<String[]> cards = readFile(2);
                    for (String[] data : cards) {
                        holderTextBox.addItem(data[0]);
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

                // Create a button to save the content of text boxes
                Button saveButton = new Button("Save", new Runnable() {
                    @Override
                    public void run() {
                        String fullName = fullNameTextBox.getText();
                        String card = cardTextBox.getSelectedItem();
                        List<String> dependent = dependentTextBox.getCheckedItems();
                        String holder = holderTextBox.getSelectedItem();
                        if(!dependent.isEmpty() && !Objects.equals(holder, "NONE")){
                            MessageDialog.showMessageDialog(textGUI,"Error", "A customer cannot be a Holder and a Dependent");
                        } else if (!dependent.isEmpty() && Objects.equals(holder, "NONE")) {
                            try {
                                PolicyHolder dummy = new PolicyHolder();
                                InsuranceCard fake = new InsuranceCard(card);
                                dummy.setFullName(fullName);
                                dummy.setCard(fake);
                                for (String data : dependent){
                                    dummy.addDependent(data);
                                }
                                ObjectWriter.writeObject(new PolicyHolderWriter(dummy));
                                MessageDialog.showMessageDialog(textGUI,"Object Added", "The object was added successfully.");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (Objects.equals(fullName, "")) {
                            MessageDialog.showMessageDialog(textGUI,"Error", "No name provided");
                        } else {
                            try {
                                Dependent dummy = new Dependent(holder);
                                InsuranceCard fake = new InsuranceCard();
                                dummy.setFullName(fullName);
                                dummy.setCard(fake);
                                ObjectWriter.writeObject(new DependentWriter(dummy));
                                MessageDialog.showMessageDialog(textGUI,"Object Added", "The object was added successfully.");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
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
