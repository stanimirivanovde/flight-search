package stan.flightsearch;

/**
 * This class helps generate kayak.com URLs. It implements all hooks from the {@link SiteTemplate SiteTemplate} abstract
 * class.
 * A multi-city Kayak URL looks like this: <b>http://www.kayak.com/flights/PHL-SOF/2015-07-11/MAD-PHL/2015-08-08</b>
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class KayakTemplate extends SiteTemplate {
	//! This is the base URL that will be used when generating a URL.
	private final String m_baseUrl = "http://www.kayak.com";

	/**
	 * Constructor. The passed trip and permutationAlgorithm variables should not be null.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object specifying the permutation algorithm to use.
	 * @throws NullPointerException If the trip or permutationAlgorithm objects are null.
	 **/
	public KayakTemplate( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
		super( trip, permutationAlgorithm );
	}

	public String createMultiCityUrl( PermutationResult depart, PermutationResult returning ) { // {{{
		return String.format( 
				// Base URL
				"%s/flights/" +
				// Depart From
				"%s-" +
				// Depart To
				"%s/" +
				// Depart Date
				"%d-%02d-%02d/" +
				// Return From
				"%s-" + 
				// Return To
				"%s/" +
				// Return Date
				"%d-%02d-%02d/" +
				// Number of passangers
				"%dadults",
				m_baseUrl,
				depart.getFrom(),
				depart.getTo(),
				depart.getDate().getYear(),
				depart.getDate().getMonth(),
				depart.getDate().getDay(),
				returning.getFrom(),
				returning.getTo(),
				returning.getDate().getYear(),
				returning.getDate().getMonth(),
				returning.getDate().getDay(),
				m_trip.getNumberOfPassangers()
		);
	} // }}}

	public String createOneWayUrl( PermutationResult result ) { // {{{
		return String.format( 
				// Base URL
				"%s/flights/" +
				// From
				"%s-" +
				// To
				"%s/" +
				// Date
				"%d-%02d-%02d",
				m_baseUrl,
				result.getFrom(),
				result.getTo(),
				result.getDate().getYear(),
				result.getDate().getMonth(),
				result.getDate().getDay()
		);
	} // }}}
}
