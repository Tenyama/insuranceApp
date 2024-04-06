package classes.cli.Card;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static classes.fileManip.Reader.readFile;

public class CardsTable {
    public static Table get(WindowBasedTextGUI textGUI){
        Table<String> table = new Table<String>("Card Number", "Card Holder", "Policy Owner", "Expiration Date");
        try {
            List<String[]> cardData = readFile(2);
            for (String[] data : cardData) {
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
                message.append("Card Number: ").append(data.get(0)).append("\n");
                message.append("Card Holder: ").append(data.get(1)).append("\n");
                message.append("Policy Owner: ").append(data.get(2)).append("\n");
                message.append("Expiration Date: ").append(data.get(3)).append("\n");

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
