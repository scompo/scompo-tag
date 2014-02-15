package it.scompo.scompotagger.modules.filereader;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements a Record.
 * 
 * @author mscomparin
 * @version 1.0
 */
public class Record{
	
	private String Name;
	private String Author;
	private String Year;
	
	/**
	 * List of tracks.
	 */
	private Map<Integer, Song> trackMap;	
	
	/**
	 * Constructor.
	 */
	public Record() {
		super();
		trackMap = new LinkedHashMap<Integer, Song>();
	}
	
	/**
	 * Add A track to the tracks of the record.
	 * 
	 * @param song the song to add.
	 */
	public void addTrack(Song song){
		trackMap.put(song.getNumber(), song);
	}
	
	/**
	 * Return a track name by bumber.
	 * 
	 * @param number
	 * @return
	 */
	public Song getTrackNameByNumber(Integer number){
		return trackMap.get(number);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return Author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		Author = author;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return Year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		Year = year;
	}
	
	
}
