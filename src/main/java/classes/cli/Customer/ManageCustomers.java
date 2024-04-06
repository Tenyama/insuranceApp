package classes.cli.Customer;
/**
 * @author Le Thien Son - s3977955
 */
import java.io.IOException;
import java.util.*;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;

public class ManageCustomers {
    public static void selector(Screen screen) throws IOException {
        screen.clear();
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window window = new BasicWindow("Manage Customers");
        Panel contentPanel = new Panel(new GridLayout(4));
        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        window.getCursorPosition();
        gridLayout.setHorizontalSpacing(4);
        window.setHints(Arrays.asList(Window.Hint.NO_POST_RENDERING, Window.Hint.FULL_SCREEN));
        contentPanel.addComponent(
                new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));
        Table table = CustomerTable.get(textGUI);
        contentPanel.addComponent(table);
        contentPanel.addComponent(
                new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));
        contentPanel.addComponent(AddCustomer.button(textGUI));
        contentPanel.addComponent(RemoveCustomer.button(textGUI));
        contentPanel.addComponent(
                new Button("Close", window::close).setLayoutData(
                        GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        screen.refresh();
        window.setComponent(contentPanel);
        textGUI.addWindowAndWait(window);
    }
}
