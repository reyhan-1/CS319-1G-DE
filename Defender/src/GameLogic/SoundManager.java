package GameLogic;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private File file;
    private Clip clip;
    private AudioInputStream stream;
    private double vol;

    public SoundManager(String path){
        String musicPath = System.getProperty("user.dir") + path;
        file = new File(musicPath);
        if(!file.exists()){
            System.out.println("Audio file not found");
        }
        stream = getAudioStream();
        try {
            clip = AudioSystem.getClip();
            clip.open(stream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        clip.start();
    }

    public void setVolume(double volume){
        FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10) * 20);
        vol = volume;
        gain.setValue(dB);
    }
    public double getVolume() {
        return vol;
    }
}
