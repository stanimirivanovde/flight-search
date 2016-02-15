package stan.flightsearch;

/**
 * This class helps generate flighthub.com URLs. It implements all hooks from the {@link SiteTemplate SiteTemplate} abstract
 * class.
 * A multi-city FlightHub URL looks like this:
 * <b>{@code http://www.flighthub.com/flight/search?seg0_from=IAD&seg0_date=2016-03-29&seg0_to=MAD&seg1_from=BCN&seg1_date=2016-04-05&seg1_to=IAD&num_adults=3&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy&campaign=1&search_id=97407f29cfcab1ed645d1ccd223c2abb&flexible_search_id=d5cd985a7d1014ad832323b922d4ab0c}</b>
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class FlightHubTemplate extends SiteTemplate {
	//! This is the base URL that will be used when generating a URL.
	private final String m_baseUrl = "http://www.flighthub.com/flight/search?";

	/**
	 * Constructor. The passed trip and permutationAlgorithm variables should not be null.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object specifying the permutation algorithm to use.
	 * @throws NullPointerException If the trip or permutationAlgorithm objects are null.
	 **/
	public FlightHubTemplate( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
		super( trip, permutationAlgorithm );
	}

	public String createMultiCityUrl( PermutationResult depart, PermutationResult returning ) { // {{{
		return String.format(
				// Base URL
				"%s" +
				// Depart From
				"seg0_from=%s" +
				// Depart Date
				"&seg0_date=%d-%02d-%02d" +
				// Depart To
				"&seg0_to=%s" +
				// Return From
				"&seg1_from=%s" + 
				// Return Date
				"&seg1_date=%d-%02d-%02d" +
				// Return To
				"&seg1_to=%s" +
				// Number of passangers
				"&num_adults=%d" +
				// Final parameters
				"&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy",
				m_baseUrl,
				depart.getFrom().toLowerCase(),
				depart.getDate().getYear(),
				depart.getDate().getMonth(),
				depart.getDate().getDay(),
				depart.getTo().toLowerCase(),
				returning.getFrom().toLowerCase(),
				returning.getDate().getYear(),
				returning.getDate().getMonth(),
				returning.getDate().getDay(),
				returning.getTo().toLowerCase(),
				m_trip.getNumberOfPassangers()
		);
	} // }}}

	public String createOneWayUrl( PermutationResult result ) { // {{{
		// TODO: implement this.
		return new String();
	} // }}}
}
