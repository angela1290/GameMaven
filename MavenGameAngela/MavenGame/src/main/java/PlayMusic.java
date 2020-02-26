import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class PlayMusic {

    public static void main(String[] args) throws InterruptedException {
        // Spiller kun wav-filer!!
        play("music.wav");
    }

    public static synchronized void play(final String fileName) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        });
        t.start();
    }
}