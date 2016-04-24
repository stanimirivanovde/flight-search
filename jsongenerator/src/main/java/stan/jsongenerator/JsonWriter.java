package stan.jsongenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import stan.flightsearch.Trip;

public class JsonWriter {
	private final List<Trip> m_tripList;
	private final String m_fileName;
	
	public JsonWriter( List<Trip> tripList, String fileName ) {
		m_tripList = Util.nullCheck( tripList, "trip list" );
		m_fileName = Util.nullCheck( fileName, "file name" );
	}

	public void write() {
		try {
			final File file = new File( m_fileName );
			final OutputStream outputStream = new FileOutputStream( file );
			final ObjectMapper mapper = new ObjectMapper();

			mapper.writeValue( outputStream, m_tripList );
		} catch( FileNotFoundException e ) {
			throw new RuntimeException( "The file was not found." );
		} catch( IOException e ) {
			throw new RuntimeException( "Error writing to the output stream." );
		}
	}
}

