package stan.flightsearch;

import java.lang.IllegalArgumentException;

/**
 * Factory for creating instances of the {@link Site Site} interface.
 * This class helps create all of the objects that implement the {@link Site Site} interface.
 * It depends on the {@link SupportedSitesEnum} and a {@link Trip Trip} object in order to craft 
 * the correct instance. If the specified enum is not found it will throw an IllegalArgumentException.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class SiteFactory {
	/**
	 * Creates a new {@link Site Site} instance.
	 * @param siteId A {@link SupportedSitesEnum SupportedSitesEnum} enum representing the object to create.
	 * @param trip A {@link Trip Trip} object that will be passed to the created site.
	 * @param permutationAlgorithm {@link PermutationAlgorithm PermutationAlgorithm} object that will provide the permutation algorithm to use for permuting the flight combinations.
	 * @return A {@link Site Site} object representing the site.
	 * @throws IllegalArgumentException If the specified enum doesn't exist.
	 * @throws NullPointerException If the specified {@link Trip Trip} or {@link PermutationAlgorithm PermutationAlgorithm} objects are null.
	 **/
	public Site createSite( SupportedSitesEnum siteId, Trip trip, PermutationAlgorithm permutationAlgorithm ) {
		if( trip == null ) {
			throw new NullPointerException( "The provided Trip object is null." );
		}

		if( permutationAlgorithm == null ) {
			throw new NullPointerException( "The provided PermutationAlgorithm object is null." );
		}

		switch( siteId ) {
			case KAYAK:
				return new Kayak( trip, permutationAlgorithm );
			case GOOGLE:
				return new Google( trip, permutationAlgorithm );
			case MOMONDO:
				return new Momondo( trip, permutationAlgorithm );
			case HIPMUNK:
				return new Hipmunk( trip, permutationAlgorithm );
			case FLIGHTHUB:
				return new FlightHub( trip, permutationAlgorithm );
			default:
				throw new IllegalArgumentException( "Bad site ID has been provided: " + siteId );
		}
	}
}
