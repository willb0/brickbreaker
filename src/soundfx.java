import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class soundfx{
	private Clip clip;
	public soundfx(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream ais=AudioSystem.getAudioInputStream(new File("src/"+filename));
		clip =AudioSystem.getClip();
		clip.open(ais);
	}
	public void start() {
		clip.start();
		clip.setFramePosition(0);
	}
}