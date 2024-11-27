package manager;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
    String Path = "Shooter/SoundsWav/Sound/";
    private String[] filePaths = {
        Path+"parry.wav", // 0 bomb
        Path+"levelup.wav", // 1 new level
        Path+"dooropen.wav", // 2 shoot
        Path+"coin.wav", // 3 Potion
        Path+"blocked.wav", // 4 Hit Monster
        Path+"hitmonster.wav", // 5 Death Monster
        Path+"fanfare.wav", //6 win game
        Path+"powerup.wav",// 7 Power Up
        Path+"gameover.wav",// 8 Lose
        Path+"Dungeon.wav",// 9 while playing
        Path+"bb.wav", // 10 intro 
        Path+"ff.wav", // 11 Music Boss
        Path+"Merchant.wav", // 12 Level2
    };
    private Clip [] clips = new  Clip[filePaths.length] ;


    public Sound() {
        loadClips();
    }

    // Load the audio clip from file
    private void loadClips() {
        try {
            for (int i = 0; i < filePaths.length ; i++) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePaths[i]));
                clips[i] = AudioSystem.getClip();
                clips[i].open(audioInputStream);
            }
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play the sound once
    public void play(int ind) {
        if (clips[ind] != null) {
            stop(ind);  // Stop previous instance if any
            clips[ind].setFramePosition(0);  // Rewind to beginning
            clips[ind].start();
        }
    }

    // Play the sound in a loop
    public void loop(int ind) {
        if (clips[ind] != null) {
            stop(ind);  // Stop previous instance if any
            clips[ind].setFramePosition(0);  // Rewind to beginning
            clips[ind].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // Stop the sound
    public void stop(int ind) {
        if (clips[ind] != null && clips[ind].isRunning()) {
            clips[ind].stop();
            clips[ind].flush();
        }
    }

    // Release resources to avoid memory leaks
    public void close(int ind) {
        if (clips[ind] != null) {
            stop(ind);
            clips[ind].close();
        }
    }

    public void muteAll() {
        for (Clip clip : clips) {
            if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(volumeControl.getMinimum()); // Set volume to minimum (mute)
            }
        }
    }

    public void unmuteAll() {
        for (Clip clip : clips) {
            if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(0.0f); // Restore volume to normal
            }
        }
    }
}

