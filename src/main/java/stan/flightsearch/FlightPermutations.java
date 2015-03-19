package stan.flightsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates flight permutations based on a list of origins, destinations and dates.
 * The class computes all combinations between a flight's origins, destinations and dates.
 * The result is stored in a List &lt; {@link PermutationResult PermutationResult} &gt;.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class FlightPermutations {
	private TravelInfo flightInfo;

	/**
	 * Constructor of the class.
	 * The constructor takes a populated TravelInfo object. If the
	 * passed object is null an exception will be thrown.
	 * @param flightInfo A TravelInfo object with the flight details.
	 * @throws NullPointerException If the passed TravelInfo object is null.
	 **/
	public FlightPermutations( TravelInfo flightInfo ) {
		if( flightInfo == null ) {
			throw new NullPointerException( "The passed TravelInfo object is null." );
		}
		this.flightInfo = flightInfo;
	}

	/**
	 * Generates all permutations from the class' supplied TravelInfo object.
	 * The function will loop through the list of origins, destinations and dates
	 * and craft a new list containig all permutations.
	 * @return List &lt; {@link PermutationResult PermutationResult} &gt; of all combinations of origins, destinations and dates defined in the flightInfo class variable.
	 * @throws NullPointerException If the TravelInfo object doesn't have origins, destinations and dates defined.
	 **/
	public List<PermutationResult> generate() {
		List<String> origins = flightInfo.getOrigins();
		List<String> destinations = flightInfo.getDestinations();
		List<FlightDate> dates = flightInfo.getDates();

		if( origins == null || destinations == null || dates == null ) {
			throw new NullPointerException( "The origins, destinations or dates lists are null." );
		}

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
