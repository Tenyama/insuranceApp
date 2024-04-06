package classes.cli;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class MenuScreen {
    public static void display(Terminal terminal, Screen screen, TextGraphics tg, TerminalSize terminalSize) throws IOException{
        screen.clear();
        tg.setForegroundColor(TextColor.ANSI.RED);
        tg.putString(2, 1,"Press esc to quit");
        tg.setForegroundColor(TextColor.ANSI.DEFAULT);
        tg.putString(2, terminalSize.getRows() - 7,"Please choose the desired function:");
        tg.putString(2, terminalSize.getRows() - 6,"1. Manage Customers");
        tg.putString(2, terminalSize.getRows() - 5,"2. Manage Cards");
        tg.putString(2, terminalSize.getRows() - 4,"3. Manage Claims");
    }
}
