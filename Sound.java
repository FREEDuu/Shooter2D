import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound{
    
    Clip clip;
    AudioInputStream inputStream;
    String [] ss = new String[20];

    public Sound(){
        ss[0] = "Shooter/SoundsWav/song2.wav";
        ss[1] = "Shooter/SoundsWav/rec1.wav";
    }
    public void setFile(int ind) {

     try {
        inputStream = AudioSystem.getAudioInputStream(new File(ss[ind]));
        clip = AudioSystem.getClip();
        clip.open(inputStream);

     } catch (Exception e) {
        System.out.println(e);

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

