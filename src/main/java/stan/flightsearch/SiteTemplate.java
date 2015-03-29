package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

/**
 * An abstract class that all SiteTemplates should implement.
 * This class implements the Template Pattern and serves as the
 * performer of the URL generation algorithm. Each class that inherits this one would
 * have to implement the abstract functions (hooks) that are defined in the class:
 * {@link #createMultiCityUrl createMultiCityUrl()}
 * {@link #createOneWayUrl createOneWayUrl()}
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public abstract class SiteTemplate {
	//! This is the Trip object that contains all of the details for the flight
	private Trip m_trip;
	//! This will have the generated URLs.
	private List<String> m_generatedUrls = new ArrayList<String>();
	//! This is the permutation algorithm to use.
	private PermutationAlgorithm m_permutationAlgorithm;

	/**
	 * Preferred Constructor.
	 * This will instantiate an instance of the class and set the necessary variables of the class.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object containing the permutation algorithm to be used.
	 * @throws NullPointerException If either the provided trip or permutationAlgorithm are null.
	 **/
	public SiteTemplate( Trip trip, PermutationAlgorithm permutationAlgorithm ) { // {{{
		if( trip == null ) {
			throw new NullPointerException( "The provided Trip object is null." );
		}
		if( permutationAlgorithm == null ) {
			throw new NullPointerException( "The provided PermutationAlgorithm object is null." );
		}
		m_trip = trip;
		m_permutationAlgorithm = permutationAlgorithm;
	} // }}}

	/**
	 * Returns the generated list of URLs.
	 * @return A List&lt;String&gt; object representing the generated URLs
	 **/
	public final List<String> getGeneratedUrls() { return m_generatedUrls; }

	/**
	 * Generates the site URLs based on the provided trip and permutation algorithm.
	 * The list is stored internally. Use {@link #getGeneratedUrls getGeneratedUrls()} to
	 * retrieve the list.
	 **/
	public final void generateUrls() { // {{{
		// Generate either multi-city or one way URLs
		if( m_trip.getDepart() != null && m_trip.getReturning() != null ) {
			generateURLsMultiCity();
		} else {
			generateURLsOneWay();
		}
	} // }}}

	/**
	 * Generates all multi city URLs based on the internal trip and permutationAlgorithm objects.
	 * The results are stored locally in the variable m_generatedUrls. Use the function
	 * {@link #getGeneratedUrls getGeneratedUrls()} to retrieve it.
	 **/
	private final void generateURLsMultiCity() { // {{{
		m_permutationAlgorithm.setTravelInfo( m_trip.getDepart() );
		List<PermutationResult> departList = m_permutationAlgorithm.generate();

		m_permutationAlgorithm.setTravelInfo( m_trip.getReturning() );
		List<PermutationResult> returningList = m_permutationAlgorithm.generate();

		System.out.println( "Number of total Multi City URLs: " + departList.size() * returningList.size() );
		for( PermutationResult depart : departList ) {
			for( PermutationResult returning : returningList ) {
				String currentUrl = this.createMultiCityUrl( depart, returning );
				System.out.println( "Generated the URL: " + currentUrl );
				m_generatedUrls.add( currentUrl );
			}
		}
	} // }}}

	/**
	 * Generates all one way URLs based on the internal trip and permutationAlgorithm objects.
	 * The results are stored locally in the variable m_generatedUrls. Use the function
	 * {@link #getGeneratedUrls getGeneratedUrls()} to retrieve it.
	 **/
	private final void generateURLsOneWay() { // {{{
		TravelInfo travelInfo;
		if( m_trip.getDepart() != null ) {
			travelInfo = m_trip.getDepart();
		} else {
			travelInfo = m_trip.getReturning();
		}

		m_permutationAlgorithm.setTravelInfo( travelInfo );
		List<PermutationResult> resultList = m_permutationAlgorithm.generate();

		System.out.println( "Number of total One Way URLs: " + resultList.size() );
		for( PermutationResult result : resultList ) {
			String currentUrl = this.createOneWayUrl( result );
			System.out.println( "Generated the URL: " + currentUrl );
			m_generatedUrls.add( currentUrl );
		}
	} // }}}

	/**
	 * Should create a URL based on the supplied {@link PermutationResult PermutationResult} depart and
	 * returning objects. This is a hook that should be overridden by all subclasses.
	 * @param depart A {@link PermutationResult PermutationResult} object containing the departing information.
	 * @param returning A {@link PermutationResult PermutationResult} object containing the returning information.
	 * @return A String object of the created URL.
	 **/
	public abstract String createMultiCityUrl( PermutationResult depart, PermutationResult returning );

	/**
	 * Should create a URL based on the supplied {@link PermutationResult PermutationResult} object.
	 * This is a hook that should be overridden by all subclasses.
	 * @param result A {@link PermutationResult PermutationResult} object containing the one way information.
	 * @return A String object of the created URL.
	 **/
	public abstract String createOneWayUrl( PermutationResult result );
}
