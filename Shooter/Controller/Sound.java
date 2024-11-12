package Controller;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;

public class Sound {
    String Path = "Shooter/SoundsWav/Sound/";
    private Clip clip;
    String filePath;
    private String[] filePaths = {
        Path+"parry.wav",
        Path+"levelup.wav",
        Path+"dooropen.wav",
        Path+"coin.wav",
        Path+"blocked.wav",
        Path+"hitmonster.wav",
        Path+"fanfare.wav",
        Path+"powerup.wav",
        Path+"gameover.wav",
        Path+"Dungeon.wav",
        Path+"bb.wav",
        Path+"ff.wav",
        Path+"Merchant.wav",
    };

    public Sound(int indice) {
        this.filePath = filePaths[indice];
        loadClip();
    }

    // Load the audio clip from file
    private void loadClip() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play the sound once
    public void play() {
        if (clip != null) {
            stop();  // Stop previous instance if any
            clip.setFramePosition(0);  // Rewind to beginning
            clip.start();
        }
    }

    // Play the sound in a loop
    public void loop() {
        if (clip != null) {
            stop();  // Stop previous instance if any
            clip.setFramePosition(0);  // Rewind to beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // Stop the sound
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.flush();
        }
    }

    // Release resources to avoid memory leaks
    public void close() {
        if (clip != null) {
            stop();
            clip.close();
        }
    }

}



