package classes.cli.Claim;
/**
 * @author Le Thien Son - s3977955
 */
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Arrays;

public class ManageClaims {
    public static void view(Screen screen) throws IOException {
        screen.clear();
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window window = new BasicWindow("Manage Claims");
        Panel contentPanel = new Panel(new GridLayout(4));
        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        window.getCursorPosition();
        gridLayout.setHorizontalSpacing(4);
        window.setHints(Arrays.asList(Window.Hint.NO_POST_RENDERING, Window.Hint.FULL_SCREEN));
        contentPanel.addComponent(
                new EmptySpace()
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));
        Table table =  ClaimsTable.get(textGUI);
        table.setLayoutData(
                GridLayout.createLayoutData(
                        GridLayout.Alignment.BEGINNING,
                        GridLayout.Alignment.BEGINNING,
                        true,
                        true,
                        30,
                        20)
        );
        contentPanel.addComponent(table);
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));
        contentPanel.addComponent(AddClaim.button(textGUI));
        contentPanel.addComponent(RemoveClaim.button(textGUI));
        contentPanel.addComponent(
                new Button("Close", window::close).setLayoutData(
                        GridLayout.createHorizontallyEndAlignedLayoutData(4)));
        screen.refresh();
        window.setComponent(contentPanel);
        textGUI.addWindowAndWait(window);
    }
}
