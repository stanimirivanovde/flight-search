package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Kayak implements Site {
	private Trip m_trip;
	private String m_baseUrl = "http://www.kayak.com";
	private List<String> m_generatedUrls = new ArrayList<String>();
	private PermutationAlgorithm m_permutationAlgorithm;

	/**
	 * Default Constructor.
	 **/
	public Kayak() {}

	/**
	 * Preferred Constructor.
	 * This will instantiate an instance of the class and set the necessary variables of the class.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object containing the permutation algorithm to be used.
	 **/
	public Kayak( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
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
		// A Kayak URL looks like this: http://www.kayak.com/flights/PHL-SOF/2015-07-11/MAD-PHL/2015-08-08;
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

		System.out.println( "Number of total Multi City URLs: " + departList.size() * returningList.size() );
		for( PermutationResult depart : departList ) {
			for( PermutationResult returning : returningList ) {
				String currentUrl = String.format( 
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
						"%d-%02d-%02d",
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
	}
}
