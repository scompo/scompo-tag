/**
 * 
 */
package it.scompo.scompotagger.modules.songs;

/**
 * Implements a song.
 * 
 * @author mscomparin
 * @version 1.0
 */
public class Song implements Comparable<Song>{
	
	private String name;
	private Integer number;
	private Integer length;
	private Record record;
	
	/**
	 * Creates a song
	 * 
	 * @param name name
	 * @param number number
	 * @param length length
	 */
	public Song(String name, Integer number, Integer length) {
		super();
		this.name = name;
		this.number = number;
		this.length = length;
	}
	
	/**
	 * Creates a null song.
	 */
	public Song(){
		this(null,null,null);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * Gets the record.
	 *
	 * @return the record.
	 */
	public Record getRecord() {
		return record;
	}

	/**
	 * Sets record.
	 *
	 * @param record the record to set
	 */
	public void setRecord(Record record) {
		this.record = record;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Song [name=" + name + ", number=" + number + ", length="
				+ length + "]";
	}

	/**
	 * Sorts by length.
	 */
	public int compareTo(Song o) {
		return this.length.compareTo(o.length);
	}
	
	
}
