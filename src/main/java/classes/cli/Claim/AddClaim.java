package classes.cli.Claim;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.Claim;
import classes.Customer.Dependent;
import classes.Customer.PolicyHolder;
import classes.Customer.Utils;
import classes.InsuranceCard;
import classes.fileManip.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;

import javax.swing.text.LabelView;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static classes.fileManip.Reader.*;

public class AddClaim{
    static Claim dummy = null;
    public static Component button(WindowBasedTextGUI textGUI){
        Button button = new Button("Add", new Runnable() {
            @Override
            public void run() {
                //Initiate a list of documents
                List<String> documents = new ArrayList<>();
                // Create text boxes
                TextBox claimYearBox = new TextBox();
                TextBox claimMonthBox = new TextBox();
                TextBox claimDayBox = new TextBox();
                ComboBox<String> insuredPersonBox = new ComboBox<String>();
                ComboBox<String> cardBox = new ComboBox<String>();
                TextBox examYearBox = new TextBox();
                TextBox examMonthBox = new TextBox();
                TextBox examDayBox = new TextBox();
                TextBox claimBox = new TextBox();
                ComboBox<String> statusBox = new ComboBox<String>();
                TextBox bankBox = new TextBox();
                TextBox nameBox = new TextBox();
                TextBox numberBox = new TextBox();

                //ComboBoxes
                insuredPersonBox.addItem("NONE");
                try {
                    List<String[]> holders = readFile(1);
                    for (String[] data : holders) {
                        insuredPersonBox.addItem(data[1]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                cardBox.addItem(("NONE"));
                try {
                    List<String[]> cards = readFile(2);
                    for (String[] data : cards) {
                        cardBox.addItem(data[0]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                statusBox.addItem("NEW");
                statusBox.addItem("PROCESSING");
                statusBox.addItem("DONE");

                // Create labels for text boxes
                Label claimDateLabel = new Label("Claim date:");
                Label claimYearLabel = new Label("Year:");
                Label claimMonthLabel = new Label("Month:");
                Label claimDayLabel = new Label("Day:");
                Label insuredPersonLabel = new Label("Full Name:");
                Label cardLabel = new Label("Card:");
                Label examDateLabel = new Label("Exam date:");
                Label examYearLabel = new Label("Year:");
                Label examMonthLabel = new Label("Month:");
                Label examDayLabel = new Label("Day:");
                Label claimLabel = new Label("Claim Amount:");
                Label statusLabel = new Label("Status:");
                Label receiverBankInfoLabel = new Label("Receiver information:");
                Label bankLabel = new Label("Receiver Bank:");
                Label bankNameLabel = new Label("Receiver Name:");
                Label bankNumberLabel = new Label("Receiver Number:");

                // Add components to a container (e.g., Panel)
                Panel panel = new Panel();
                panel.setLayoutManager(new GridLayout(4)); // Use GridLayout to arrange components

                panel.addComponent(claimDateLabel);
                panel.addComponent(new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(3)));
                panel.addComponent(claimYearLabel);
                panel.addComponent(claimYearBox);
                panel.addComponent(claimMonthLabel);
                panel.addComponent(claimMonthBox);
                panel.addComponent(claimDayLabel);
                panel.addComponent(claimDayBox);
                panel.addComponent(new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(2)));
                panel.addComponent(insuredPersonLabel);
                panel.addComponent(insuredPersonBox);
                panel.addComponent(cardLabel);
                panel.addComponent(cardBox);
                panel.addComponent(examDateLabel);
                panel.addComponent(new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(3)));
                panel.addComponent(examYearLabel);
                panel.addComponent(examYearBox);
                panel.addComponent(examMonthLabel);
                panel.addComponent(examMonthBox);
                panel.addComponent(examDayLabel);
                panel.addComponent(examDayBox);
                panel.addComponent(new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(2)));
                panel.addComponent(new Label("Documents:"));
                panel.addComponent(new Button("Add Document", new Runnable() {
                    @Override
                    public void run() {
                        try {
                            dummy = new Claim();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        String input = TextInputDialog.showDialog(textGUI, "Documents", "Please enter the document name", "Initial content");
                        documents.add(dummy.getId() + "_" + cardBox.getSelectedItem() + "_" + input + ".pdf");
                    }
                }));
                panel.addComponent(new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(2)));
                panel.addComponent(claimLabel);
                panel.addComponent(claimBox);
                panel.addComponent(statusLabel);
                panel.addComponent(statusBox);
                panel.addComponent(receiverBankInfoLabel);
                panel.addComponent(new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(3)));
                panel.addComponent(bankLabel);
                panel.addComponent(bankBox);
                panel.addComponent(bankNameLabel);
                panel.addComponent(nameBox);
                panel.addComponent(bankNumberLabel);
                panel.addComponent(numberBox);

                // Create a button to save the content of text boxes
                Button saveButton = new Button("Save", new Runnable() {
                    @Override
                    public void run() {
                        int claimYear = Objects.equals(claimYearBox.getText(), "") ? LocalDate.now().getYear() : Integer.parseInt(claimYearBox.getText());
                        int claimMonth = Objects.equals(claimMonthBox.getText(), "") ? LocalDate.now().getMonthValue() : Integer.parseInt(claimMonthBox.getText());
                        int claimDay = Objects.equals(claimDayBox.getText(), "") ? LocalDate.now().getDayOfMonth() : Integer.parseInt(claimDayBox.getText());
                        String insuredPerson = insuredPersonBox.getSelectedItem();
                        String card = cardBox.getSelectedItem();
                        int examYear = Objects.equals(examYearBox.getText(), "") ? LocalDate.now().getYear() : Integer.parseInt(examYearBox.getText());
                        int examMonth = Objects.equals(examMonthBox.getText(), "") ? LocalDate.now().getMonthValue() : Integer.parseInt(examMonthBox.getText());
                        int examDay = Objects.equals(examDayBox.getText(), "") ? LocalDate.now().getDayOfMonth() : Integer.parseInt(examDayBox.getText());
                        double claimAmount = Objects.equals(claimBox.getText(), "") ? 0 : Double.parseDouble(claimBox.getText());
                        String status = statusBox.getSelectedItem();
                        String receiverInfo = bankBox.getText() + "-" + nameBox.getText() + "-" + numberBox.getText();
                        if(Objects.equals(insuredPerson, "NONE")){
                            MessageDialog.showMessageDialog(textGUI,"Error", "Please select a customer");
                        } else if (Objects.equals(card, "NONE")) {
                            MessageDialog.showMessageDialog(textGUI,"Error", "Please select a card");
                        } else if (Objects.equals(bankBox.getText(), "") || Objects.equals(nameBox.getText(), "") || Objects.equals(numberBox.getText(), "")) {
                            MessageDialog.showMessageDialog(textGUI,"Error", "Please provide the Receiver Info");
                        } else {
                            try {
                                PolicyHolder dum = new PolicyHolder(Utils.findIdByName(insuredPerson));
                                Claim dummy = new Claim.Builder().id().claimDate(LocalDate.of(claimYear, claimMonth, claimDay))
                                        .insuredPerson(dum).cardNumber(card).examDate(LocalDate.of(examYear, examMonth, examDay))
                                        .documents(documents).claimAmount(claimAmount).status(status).receiverBankingInfo(receiverInfo).build();
                                dummy.add();
                                MessageDialog.showMessageDialog(textGUI, "Object Added", "The card was added successfully.");
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
