package classes.cli;
/**
 * @author Le Thien Son - s3977955
 */

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyType;
public class Home {
    public static  void drawBox(TextGraphics tg, int terminalWidth, int terminalHeight){
        tg.drawLine(new TerminalPosition(0, 0), new TerminalPosition(terminalWidth - 1, 0), '─');
        tg.drawLine(new TerminalPosition(0, 0), new TerminalPosition(0, terminalHeight - 1), '│');
        tg.drawLine(new TerminalPosition(terminalWidth - 1, terminalHeight - 1), new TerminalPosition(0, terminalHeight - 1), '─');
        tg.drawLine(new TerminalPosition(terminalWidth - 1, terminalHeight - 1), new TerminalPosition(terminalWidth - 1, 0), '│');
        tg.putString(0, 0, "┌");
        tg.putString(terminalWidth - 1, 0, "┐");
        tg.putString(0, terminalHeight - 1, "└");
    }

    public static void displayHomeScreen() throws IOException {
        //Set up thingy
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TextGraphics tg = screen.newTextGraphics();
        screen.startScreen();

        // Get terminal size and draw box
        TerminalSize terminalSize = terminal.getTerminalSize();
        int terminalWidth = terminalSize.getColumns();
        int terminalHeight = terminalSize.getRows();
        drawBox(tg, terminalWidth, terminalHeight);
        tg.putString(3, 3, "╭━━╮    　　　   　　　　　╭━━━╮      ");
        tg.putString(3, 4, "╰┫┣╯    　　　   　　　　　┃╭━╮┃      ");
        tg.putString(3, 5, " ┃┃╭━╮╭━━┳╮╭┳━┳━━┳━╮╭━━┳━━╮┃┃ ┃┣━━┳━━╮");
        tg.putString(3, 6, " ┃┃┃╭╮┫━━┫┃┃┃╭┫╭╮┃╭╮┫╭━┫┃━┫┃╰━╯┃╭╮┃╭╮┃");
        tg.putString(3, 7, "╭┫┣┫┃┃┣━━┃╰╯┃┃┃╭╮┃┃┃┃╰━┫┃━┫┃╭━╮┃╰╯┃╰╯┃");
        tg.putString(3, 8, "╰━━┻╯╰┻━━┻━━┻╯╰╯╰┻╯╰┻━━┻━━╯╰╯ ╰┫╭━┫╭━╯");
        tg.putString(3, 9, "                               ┃┃ ┃┃  ");
       tg.putString(3, 10, "                               ╰╯ ╰╯  ");
        tg.putString(3, terminalHeight - 4, "Press Right Arrow key → to continue");
        tg.putString(3, terminalHeight - 3, "Press Esc to quit");


        screen.refresh();

        boolean keepRunning = true;
        while (keepRunning){
            KeyStroke keyPressed = terminal.pollInput();

            if(keyPressed != null){
                System.out.println(keyPressed);
                switch (keyPressed.getKeyType()){
                    case Escape:
                        terminal.bell();
                        keepRunning = false;
                        break;
                    case EOF:
                        terminal.bell();
                        keepRunning = false;
                        break;
                    case ArrowRight:
                        screen.clear();
                        drawBox(tg, terminalWidth, terminalHeight);
                        tg.putString(2, terminalHeight - 6,"Please choose the desired function:");
                        tg.putString(2, terminalHeight - 5,"1. Manage Customers");
                        tg.putString(2, terminalHeight - 4,"2. Manage Claims");
                        tg.putString(2, terminalHeight - 3,"3. Reports");
                        screen.refresh();
                        break;
                    case Character:
                        Character choice =  keyPressed.getCharacter();
                        screen.refresh();
                        switch (choice) {
                            case '1':
                                // Call method to manage customers
                                tg.putString(2, terminalHeight - 2, "│                                                                 ");
                                tg.putString(2, terminalHeight - 2, "You selected: Manage Customers");
                                screen.refresh();
                                break;
                            case '2':
                                // Call method to manage claims
                                tg.putString(2, terminalHeight - 2, "│                                                                 ");
                                tg.putString(2, terminalHeight - 2, "You selected: Manage Claims");
                                screen.refresh();
                                break;
                            case '3':
                                // Call method to generate reports
                                tg.putString(2, terminalHeight - 2, "│                                                                 ");
                                tg.putString(2, terminalHeight - 2, "You selected: Reports");
                                screen.refresh();
                                break;
                            default:
                                tg.putString(2, terminalHeight - 2, "│                                                                 ");
                                tg.putString(2, terminalHeight - 2, "Invalid choice. Please enter a number between 1 and 3.");
                                screen.refresh();
                                break;
                        }
                        break;
                }
            }
        }

        // Close the screen
        screen.stopScreen();
    }
}
