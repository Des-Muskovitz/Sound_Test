import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final File CHEW_CHEW_YUM_FILE = new File("src/main/resources/ChewChewYum.wav");
    public static final File CRUNCH_CRUNCH_YUM_FILE = new File("src/main/resources/CrunchCrunchYum.wav");
    public static final File GLUG_GLUG_YUM_FILE = new File("src/main/resources/GlugGlugYum.wav");
    public static final File MUNCH_MUNCH_YUM_FILE = new File("src/main/resources/MunchMunchYum.wav");




    public static void main(String[] args) {
        String[] options = new String[] {"Play", "Pause", "Resume", "Stop"};
        try (Audio audio = new Audio(options, CHEW_CHEW_YUM_FILE);
             Scanner input = new Scanner(System.in)){
            label:
            while(true){
                String userInput = input.nextLine();
                switch (userInput) {
                    case "close":
                        break label;
                    case "new file":
                        switch (input.nextLine().toLowerCase()) {
                            case "chew chew":
                                audio.loadNewSoundFile(CHEW_CHEW_YUM_FILE);
                                break;
                            case "crunch crunch":
                                audio.loadNewSoundFile(CRUNCH_CRUNCH_YUM_FILE);
                                break;
                            case "glug glug":
                                audio.loadNewSoundFile(GLUG_GLUG_YUM_FILE);
                                break;
                            case "munch munch":
                                audio.loadNewSoundFile(MUNCH_MUNCH_YUM_FILE);
                                break;
                            default:
                                System.err.println("Invalid file");
                                break;
                        }

                        break;
                    case "play":
                        audio.selectedChoice("play");
                        break;
                }
            }
        } catch (Exception e){
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
