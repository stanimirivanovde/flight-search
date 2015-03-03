package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Momondo implements Site {
	private Trip trip;
	private String baseUrl = "http://www.momondo.com";
	private List<String> generatedUrls = new ArrayList<String>();

	// Constructor
	public Momondo( Trip trip ) {
		this.trip = trip;
	}

	public void setTrips( Trip trip ) {
		this.trip = trip;
	}
	public void setBaseUrl( String url ) {
		this.baseUrl = url;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public List<String> getGeneratedUrls() { return generatedUrls; }

	public void generateUrls() {
		// A Momondo URL looks like this http://www.momondo.com/multicity/?Search=true&TripType=multi&SegNo=2&SO0=PHL&SD0=VAR&SDP0=11-07-2015&SO1=MAD&SD1=PHL&SDP1=08-08-2015&AD=1&TK=ECO&NA=false
		if( trip.getDepart() != null && trip.getReturning() != null ) {
			generateURLsMultiCity();
		} else {
			generateURLsOneWay();
		}
	}

	private void generateURLsMultiCity() {
		FlightPermutations departPermutations = new FlightPermutations( trip.getDepart() );
		FlightPermutations returningPermutations = new FlightPermutations( trip.getReturning() );

		List<PermutationResult> departList = departPermutations.generate();
		List<PermutationResult> returningList = returningPermutations.generate();

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
				baseUrl
		);
		for( PermutationResult depart : departList ) {
			for( PermutationResult returning : returningList ) {
				String currentUrl = String.format( 
						// Building the URL string
						// Base URL
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
				generatedUrls.add( currentUrl );
			}
		}
	}

	private void generateURLsOneWay() {
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
	}
}
