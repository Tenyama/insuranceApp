package classes.cli.Claim;
/**
 * @author Le Thien Son - s3977955
 */
import classes.Claim;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static classes.fileManip.Reader.readFile;

public class ClaimsTable  {
    static Claim dummy = null;
    public static Table get(WindowBasedTextGUI textGUI){
        Table<String> table = new Table<String>("Id", "Claim Date", "Insured Person", "Card Number", "Exam Date", "Documents", "Claim Amount", "Status", "Receiver Banking Info");
        try {
            dummy = new Claim();
            List<String[]> claimData = dummy.getAll();
            for (String[] data : claimData) {
                List<String> rowData = new ArrayList<>();
                for (String value : data) {
                    rowData.add(value);
                }
                table.getTableModel().addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        table.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = table.getTableModel().getRow(table.getSelectedRow());

                // Create and display a pop-up window
                Panel popupContent = new Panel();
                popupContent.setLayoutManager(new GridLayout(1));
                popupContent.addComponent(new Label("Selected Row Info:"));
                popupContent.addComponent(new Label(dummy.getOne(data)));


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
