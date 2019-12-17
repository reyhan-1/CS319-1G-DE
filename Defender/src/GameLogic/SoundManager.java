package GameLogic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundManager {
    private File file;
    private Clip clip;

    public SoundManager(String path){
        String musicPath = System.getProperty("user.dir") + path;
        file = new File(musicPath);
        if(!file.exists()){
            System.out.println("Audio file not found");
        }
    }

    public AudioInputStream getAudioStream(){
        try{
            return AudioSystem.getAudioInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void playSound(){
        try{

            AudioInputStream stream  = getAudioStream();
            clip = AudioSystem.getClip();

            clip.open(stream);
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setVolume(double volume){
        FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10) * 20);
        gain.setValue(dB);

    }
}
