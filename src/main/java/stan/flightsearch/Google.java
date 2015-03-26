package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Google implements Site {
	private Trip m_trip;
	private String m_baseUrl = "https://www.google.com/flights/#search;iti=";
	private List<String> m_generatedUrls = new ArrayList<String>();
	private PermutationAlgorithm m_permutationAlgorithm;

	/**
	 * Default Constructor.
	 **/
	public Google() {}

	/**
	 * Preferred Constructor.
	 * This will instantiate an instance of the class and set the necessary variables of the class.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object containing the permutation algorithm to be used.
	 **/
	public Google( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
		m_trip = trip;
		m_permutationAlgorithm = permutationAlgorithm;
	}

	public void setTrip( Trip trip ) {
		m_trip = trip;
	}
	public void setBaseUrl( String url ) {
		m_baseUrl = url;
	}

	public String getBaseUrl() { return m_baseUrl; }
	public List<String> getGeneratedUrls() { return m_generatedUrls; }

	public void generateUrls() {
		// Google URL looks like https://www.google.com/flights/#search;iti=PHL_VAR_2015-03-13*MAD_PHL_2015-03-20;tt=m
		if( m_trip.getDepart() != null && m_trip.getReturning() != null ) {
			generateURLsMultiCity();
		} else {
			generateURLsOneWay();
		}
	}

	private void generateURLsMultiCity() {
		m_permutationAlgorithm.setTravelInfo( m_trip.getDepart() );
		List<PermutationResult> departList = m_permutationAlgorithm.generate();

		m_permutationAlgorithm.setTravelInfo( m_trip.getReturning() );
		List<PermutationResult> returningList = m_permutationAlgorithm.generate();

		System.out.println( "Number of total URLs: " + departList.size() * returningList.size() );
		for( PermutationResult depart : departList ) {
			for( PermutationResult returning : returningList ) {
				String currentUrl = String.format( 
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
						";tt=m",
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
						returning.getDate().getDay()
				);
				System.out.println( "Generated the URL: " + currentUrl );
				m_generatedUrls.add( currentUrl );
			}
		}
	}

	private void generateURLsOneWay() {
		/*
		TravelInfo travelInfo;
		if( trip.getDepart() != null ) {
			travelInfo = trip.getDepart();
		} else {
			travelInfo = trip.getReturning();
		}

		FlightPermutations permutations = new FlightPermutations( travelInfo );

		List<PermutationResult> resultList = permutations.generate();

		System.out.println( "Number of total URLs: " + resultList.size() );
		for( PermutationResult result : resultList ) {
			String currentUrl = String.format( "%s/flights/%s-%s/%s",
					baseUrl,
					result.getFrom(),
					result.getTo(),
					result.getDate()
			);
			System.out.println( "Generated the URL: " + currentUrl );
			generatedUrls.add( currentUrl );
		}
		*/
	}
}
