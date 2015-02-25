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
	private String fileName = "";

	public FlightsReader( String fileName ) {
		this.fileName = fileName;
	}

	public void setFileName( String file ) {
		this.fileName = file;
	}
	public String getFileName() {
		return fileName;
	}

    public Trip parseJson() {
		try {
			ObjectMapper om = new ObjectMapper();
			Trip t = om.readValue( new File( fileName ), Trip.class );
			t.print();
			return t;
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return null;

		/*
		// List of all the trips
		List<Trip> tripList = new ArrayList<Trip>();
		try {
			System.out.println( "Opening the file: " + fileName );
			JsonParser jsonParser = new JsonFactory().createParser( new File( fileName ) );

			ObjectMapper objectMapper = new ObjectMapper();

			Trip currentTrip = new Trip();
			int count = 0;
			while( !jsonParser.isClosed() ) {
				jsonParser.nextToken();
				String name = jsonParser.getCurrentName();
				//System.out.println( "Parsing the current name: " + name + " and the text: " + jsonParser.getText() );
				if( "depart".equals( name ) ) {
					TravelInfo depart = parseSection( jsonParser );
					currentTrip.setDepart( depart );
				} else if( "return".equals( name ) ) {
					TravelInfo myReturn = parseSection( jsonParser );
					currentTrip.setReturn( myReturn );
					tripList.add( currentTrip );
					currentTrip = new Trip();
				}
			}

			System.out.println( "Finally gathered " + tripList.size() + " number of trips" );
			for( Trip trip : tripList ) {
				trip.print();
			}
		} catch( Exception e ) {
			System.out.println( "The exception: " + e );
			e.printStackTrace();
		}
		return tripList;
		*/
    }

	private TravelInfo parseSection( JsonParser jsonParser ) {
		TravelInfo info = new TravelInfo();
		/*
		try {
			while( jsonParser.nextToken() != JsonToken.END_OBJECT && !jsonParser.isClosed() ) {
				String name = jsonParser.getCurrentName();
				if( "from".equals( name ) ) {
					jsonParser.nextToken();
					info.setFrom( jsonParser.getText() );
				} else if( "to".equals( name ) ) {
					jsonParser.nextToken();
					info.setTo( jsonParser.getText() );
				} else if( "date".equals( name ) ) {
					jsonParser.nextToken();
					info.setDate( jsonParser.getText() );
					// We are done with the current part
					break;
				}
			}
			// Get pass the finalizing "}" for this object
			jsonParser.nextToken();
		} catch( IOException e ) {
			System.out.println( "An exception was thrown: " + e );
		}
		*/
		return info;
	}
}

