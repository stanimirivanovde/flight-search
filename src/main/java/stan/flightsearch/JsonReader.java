package stan.flightsearch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * A JSON file reader
 * This file reader will parse a JSON file and create a Trip instance
 * for it.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class JsonReader implements FlightsReader {
	//! The file that will be parsed
	private File file;

	/**
	 * Default constructor. The default constructor along with the
	 * setFile() function can be used to efficiently reuse a single object
	 * in order to parse multiple files.
	 **/
	public JsonReader() {}
	/**
	 * The preferred constructor. This constructor takes as input the file to be parsed.
	 * The file must exist or otherwise the constructor will throw an exception.
	 * @param file A File instance of the file that would be parsed. The file needs to exist.
	 * @throws IOException An exception if the file doesn't exist.
	 **/
	public JsonReader( File file ) throws IOException{
		// Calls the internal setter.
		this.setFile( file );
	}

	public void setFile( File file ) throws IOException {
		if( !file.exists() ) {
			throw new IOException( "File " + file + " was not found!" );
		}
		this.file = file;
	}
	public File getFile() {
		return file;
	}

    public Trip[] parseFile() throws IOException {
		ObjectMapper om = new ObjectMapper();
		Trip[] tripArray = om.readValue( file, Trip[].class );
		for( Trip t : tripArray ) {
			System.out.println( t );
		}
		return tripArray;
    }
}

