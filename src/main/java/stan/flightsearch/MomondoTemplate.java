package stan.flightsearch;

/**
 * This class helps generate momondo.com URLs. It implements all hooks from the {@link SiteTemplate SiteTemplate} abstract
 * class.
 * A multi-city Momondo URL looks like this:
 * <b>{@code http://www.momondo.com/flightsearch/?Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MAD&SDP0=30-04-2016&SO1=BCN&SD1=PHL&SDP1=08-05-2016&AD=1&TK=ECO&DO=false&NA=false#Search=true&TripType=4&SegNo=2&SO0=PHL&SD0=MAD&SDP0=30-04-2016&SO1=BCN&SD1=PHL&SDP1=08-05-2016&AD=1&TK=ECO&DO=false&NA=false}</b>
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class MomondoTemplate extends SiteTemplate {
	//! This is the base URL that will be used when generating a URL.
	private final String m_baseUrl = "http://www.momondo.com";

	/**
	 * Constructor. The passed trip and permutationAlgorithm variables should not be null.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object specifying the permutation algorithm to use.
	 * @throws NullPointerException If the trip or permutationAlgorithm objects are null.
	 **/
	public MomondoTemplate( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
		super( trip, permutationAlgorithm );
	}

	public String createMultiCityUrl( PermutationResult depart, PermutationResult returning ) { // {{{
		// Lets craft the right URL:
		String craftedUrl = String.format(
				// Base URL
				"%s/" +
				// trip type
				"flightsearch/" +
				// trip type again
				"?Search=true&TripType=4" +
				// Something I don't understand
				"&SegNo=2", 
				m_baseUrl
		);
		return String.format(
				// Building the URL string
				// Crafted URL
				"%s"+
				// First Origin
				"&SO0=%s" +
				// First Destination
				"&SD0=%s" +
				// First date
				"&SDP0=%02d-%02d-%d" +
				// Second Origin
				"&SO1=%s" +
				// Second Destination
				"&SD1=%s" +
				// Second date
				"&SDP1=%02d-%02d-%d" +
				// Number of passangers
				"&AD=%d" +
				// Final tail of the query
				"&TK=ECO&DO=false&NA=false" +
				// For some reason we need to enter the trip again
				"#Search=true&TripType=4&SegNo=2" +
				// First Origin
				"&SO0=%s" +
				// First Destination
				"&SD0=%s" +
				// First Date
				"&SDP0=%02d-%02d-%d" +
				// Second Origin
				"&SO1=%s" +
				// Second Destination
				"&SD1=%s" +
				// Second Date
				"&SDP1=%02d-%02d-%d" +
				// Number of passangers
				"&AD=%d" +
				// Final tail of the query
				"&TK=ECO&DO=false&NA=false",
				craftedUrl,
				depart.getFrom(),
				depart.getTo(),
				depart.getDate().getDay(),
				depart.getDate().getMonth(),
				depart.getDate().getYear(),
				returning.getFrom(),
				returning.getTo(),
				returning.getDate().getDay(),
				returning.getDate().getMonth(),
				returning.getDate().getYear(),
				m_trip.getNumberOfPassangers(),
				// Second part of the query with the same information
				depart.getFrom(),
				depart.getTo(),
				depart.getDate().getDay(),
				depart.getDate().getMonth(),
				depart.getDate().getYear(),
				returning.getFrom(),
				returning.getTo(),
				returning.getDate().getDay(),
				returning.getDate().getMonth(),
				returning.getDate().getYear(),
				m_trip.getNumberOfPassangers()
		);
	} // }}}

	public String createOneWayUrl( PermutationResult result ) { // {{{
		// TODO: implement this.
		return new String();
	} // }}}
}
