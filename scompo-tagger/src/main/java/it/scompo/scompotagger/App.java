package it.scompo.scompotagger;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LogManager.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	File file = new File(args[0]);
    	logger.info(args[0]);
    	AudioFile audioFile = null;
        try {
        	audioFile = AudioFileIO.read(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Tag tag = audioFile.getTag();
        AudioHeader audioHeader = audioFile.getAudioHeader();
        logger.info("Artist: " + tag.getFirst(FieldKey.ARTIST));
        logger.info("title: " + tag.getFirst(FieldKey.TITLE));
        logger.info("Length: " + (audioHeader.getTrackLength()/60) + ":" + (audioHeader.getTrackLength()%60));
        logger.info("Format: " + audioHeader.getFormat());
    }
}
