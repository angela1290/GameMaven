import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {


    static boolean redBullRush = false;
    static int redBullCounter = 0;
    static int score = 0;
    static boolean clearScreen = false;
    static Character menuChoice;
    static KeyStroke keyStroke;
    static boolean go;
    static boolean inMenu = true;


    public static DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    public static Terminal terminal;

    static {
        try {
            terminal = defaultTerminalFactory.createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        terminal.setCursorVisible(false);

        do {
            PrintMenu.printMenu();
            if (menuChoice != null) {

                if (menuChoice == '\n') {
                    terminal.clearScreen();
                    go = true;
                    inMenu = false;
                }
            }

        } while (inMenu);


        Player playa = new Player(40, 12);


        int px = playa.px;
        int py = playa.py;


        final char player = '\u263a';
        final char sleazer = '\u2665';
        final char redBullVodka = '\u2605';
        Random r = new Random();


        terminal.setCursorPosition(px, py);
        terminal.putCharacter(player);
        terminal.setCursorVisible(false);


        List<Position> allSleazer = new ArrayList<>();

        Position sleaze1 = new Position(r.nextInt(70), r.nextInt(23));
        Position sleazer2 = new Position(r.nextInt(70), r.nextInt(23));
        Position sleazer3 = new Position(r.nextInt(70), r.nextInt(23));
        Position sleazer4 = new Position(r.nextInt(70), r.nextInt(23));
        Position sleazer5 = new Position(r.nextInt(70), r.nextInt(23));


        allSleazer.add(sleaze1);
        allSleazer.add(sleazer2);
        allSleazer.add(sleazer3);
        allSleazer.add(sleazer4);
        allSleazer.add(sleazer5);


        for (Position sle : allSleazer) {
            terminal.setCursorPosition(sle.x, sle.y);
            terminal.putCharacter(sleazer);
        }


       /* Position[] sleazers = new Position[20];
        for (int i = 0; i < 20; i++) {
            sleazers[i] = new Position(10 + i, 10);
        }

        // SleazerObsticle array to print to lanterna
        for (Position p : sleazers) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(sleazer);
        }*/


        List<FeautersPosition> redBullVodkasAll = new ArrayList<>();

        FeautersPosition redBull1 = new FeautersPosition(r.nextInt(79), r.nextInt(23));
        FeautersPosition redBull2 = new FeautersPosition(r.nextInt(79), r.nextInt(23));
        FeautersPosition redBull3 = new FeautersPosition(r.nextInt(79), r.nextInt(23));
        FeautersPosition redBull4 = new FeautersPosition(r.nextInt(79), r.nextInt(23));

        redBullVodkasAll.add(redBull1);
        redBullVodkasAll.add(redBull2);
        redBullVodkasAll.add(redBull3);
        redBullVodkasAll.add(redBull4);

        for (FeautersPosition redBull : redBullVodkasAll) {

            terminal.setCursorPosition(redBull.getRedBullX(), redBull.getRedBullY());
            terminal.putCharacter(redBullVodka);

        }

        if (go) {
            PlayMusic.play("music.wav");
        }

        while (go) {
            score++;

            do {
                Thread.sleep(0); // might throw InterruptedException
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter();

            if (c == Character.valueOf('Q')) {
                go = false;
                terminal.close();
            } else {
                go = true;
            }

            int pxOld = px;
            int pyOld = py;
            if (!redBullRush) {

                switch (type) {
                    case ArrowDown:
                        ++py;
                        break;
                    case ArrowUp:
                        --py;
                        break;
                    case ArrowLeft:
                        --px;
                        break;
                    case ArrowRight:
                        ++px;
                        break;
                }
            } else if (redBullRush) {

                if (redBullCounter <= 0) {
                    redBullRush = false;
                }
                redBullCounter--;

                switch (type) {
                    case ArrowDown:
                        ++py;
                        ++py;
                        ++py;
                        ++py;

                        break;
                    case ArrowUp:
                        --py;
                        --py;
                        --py;
                        --py;
                        break;

                    case ArrowLeft:
                        --px;
                        --px;
                        --px;
                        --px;
                        break;

                    case ArrowRight:
                        ++px;
                        ++px;
                        ++px;
                        ++px;
                        break;
                }
            }


            for (Position s : allSleazer) {

                int sOldX = s.getX();
                int sOldY = s.getY();

                if (!(px == s.getX() && py == s.getY())) {
                    System.out.println("Player Position: " + px + "," + py);
                    System.out.println("Sleazer Position: " + s.getX() + "," + s.getY());
                    //X
                    if (px > s.getX()) {
                        s.setX(s.getX() + 1);

                    }
                    if (px < s.getX()) {
                        s.setX(s.getX() - 1);
                    }
                    //Y
                    if (py > s.getY()) {
                        s.setY(s.getY() + 1);
                    }
                    if (py < s.getY()) {
                        s.setY(s.getY() - 1);
                    }
                    System.out.println();


                    terminal.setCursorPosition(sOldX, sOldY);
                    terminal.putCharacter(' ');
                    terminal.setCursorPosition(s.getX(), s.getY());
                    terminal.putCharacter(sleazer);

                }


                boolean crashIntoSleazer = false;

                for (Position sleaz : allSleazer) {
                    if (sleaz.x == px && sleaz.y == py) {
                        crashIntoSleazer = true;
                    }
                }

                if (crashIntoSleazer) {

                    terminal.clearScreen();

                    px = pxOld;
                    py = pyOld;
                    PlayMusic.play("GameOver.wav");

                    terminal.setForegroundColor(TextColor.ANSI.RED);
                    terminal.enableSGR(SGR.BOLD);
                    terminal.enableSGR(SGR.BLINK);
                    terminal.clearScreen();

                    Print.print(terminal, "Game over", 36, 12);
                    Print.print(terminal, "Score: " + score, 36, 13);
                    terminal.setCursorPosition(pxOld, pyOld);
                    terminal.putCharacter(' ');
                    go = false;


                } else {

                    terminal.setCursorPosition(pxOld, pyOld);
                    terminal.putCharacter(' ');
                    terminal.setCursorPosition(px, py);
                    terminal.putCharacter(player);
                }


            }

            for (FeautersPosition redBull : redBullVodkasAll) {

                boolean crashIntoRedBull = false;

                int redbullVodkaOldX = redBull.redBullX;
                int redbullVodkaOldY = redBull.redBullY;

                for (FeautersPosition redbull : redBullVodkasAll) {
                    if (redbull.getRedBullX() == px && redbull.getRedBullY() == py) {
                        crashIntoRedBull = true;
                    }
                }

                if (crashIntoRedBull) {

                    redBullRush = true;
                    redBullCounter = 10;
                    PlayMusic.play("OhYeah.Wav");
                    terminal.setCursorPosition(pxOld, pyOld);
                    terminal.putCharacter(' ');
                    terminal.setCursorPosition(px, py);
                    terminal.putCharacter(player);

                } else {

                    terminal.setCursorPosition(pxOld, pyOld);
                    terminal.putCharacter(' ');
                    terminal.setCursorPosition(px, py);
                    terminal.putCharacter(player);
                }

                terminal.flush();

            }

        }

    }

}


