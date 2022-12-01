package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/res/sounds/PLUCK(OST).wav");
        soundURL[1] = getClass().getResource("/res/sounds/PICK.wav");
        soundURL[2] = getClass().getResource("/res/sounds/POWERUP.wav");
        soundURL[3] = getClass().getResource("/res/sounds/DOOR_OPEN.wav");
        soundURL[4] = getClass().getResource("/res/sounds/YOU_WIN.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}