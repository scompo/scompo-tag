package it.scompo.scompotagger;

import it.scompo.scompotagger.modules.filereader.FileScanner;
import it.scompo.scompotagger.modules.songs.Record;
import it.scompo.scompotagger.modules.songs.Song;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.lang3.Validate;
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
	
	private final static Set<String> musicFiles = new HashSet<String>(Arrays.asList(".mp3",".flac"));
	private final static Set<String> tracklistFiles = new HashSet<String>(Arrays.asList(".txt"));
	
    public static void main( String[] args ) throws Exception
    {
    	File directory = new File(args[0]);
    	Validate.isTrue(directory.isDirectory(),"Sprecify a directory!");
    	logger.debug("folder: " + args[0]);
    	List<File> songs = getMusicFiles(directory,musicFiles);
    	List<File> tracklists = getMusicFiles(directory, tracklistFiles);
    	List<Record> records = new ArrayList<Record>();
    	List<Song> allSongs = new ArrayList<Song>();
    	List<AudioFile> audioFiles = FileScanner.getAllAudioFiles(songs);
    	for (File file : tracklists) {
			records.add(FileScanner.getRecordProperties(file));
		}
    	for (Record record : records) {
			for (Integer i=1; i<=record.getTrackMap().size();i++) {
				logger.debug(record.getTrackMap().get(i));
				allSongs.add(record.getTrackMap().get(i));
			}
		}
    	logger.info("Found "+allSongs.size() +" songs.");
    	logger.info("Found "+audioFiles.size() +" audio files.");
    	Collections.sort(allSongs);
    	Collections.sort(audioFiles, new Comparator<AudioFile>() {
			public int compare(AudioFile o1, AudioFile o2) {
				return (o1.getAudioHeader().getTrackLength() > o2.getAudioHeader().getTrackLength())?
						+1:(o1.getAudioHeader().getTrackLength() < o2.getAudioHeader().getTrackLength())?-1:0;
			}
		} );
    	for ( int i = 0;i<allSongs.size();i++) {
			logger.info("Song: " + allSongs.get(i) +" -> " + audioFiles.get(i).getFile().getName());
			audioFiles.get(i).getTag().setField(FieldKey.TITLE, allSongs.get(i).getName());
			audioFiles.get(i).getTag().setField(FieldKey.ALBUM, allSongs.get(i).getRecord().getName());
			audioFiles.get(i).getTag().setField(FieldKey.YEAR, allSongs.get(i).getRecord().getYear());
			AudioFileIO.write(audioFiles.get(i));
		}
    }

	private static List<File> getMusicFiles(File directory, Set<String> exts) {
		List<File> result = new ArrayList<File>();
		for (File file : directory.listFiles()) {
			String fileName = file.getName();
			String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
			if (exts.contains(extension)) {
				result.add(file);
			}
		}
		return result;
	}
}
