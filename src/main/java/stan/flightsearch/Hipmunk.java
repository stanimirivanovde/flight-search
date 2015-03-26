package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Hipmunk implements Site {
	private Trip m_trip;
	private String m_baseUrl = "https://www.hipmunk.com/flights#";
	private List<String> m_generatedUrls = new ArrayList<String>();
	private PermutationAlgorithm m_permutationAlgorithm;

	/**
	 * Default Constructor.
	 **/
	public Hipmunk() {}

	/**
	 * Preferred Constructor.
	 * This will instantiate an instance of the class and set the necessary variables of the class.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object containing the permutation algorithm to be used.
	 **/
	public Hipmunk( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
		m_trip = trip;
		m_permutationAlgorithm = permutationAlgorithm;
	}

	public void setTrip( Trip trip ) {
		m_trip = trip;
	}
	public void setBaseUrl( String url ) {
		m_baseUrl = url;
	}
	public void setPermutationAlgorithm( PermutationAlgorithm permutationAlgorithm ) {
		m_permutationAlgorithm = permutationAlgorithm;
	}

	public String getBaseUrl() { return m_baseUrl; }
	public List<String> getGeneratedUrls() { return m_generatedUrls; }

	public void generateUrls() {
		// A Hipmunk URL looks like this: https://www.hipmunk.com/flights#f0=phl;t0=var;d0=2015-07-11;f1=mad;t1=phl;d1=2015-08-08
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
				System.out.println( "Generated the URL: " + currentUrl );
				m_generatedUrls.add( currentUrl );
			}
		}
	}

	private void generateURLsOneWay() {
		/*
		TravelInfo travelInfo;
		if( m_trip.getDepart() != null ) {
			travelInfo = m_trip.getDepart();
		} else {
			travelInfo = m_trip.getReturning();
		}

		FlightPermutations permutations = new FlightPermutations( travelInfo );

		List<PermutationResult> resultList = permutations.generate();

		System.out.println( "Number of total URLs: " + resultList.size() );
		for( PermutationResult result : resultList ) {
			String currentUrl = String.format( 
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
			System.out.println( "Generated the URL: " + currentUrl );
			m_generatedUrls.add( currentUrl );
		}
		*/
	}
}
