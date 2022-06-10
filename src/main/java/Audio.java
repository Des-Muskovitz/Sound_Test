import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio implements AutoCloseable{


    private Long currentFrame;
    private Clip clip;

    private String status;

    private AudioInputStream audioInputStream;
    private static File file;

    private String[] options;


    public Audio(String[] options, File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        this.options = options;
    }


    public void displayChoices(){
        for(int i = 0; i < options.length; i++){
            System.out.println(i+1 + ") " + options[i]);
        }
        System.out.println("Enter in your choice.");
    }

    public void selectedChoice(String input) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch(input.toLowerCase()){
            case "play":
                play();
                break;
            case "pause":
                pause();
                break;
            case "resume":
                resume();
                break;
            case "stop":
                stop();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void play(){
        status = "play";

        clip.start();
    }

    private void pause(){
        if(status.equals("paused")){
            System.out.println("Clip is already paused");
        } else{
            currentFrame = clip.getMicrosecondPosition();
            clip.stop();
            status = "paused";
        }
    }

    private void resume() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(status.equals("play")){
            System.out.println("Audio is already being played");
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        play();
    }

    private void stop(){
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                file.getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void close() throws IOException {
        clip.close();
        audioInputStream.close();
    }

    public void loadNewSoundFile(File file) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        close();
        audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }
}