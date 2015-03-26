package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Momondo implements Site {
	private Trip m_trip;
	private String m_baseUrl = "http://www.momondo.com";
	private List<String> m_generatedUrls = new ArrayList<String>();
	private PermutationAlgorithm m_permutationAlgorithm;

	/**
	 * Default Constructor.
	 **/
	public Momondo() {}

	/**
	 * Preferred Constructor.
	 * This will instantiate an instance of the class and set the necessary variables of the class.
	 * @param trip A {@link Trip Trip} object containing the trip details.
	 * @param permutationAlgorithm A {@link PermutationAlgorithm PermutationAlgorithm} object containing the permutation algorithm to be used.
	 **/
	public Momondo( Trip trip, PermutationAlgorithm permutationAlgorithm ) {
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
		// A Momondo URL looks like this http://www.momondo.com/multicity/?Search=true&TripType=multi&SegNo=2&SO0=PHL&SD0=VAR&SDP0=11-07-2015&SO1=MAD&SD1=PHL&SDP1=08-08-2015&AD=1&TK=ECO&NA=false
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

		// Lets craft the right URL:
		String craftedUrl = String.format(
				// Base URL
				"%s/" +
				// trip type
				"multicity/" +
				// trip type again
				"?Search=true&TripType=multi" +
				// Something I don't understand
				"&SegNo=2", 
				m_baseUrl
		);
		for( PermutationResult depart : departList ) {
			for( PermutationResult returning : returningList ) {
				String currentUrl = String.format( 
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
						// Final tail of the query
						"&AD=1&TK=ECO&NA=false",
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
						returning.getDate().getYear()
				);
				System.out.println( "Generated the URL: " + currentUrl );
				m_generatedUrls.add( currentUrl );
			}
		}
	}

	private void generateURLsOneWay() {
	 // TODO: implement this!
	/*
		TravelInfo travelInfo;
		if( m_trip.getDepart() != null ) {
			travelInfo = m_trip.getDepart();
		} else {
			travelInfo = m_trip.getReturning();
		}

		FlightPermutations permutations = new FlightPermutations( m_travelInfo );

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
			m_generatedUrls.add( currentUrl );
		}
	*/
	}
}
