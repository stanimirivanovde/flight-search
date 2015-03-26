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
public class FlightPermutations implements PermutationAlgorithm {
	private TravelInfo m_travelInfo;

	/**
	 * Default constructor.
	 **/
	public FlightPermutations() {}

	/**
	 * Constructor of the class.
	 * The constructor takes a populated TravelInfo object. If the
	 * passed object is null an exception will be thrown.
	 * @param travelInfo A TravelInfo object with the flight details.
	 * @throws NullPointerException If the passed TravelInfo object is null.
	 **/
	public FlightPermutations( TravelInfo travelInfo ) {
		this.setTravelInfo( travelInfo );
	}

	public void setTravelInfo( TravelInfo travelInfo ) {
		if( travelInfo == null ) {
			throw new NullPointerException( "The passed TravelInfo object is null." );
		}
		m_travelInfo = travelInfo;
	}

	public List<PermutationResult> generate() {
		List<String> origins = m_travelInfo.getOrigins();
		List<String> destinations = m_travelInfo.getDestinations();
		List<FlightDate> dates = m_travelInfo.getDates();

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
