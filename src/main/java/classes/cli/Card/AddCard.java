package classes.cli.Card;
/**
 * @author Le Thien Son - s3977955
 */
import classes.Customer.Customer;
import classes.Customer.Dependent;
import classes.Customer.PolicyHolder;
import classes.Customer.Utils;
import classes.InsuranceCard;
import classes.fileManip.DependentWriter;
import classes.fileManip.InsuranceCardWriter;
import classes.fileManip.ObjectWriter;
import classes.fileManip.PolicyHolderWriter;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static classes.fileManip.Reader.listDependent;
import static classes.fileManip.Reader.listHolder;

public class AddCard {
    public static Component button(WindowBasedTextGUI textGUI) {
        Button button = new Button("Add", new Runnable() {
            @Override
            public void run() {
                // Implement the logic to add an object to the file
                // For example:
                // Create text boxes
                ComboBox<String> cardHolderBox = new ComboBox<String>();
                TextBox policyOwnerBox = new TextBox(new TerminalSize(20, 1));
                TextBox yearBox = new TextBox();
                TextBox monthBox = new TextBox();
                TextBox dayBox = new TextBox();

                //ComboBox
                cardHolderBox.addItem("NONE");
                try {
                    List<String[]> holders = listHolder();
                    for (String[] data : holders) {
                        cardHolderBox.addItem(data[1]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Create labels for text boxes
                Label cardHolderLabel = new Label("Card Holder:");
                Label policyOwnerLabel = new Label("Policy Owner:");
                Label expDate = new Label("Please enter the Expiration Date");
                Label yearLabel = new Label("Year:");
                Label monthLabel = new Label("Month:");
                Label dayLabel = new Label("Day:");

                // Add components to a container (e.g., Panel)
                Panel panel = new Panel();
                panel.setLayoutManager(new GridLayout(2)); // Use GridLayout to arrange components

                panel.addComponent(cardHolderLabel);
                panel.addComponent(cardHolderBox);
                panel.addComponent(policyOwnerLabel);
                panel.addComponent(policyOwnerBox);
                panel.addComponent(expDate);
                panel.addComponent(new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(2)));
                panel.addComponent(yearLabel);
                panel.addComponent(yearBox);
                panel.addComponent(monthLabel);
                panel.addComponent(monthBox);
                panel.addComponent(dayLabel);
                panel.addComponent(dayBox);

                // Create a button to save the content of text boxes
                Button saveButton = new Button("Save", new Runnable() {
                    @Override
                    public void run() {
                        String cardHolder = cardHolderBox.getSelectedItem();
                        String policyOwner = Objects.equals(policyOwnerBox.getText(), "") ? "null" : policyOwnerBox.getText();
                        int year = Objects.equals(yearBox.getText(), "") ? LocalDate.now().getYear() : Integer.parseInt(yearBox.getText());
                        int month = Objects.equals(monthBox.getText(), "") ? LocalDate.now().getMonthValue() : Integer.parseInt(monthBox.getText());
                        int day = Objects.equals(dayBox.getText(), "") ? LocalDate.now().getDayOfMonth() : Integer.parseInt(dayBox.getText());

                        InsuranceCard dummy = null;
                        try {
                            PolicyHolder dum = new PolicyHolder(Utils.findIdByName(cardHolder));
                            dummy = new InsuranceCard.Builder()
                                    .cardHolder(dum).policyOwner(policyOwner).expirationDate(LocalDate.of(year, month, day)).build();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ObjectWriter.writeObject(new InsuranceCardWriter(dummy));
                        MessageDialog.showMessageDialog(textGUI,"Object Added", "The card was added successfully.\n" +
                                "Your card number is: " + dummy.getCardNumber());
                    }
                });
                panel.addComponent(saveButton);
                // Create a window to contain the panel
                BasicWindow window = new BasicWindow("Enter Card Details");
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
