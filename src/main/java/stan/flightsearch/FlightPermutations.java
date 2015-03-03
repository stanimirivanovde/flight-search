package stan.flightsearch;

import java.util.ArrayList;
import java.util.List;

public class FlightPermutations {
	private TravelInfo flightInfo;

	public FlightPermutations( TravelInfo flightInfo ) {
		this.flightInfo = flightInfo;
	}

	public List<PermutationResult> generate() {
		List<String> origins = flightInfo.getOrigins();
		List<String> destinations = flightInfo.getDestinations();
		List<FlightDate> dates = flightInfo.getDates();

		List<PermutationResult> results = new ArrayList<PermutationResult>();
		for( String origin : origins ) {
			for( String destination : destinations ) {
				for( FlightDate date : dates ) {
					results.add( new PermutationResult( origin, destination, date ) );
				}
			}
		}
		return results;
	}
}
