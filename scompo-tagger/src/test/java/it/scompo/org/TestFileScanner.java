/**
 * TestFileScanner.java
 */
package it.scompo.org;

import it.scompo.scompotagger.modules.filereader.FileScanner;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mscomparin
 * @version 1.0
 */
public class TestFileScanner {
	
	private static final Logger logger = LogManager
			.getLogger(TestFileScanner.class);

	private static final String FILE_PATH_STRING = "./test.txt";
	private File f =null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		f = new File(FILE_PATH_STRING);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link it.scompo.scompotagger.modules.filereader.FileScanner#getRecordProperties(java.io.File)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetRecordProperties() throws Exception {
		logger.debug("got record: " +FileScanner.getRecordProperties(f));
	}

}
