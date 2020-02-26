import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Print {

    public static void printInstructions() throws IOException, InterruptedException {
        Main.terminal.setForegroundColor(TextColor.ANSI.GREEN);
        Print.print(Main.terminal, "------Instructions-----", 28, 8);
        Print.print(Main.terminal, "You are in a club, and you just want to dance...", 5, 10);
        Print.print(Main.terminal, "Unfortunately, others also want to dance... but with/on YOU!", 5, 11);
        Print.print(Main.terminal, "Your goal is to stay away from the SLEAZERS, for as long as possible", 5, 12);
        Print.print(Main.terminal, "Drink VODKA REDBULL('\u2605') for a temporary speed boost.", 5, 13);

    }
    public static void printCredits() throws IOException, InterruptedException {
        Main.terminal.setForegroundColor(TextColor.ANSI.BLUE);
        Print.print(Main.terminal, "------Credits-----", 15, 8);
        Print.print(Main.terminal, "Code, and awesome spirit: Angela", 15, 10);
        Print.print(Main.terminal, "Code, and awesome hand stands: Karoline", 15, 11);
        Print.print(Main.terminal, "Code, and awesome, sexy voice: Christian", 15, 12);
        Print.print(Main.terminal, "Code, and handsome looks: Mirdon", 15, 13);


    }

    public static void print(Terminal terminal, String text, int x, int y) throws IOException {

        terminal.setCursorPosition(x, y);

        int stringLength = text.toCharArray().length;

        for (int i = 0; i < stringLength; i++) {

            terminal.putCharacter(text.charAt(i));


        }

    }
}
