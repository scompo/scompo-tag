/**
 * 
 */
package it.scompo.scompotagger.modules.filereader;

/**
 * Implements a song.
 * 
 * @author mscomparin
 * @version 1.0
 */
public class Song {
	
	private String name;
	private Integer number;
	private Integer length;
	
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
	
	
}
