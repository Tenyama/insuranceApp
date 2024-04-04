package classes.cli;

import java.io.IOException;
import java.util.*;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class ManageCustomers {
    public static void selector(Terminal terminal, Screen screen, TextGraphics tg, TerminalSize terminalSize) throws IOException {
        screen.clear();
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window window = new BasicWindow("Manage Customers");
//        final SimpleTheme theme = new SimpleTheme(TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        Panel contentPanel = new Panel(new GridLayout(4));
        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        window.getCursorPosition();
        gridLayout.setHorizontalSpacing(4);
        window.setHints(Arrays.asList(Window.Hint.NO_POST_RENDERING, Window.Hint.FULL_SCREEN));
//        textGUI.setTheme(theme);
        contentPanel.addComponent(
                new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));
        Label title = new Label("");
        title.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.BEGINNING, // Horizontal alignment in the grid cell if the cell is larger than the component's preferred size
                GridLayout.Alignment.BEGINNING, // Vertical alignment in the grid cell if the cell is larger than the component's preferred size
                true,       // Give the component extra horizontal space if available
                false,        // Give the component extra vertical space if available
                4,                  // Horizontal span
                1));                  // Vertical span
        contentPanel.addComponent(title);
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
        contentPanel.addComponent(
                new Button("Close", window::close).setLayoutData(
                        GridLayout.createHorizontallyEndAlignedLayoutData(4)));
        screen.refresh();
        window.setComponent(contentPanel);
        textGUI.addWindowAndWait(window);
//        tg.putString(2, terminalHeight - 5,"1. View a Customers");
//        tg.putString(2, terminalHeight - 4,"2. View all Customers");
//        tg.putString(2, terminalHeight - 3,"3. Add a Customer");
//        tg.putString(2, terminalHeight - 2,"4. Remove a Customer");
//        screen.refresh();
    }
}
