package stan.flightsearch;

/**
 * This class helps generate google.com URLs. It implements all hooks from the {@link SiteTemplate SiteTemplate} abstract
 * class.
 * A multi-city Google URL looks like this: <b>https://www.google.com/flights/#search;iti=PHL_VAR_2015-03-13*MAD_PHL_2015-03-20;tt=m</b>
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class GoogleTemplate extends SiteTemplate {
	//! This is the base URL that will be used when generating a URL.
	private final String m_baseUrl = "https://www.google.com/flights/#search;iti=";

	/**
	 * Constructor. The passed trip and permutationAlgorithm variables should not be null.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object specifying the permutation algorithm to use.
	 * @throws NullPointerException If the trip or permutationAlgorithm objects are null.
	 **/
	public GoogleTemplate( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
		super( trip, permutationAlgorithm );
	}

	public String createMultiCityUrl( PermutationResult depart, PermutationResult returning ) { // {{{
		return String.format(
				// BaseURL
				"%s" +
				// Depart From
				"%s_" +
				// Depart To
				"%s_" +
				// Depart Date
				"%d-%02d-%02d*" +
				// Return From
				"%s_" +
				// Return To
				"%s_" +
				// Return Date
				"%d-%02d-%02d" +
				// End of URL
				";tt=m" +
				// Number of passangers
				";px=%d",
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
		// TODO: implement this.
		return new String();
	} // }}}
}
