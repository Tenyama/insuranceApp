package classes.cli;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */
import java.io.IOException;

import classes.cli.Card.ManageCards;
import classes.cli.Claim.ManageClaims;
import classes.cli.Customer.ManageCustomers;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
public class Home {
    public static void displayHomeScreen() throws IOException {
        //Set up thingy
        Screen screen = null;

        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            TextGraphics tg = screen.newTextGraphics();



            screen.startScreen();
            screen.setCursorPosition(null);

            // Get terminal size and draw box
            TerminalSize terminalSize = terminal.getTerminalSize();
            tg.putString(3, 3, "╭━━╮    　　　   　　　　　╭━━━╮      ");
            tg.putString(3, 4, "╰┫┣╯    　　　   　　　　　┃╭━╮┃      ");
            tg.putString(3, 5, " ┃┃╭━╮╭━━┳╮╭┳━┳━━┳━╮╭━━┳━━╮┃┃ ┃┣━━┳━━╮");
            tg.putString(3, 6, " ┃┃┃╭╮┫━━┫┃┃┃╭┫╭╮┃╭╮┫╭━┫┃━┫┃╰━╯┃╭╮┃╭╮┃");
            tg.putString(3, 7, "╭┫┣┫┃┃┣━━┃╰╯┃┃┃╭╮┃┃┃┃╰━┫┃━┫┃╭━╮┃╰╯┃╰╯┃");
            tg.putString(3, 8, "╰━━┻╯╰┻━━┻━━┻╯╰╯╰┻╯╰┻━━┻━━╯╰╯ ╰┫╭━┫╭━╯");
            tg.putString(3, 9, "                               ┃┃ ┃┃  ");
            tg.putString(3, 10, "                               ╰╯ ╰╯  ");
            tg.putString(3, terminalSize.getRows() - 5, "For the best experience please fullscreen your terminal", SGR.BOLD);
            tg.setForegroundColor(TextColor.ANSI.GREEN);
            tg.putString(3, terminalSize.getRows() - 4, "Press Right Arrow key → to continue");
            tg.setForegroundColor(TextColor.ANSI.RED);
            tg.putString(3, terminalSize.getRows() - 3, "Press Esc to quit");
            tg.setForegroundColor(TextColor.ANSI.DEFAULT);
            screen.refresh();

            boolean keepRunning = true;
            boolean enteredMenu = false;
            while (keepRunning) {
                KeyStroke keyPressed = terminal.pollInput();
                if (keyPressed != null) {
                    screen.refresh();
                    switch (keyPressed.getKeyType()) {
                        case Escape:
                            terminal.bell();
                            keepRunning = false;
                            break;
                        case EOF:
                            terminal.bell();
                            keepRunning = false;
                            break;
                        case ArrowRight:
                            enteredMenu = true;
                            MenuScreen.display(terminal, screen, tg, terminalSize);
                            screen.refresh();
                            break;
                        case Character:
                            if (enteredMenu) {
                                Character choice = keyPressed.getCharacter();
                                switch (choice) {
                                    case '1':
                                        tg.putString(2, terminalSize.getRows() - 2, "                                                                  ");
                                        screen.refresh();
                                        ManageCustomers.selector(screen);
                                        MenuScreen.display(terminal, screen, tg, terminalSize);
                                        screen.refresh();
                                        break;
                                    case '2':
                                        tg.putString(2, terminalSize.getRows() - 2, "                                                                  ");
                                        ManageCards.view(screen);
                                        MenuScreen.display(terminal, screen, tg, terminalSize);
                                        screen.refresh();
                                        break;
                                    case '3':
                                        tg.putString(2, terminalSize.getRows() - 2, "                                                                  ");
                                        ManageClaims.view(screen);
                                        MenuScreen.display(terminal, screen, tg, terminalSize);
                                        screen.refresh();
                                        break;
                                    default:
                                        tg.putString(2, terminalSize.getRows() - 2, "                                                                   ");
                                        tg.setForegroundColor(TextColor.ANSI.YELLOW);
                                        tg.putString(2, terminalSize.getRows() - 2, "Invalid choice. Please enter a number between 1 and 3.");
                                        tg.setForegroundColor(TextColor.ANSI.DEFAULT);
                                        screen.refresh();
                                        break;
                                }
                            }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(screen!= null){
                try{
                    screen.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
