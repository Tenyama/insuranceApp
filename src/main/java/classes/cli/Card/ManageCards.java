package classes.cli.Card;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Arrays;

public class ManageCards {
    public static void view(Screen screen) throws IOException {
        screen.clear();
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window window = new BasicWindow("Manage Cards");
        Panel contentPanel = new Panel(new GridLayout(4));
        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        window.getCursorPosition();
        gridLayout.setHorizontalSpacing(4);
        window.setHints(Arrays.asList(Window.Hint.NO_POST_RENDERING, Window.Hint.FULL_SCREEN));
        Table table = CardsTable.get(textGUI);
        contentPanel.addComponent(table);
        contentPanel.addComponent(
                new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));
        contentPanel.addComponent(AddCard.button(textGUI));
        contentPanel.addComponent(RemoveCard.button(textGUI));
        contentPanel.addComponent(
                new Button("Close", window::close).setLayoutData(
                        GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        screen.refresh();
        window.setComponent(contentPanel);
        textGUI.addWindowAndWait(window);
    }
}
