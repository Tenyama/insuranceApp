package classes.cli.Claim;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import classes.Claim;
import classes.Customer.PolicyHolder;
import classes.Customer.Utils;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static classes.fileManip.Reader.readFile;

public class UpdateClaim {
    static Claim dummy = null;
    public static Button button(WindowBasedTextGUI textGUI){
        Button button = new Button("Update", new Runnable() {
            @Override
            public void run() {
                Panel subPanel = new Panel();
                ComboBox<String> idToUpdateBox = new ComboBox<String>();
                try {
                    dummy = new Claim();
                    List<String[]> claims = dummy.getAll();
                    for (String[] data : claims) {
                        idToUpdateBox.addItem(data[0]);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Label claimIdLabel = new Label("Claim ID:");
                subPanel.addComponent(claimIdLabel);
                subPanel.addComponent(idToUpdateBox);
                Button newDataButton = new Button("New Data", new Runnable() {
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
                        panel.addComponent(new Button("Add Documents", new Runnable() {
                            @Override
                            public void run() {
                                Panel subPanel = new Panel();
                                ComboBox<String> claimIdBox = new ComboBox<String>();
                                ComboBox<String> cardNumberBox = new ComboBox<>();
                                TextBox docNameBox = new TextBox();
                                try {
                                    dummy = new Claim();
                                    List<String[]> claims = dummy.getAll();
                                    for (String[] data : claims) {
                                        claimIdBox.addItem(data[0]);
                                    }
                                    List<String[]> cards = readFile(2);
                                    for (String[] data : cards) {
                                        cardNumberBox.addItem(data[0]);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Label claimIdLabel = new Label("Claim ID:");
                                Label cardNumberLabel = new Label("Card Number:");
                                Label docNameLabel = new Label("Document Name:");

                                subPanel.addComponent(claimIdLabel);
                                subPanel.addComponent(claimIdBox);
                                subPanel.addComponent(cardNumberLabel);
                                subPanel.addComponent(cardNumberBox);
                                subPanel.addComponent(docNameLabel);
                                subPanel.addComponent(docNameBox);

                                Button saveButton = new Button("Confirm", new Runnable() {
                                    @Override
                                    public void run() {
                                        String result = claimIdBox.getSelectedItem() + "_" + cardNumberBox.getSelectedItem() + "_" + docNameBox.getText() + ".pdf";
                                        documents.add(result);
                                        MessageDialog.showMessageDialog(textGUI,"Document Added", "The document was added successfully.");
                                    }
                                });
                                subPanel.addComponent(saveButton);
                                // Create a window to contain the panel
                                BasicWindow window = new BasicWindow("Enter Document Details");
                                window.setCloseWindowWithEscape(true);
                                window.setHints(Arrays.asList(Window.Hint.CENTERED));
                                window.setComponent(subPanel);
                                // Add the window to the GUI
                                textGUI.addWindowAndWait(window);
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
                                if (Objects.equals(insuredPerson, "NONE")) {
                                    MessageDialog.showMessageDialog(textGUI, "Error", "Please select a customer");
                                } else if (Objects.equals(card, "NONE")) {
                                    MessageDialog.showMessageDialog(textGUI, "Error", "Please select a card");
                                } else if (Objects.equals(bankBox.getText(), "") || Objects.equals(nameBox.getText(), "") || Objects.equals(numberBox.getText(), "")) {
                                    MessageDialog.showMessageDialog(textGUI, "Error", "Please provide the Receiver Info");
                                } else {
                                    try {
                                        PolicyHolder dum = new PolicyHolder(Utils.findIdByName(insuredPerson));
                                        Claim dummy2 = new Claim.Builder().id(idToUpdateBox.getSelectedItem()).claimDate(LocalDate.of(claimYear, claimMonth, claimDay))
                                                .insuredPerson(dum).cardNumber(card).examDate(LocalDate.of(examYear, examMonth, examDay))
                                                .documents(documents).claimAmount(claimAmount).status(status).receiverBankingInfo(receiverInfo).build();
                                        dummy2.update(idToUpdateBox.getSelectedItem(), dummy2);
                                        MessageDialog.showMessageDialog(textGUI, "Object Updated", "The claim was updated successfully.");
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
                subPanel.addComponent(newDataButton);
                // Create a window to contain the panel
                BasicWindow window = new BasicWindow("Enter Customer Details");
                window.setCloseWindowWithEscape(true);
                window.setHints(Arrays.asList(Window.Hint.CENTERED));
                window.setComponent(subPanel);
                // Add the window to the GUI
                textGUI.addWindowAndWait(window);
            }
        });
        return button;
    }
}

