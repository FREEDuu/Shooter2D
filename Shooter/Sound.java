import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound{
    
    int SoundCounter = 0;
    Clip clip;
    AudioInputStream inputStream;
    URL[] soundURL = new URL[12];


    public Sound(){
        String Path = "SoundsWav/Sound/";
        soundURL[0] = getClass().getResource(Path+"parry.wav");
        soundURL[1] = getClass().getResource(Path+"levelup.wav");
        soundURL[2] = getClass().getResource(Path+"dooropen.wav");
        soundURL[3] = getClass().getResource(Path+"coin.wav");
        soundURL[4] = getClass().getResource(Path+"blocked.wav");
        soundURL[5] = getClass().getResource(Path+"hitmonster.wav");
        soundURL[6] = getClass().getResource(Path+"fanfare.wav");
        soundURL[7] = getClass().getResource(Path+"powerup.wav");
        soundURL[8] = getClass().getResource(Path+"gameover.wav");
        soundURL[9] = getClass().getResource(Path+"Dungeon.wav");
        soundURL[10] = getClass().getResource(Path+"bb.wav");
        soundURL[11] = getClass().getResource(Path+"ff.wav");


    }
    public void setFile(int ind) {

     try{
        AudioInputStream audioStream = AudioSystem.getAudioInputStream((soundURL[ind]));
        clip = AudioSystem.getClip();
        clip.open(audioStream);
 

    }catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
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

    public void PlaySoundEffect(int i){
        this.setFile(i);
        this.play();
    }

    public void LoopMusicEffect(int i){
        this.setFile(i);
        this.play();
        this.loop();
    }
}

