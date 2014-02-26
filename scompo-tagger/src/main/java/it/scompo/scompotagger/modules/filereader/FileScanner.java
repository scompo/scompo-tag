package it.scompo.scompotagger.modules.filereader;

import it.scompo.scompotagger.modules.songs.Record;
import it.scompo.scompotagger.modules.songs.Song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrMatcher;
import org.apache.commons.lang3.text.StrTokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

/**
 * Implements a FileScanner.
 * 
 * @author mscomparin
 * @version 1.0
 */
public class FileScanner {
	
	private static final Logger logger = LogManager
			.getLogger(FileScanner.class);
	
	private static final String DELIMITERS = "–-";
	
	/**
	 * Empty constructor.
	 */
	public FileScanner(){
		super();
	}
	
	/**
	 * Returns a {@link Record} from a file.
	 * 
	 * @return a {@link Record} from a file.
	 * @throws IOException 
	 */
	public static Record getRecordProperties(File fileToRead) throws IOException {
		Record result = null;
		Validate.notNull(fileToRead);
		result = readFile(fileToRead);
		return result;
	}
	
	public static List<AudioFile> getAllAudioFiles(List<File> dir) throws Exception{
		List<AudioFile> result = new ArrayList<AudioFile>();
		for (File file : dir) {
			result.add(AudioFileIO.read(file));
		}
		return result;
	}

	/**
	 * Reads a file and scans each song.
	 * 
	 * @param fileToRead
	 * @return
	 * @throws IOException 
	 */
	private static Record readFile(File fileToRead) throws IOException{
		Record record = new Record();
		BufferedReader reader = null;
		Boolean intestazione = true;
		Integer counter = 1;
		try {
			reader = new BufferedReader(new FileReader(fileToRead));
			while(reader.ready()){
				if (intestazione) {
					String row = reader.readLine();
					StrTokenizer strTokenizer = new StrTokenizer(row, StrMatcher.charSetMatcher(DELIMITERS));
					record.setName(strTokenizer.nextToken().trim());
					record.setAuthor(strTokenizer.nextToken().trim());
					record.setYear(strTokenizer.nextToken().trim());
					intestazione = false;
				}else{
					String row = reader.readLine();
					Song song = getSongFromString(row);
					song.setNumber(counter);
					song.setRecord(record);
					record.addTrack(song);
					counter++;
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getStackTrace());
			throw e;
		} catch (IOException e) {
			logger.error(e.getStackTrace());
			reader.close();
			throw e;
		}
		reader.close();
		return record;
	}

	private static Song getSongFromString(String row) {
		Song song = new Song();
		StrTokenizer strTokenizer = new StrTokenizer(row, StrMatcher.charSetMatcher(DELIMITERS));
		song.setName(strTokenizer.next().trim());
		String lenght = strTokenizer.next().trim();
		song.setLength(getLengthFromString(lenght));
		return song;
	}

	private static Integer getLengthFromString(String str) {
		StrTokenizer strTokenizer = new StrTokenizer(str, StrMatcher.charSetMatcher(":"));
		Integer min = Integer.parseInt(strTokenizer.next().trim());
		Integer sec = Integer.parseInt(strTokenizer.next().trim());
		return (min * 60) + sec;
	}
}
