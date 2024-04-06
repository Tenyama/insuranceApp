package classes.cli.Customer;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static classes.fileManip.Reader.readFile;

public class CustomerTable{
    public static Table get(WindowBasedTextGUI textGUI){
        Table<String> table = new Table<String>("Id", "Full Name", "Card", String.format("%15s","List of Claims"), String.format("%15s","Dependent List"), String.format("%15s","Policy Holder"));
        try {
            List<String[]> customerData = readFile(1);
            for (String[] data : customerData) {
                List<String> rowData = new ArrayList<>();
                for (String value : data) {
                    rowData.add(value);
                }
                table.getTableModel().addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        table.setLayoutData(
                GridLayout.createLayoutData(
                        GridLayout.Alignment.BEGINNING,
                        GridLayout.Alignment.BEGINNING,
                        true,
                        true,
                        4,
                        20)
        );
        table.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = table.getTableModel().getRow(table.getSelectedRow());

                // Construct the message string
                StringBuilder message = new StringBuilder();
                message.append("Id: ").append(data.get(0)).append("\n");
                message.append("Full Name: ").append(data.get(1)).append("\n");
                message.append("Card: ").append(data.get(2)).append("\n");
                message.append("List of Claims: ").append(data.get(3)).append("\n");
                message.append("Dependent List: ").append(data.get(4)).append("\n");
                message.append("Policy Holder: ").append(data.get(5)).append("\n");

                // Create and display a pop-up window
                Panel popupContent = new Panel();
                popupContent.setLayoutManager(new GridLayout(1));
                popupContent.addComponent(new Label("Selected Row Info:"));
                popupContent.addComponent(new Label(message.toString()));


                BasicWindow popupWindow = new BasicWindow();
                popupWindow.setComponent(popupContent);
                popupWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
                popupWindow.setTitle("Row Info");
                popupWindow.setCloseWindowWithEscape(true);
                textGUI.addWindow(popupWindow);
                textGUI.setActiveWindow(popupWindow);
            }
        });
        return table;
    }
}
