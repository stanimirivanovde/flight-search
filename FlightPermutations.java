import java.util.ArrayList;
import java.util.List;

public class FlightPermutations {
	private TravelInfo flightInfo;

	public FlightPermutations( TravelInfo flightInfo ) {
		this.flightInfo = flightInfo;
	}

	public ArrayList<String> generate() {
		ArrayList<String> permutations = new ArrayList<String>();
		List<String> origins = flightInfo.getOrigins();
		List<String> destinations = flightInfo.getDestinations();
		List<String> dates = flightInfo.getDates();

		for( String origin : origins ) {
			for( String destination : destinations ) {
				for( String date : dates ) {
					permutations.add( String.format( "%s-%s/%s", origin, destination, date ) );
				}
			}
		}
		return permutations;
	}
}
