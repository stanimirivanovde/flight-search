package stan.flightsearch;

/**
 * This class helps generate hipmunk.com URLs. It implements all hooks from the {@link SiteTemplate SiteTemplate} abstract
 * class.
 * A multi-city Hipmunk URL looks like this: <b>https://www.hipmunk.com/flights#f0=phl;t0=var;d0=2015-07-11;f1=mad;t1=phl;d1=2015-08-08</b>
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class HipmunkTemplate extends SiteTemplate {
	//! This is the base URL that will be used when generating a URL.
	private final String m_baseUrl = "https://www.hipmunk.com/flights#";

	/**
	 * Constructor. The passed trip and permutationAlgorithm variables should not be null.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object specifying the permutation algorithm to use.
	 * @throws NullPointerException If the trip or permutationAlgorithm objects are null.
	 **/
	public HipmunkTemplate( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
		super( trip, permutationAlgorithm );
	}

	public String createMultiCityUrl( PermutationResult depart, PermutationResult returning ) { // {{{
		return String.format(
				// Base URL
				"%s" +
				// Depart From
				"f0=%s;" +
				// Depart To
				"t0=%s;" +
				// Depart Date
				"d0=%d-%02d-%02d;" +
				// Return From
				"f1=%s;" + 
				// Return To
				"t1=%s;" +
				// Return Date
				"d1=%d-%02d-%02d",
				m_baseUrl,
				depart.getFrom().toLowerCase(),
				depart.getTo().toLowerCase(),
				depart.getDate().getYear(),
				depart.getDate().getMonth(),
				depart.getDate().getDay(),
				returning.getFrom().toLowerCase(),
				returning.getTo().toLowerCase(),
				returning.getDate().getYear(),
				returning.getDate().getMonth(),
				returning.getDate().getDay()
		);
	} // }}}

	public String createOneWayUrl( PermutationResult result ) { // {{{
		// TODO: implement this.
		return new String();
	} // }}}
}
