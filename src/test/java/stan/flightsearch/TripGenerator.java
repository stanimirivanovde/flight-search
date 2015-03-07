package stan.flightsearch;

import java.util.List;
import java.util.Arrays;

public class TripGenerator {
	public TripGenerator() {}

	public Trip generateMultiCityTrip() {
		// Generate departing flights
		List<String> departOrigins = Arrays.asList( "PHL" );
		List<String> departDestinations = Arrays.asList( "VAR" );
		List<FlightDate> departDates = Arrays.asList( new FlightDate( 3, 12, 1984 ) );
		TravelInfo depart = new TravelInfo( departOrigins, departDestinations, departDates );
		// Generate returning flights
		List<String> returnOrigins = Arrays.asList( "MAD" );
		List<String> returnDestinations = Arrays.asList( "PHL" );
		List<FlightDate> returnDates = Arrays.asList( new FlightDate( 24, 12, 1984 ) );
		TravelInfo returning = new TravelInfo( returnOrigins, returnDestinations, returnDates );
		return new Trip( depart, returning );
	}
	public Trip generateOneWayTrip( boolean isDeparting ) {
		Trip oneWayTrip = new Trip();
		// Generate departing flights
		List<String> departOrigins = Arrays.asList( "PHL" );
		List<String> departDestinations = Arrays.asList( "VAR" );
		List<FlightDate> departDates = Arrays.asList( new FlightDate( 3, 12, 1984 ) );
		TravelInfo depart = new TravelInfo( departOrigins, departDestinations, departDates );
		if( isDeparting ) {
			oneWayTrip.setDepart( depart );
		} else {
			oneWayTrip.setReturning( depart );
		}
		return oneWayTrip;
	}
	public Trip generateRoundTrip( Trip trip ) {
		// TODO: implement this
		return new Trip();
	}
}
