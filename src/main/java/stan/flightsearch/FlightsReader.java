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

public class FlightsReader {
	private File file;

	public FlightsReader( File file ) {
		this.file = file;
	}

	public void setFile( File file ) {
		this.file = file;
	}
	public File getFile() {
		return file;
	}

    public Trip parseJson() {
		try {
			ObjectMapper om = new ObjectMapper();
			Trip t = om.readValue( file, Trip.class );
			System.out.println( t );
			return t;
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return null;
    }
}

