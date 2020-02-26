import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class PrintMenu {

    public static void printMenu() throws IOException, InterruptedException {

        Main.terminal.setForegroundColor(TextColor.ANSI.CYAN);
//        Main.terminal.setBackgroundColor(TextColor.ANSI.BLUE);
        Main.terminal.enableSGR(SGR.BOLD);
        Print.print(Main.terminal, "------CLUB SLEAZERS-----", 28, 8);
        Main.terminal.setForegroundColor(TextColor.ANSI.MAGENTA);
//        Main.terminal.setBackgroundColor(TextColor.ANSI.BLUE);
        Print.print(Main.terminal, "1 start new game", 32, 10);
        Main.terminal.setForegroundColor(TextColor.ANSI.GREEN);
        Print.print(Main.terminal, "------Instructions-----", 28, 13);
        Print.print(Main.terminal, "You are in a club, and you just want to dance...", 5, 14);
        Print.print(Main.terminal, "Unfortunately, others also want to dance... but with/on YOU!", 5, 15);
        Print.print(Main.terminal, "Your goal is to stay away from the SLEAZERS, for as long as possible", 5, 16);
        Print.print(Main.terminal, "Drink VODKA REDBULL('\u2605') for a temporary speed boost.", 5, 17);


        Thread.sleep(5); // might throw InterruptedException
        Main.keyStroke = Main.terminal.pollInput();

        if (Main.keyStroke != null) {
            Main.menuChoice = Main.keyStroke.getCharacter();
        }



    }
}
