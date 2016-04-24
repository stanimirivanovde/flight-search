package stan.flightsearch;

import java.io.File;
import java.io.IOException;

/**
 * Interface for implementing file readers.
 * This interface should be extended to create file readers. The file readers
 * must return an instance of a Trip class once they have parsed the file.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public interface FlightsReader {
	/**
	 * Sets a file to be parsed.
	 * @param file An Instance of File that will be parsed. The file needs to exist.
	 * @throws IOException Throws an exception if the file doesn't exist
	 **/
	public void setFile( File file ) throws IOException;
	/**
	 * This would return the file to parse.
	 * @return A File instance of the file to parse.
	 **/
	public File getFile();
	/**
	 * This parses the input file and creates a Trip object
	 * out of it.
	 * @return A Trip instance of the parsed file.
	 * @throws IOException Throws an exception if the parsing of the file fails.
	 **/
    public Trip[] parseFile() throws IOException;
}

