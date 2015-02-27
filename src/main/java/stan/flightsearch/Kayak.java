package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Kayak implements Site {
	private Trip trip;
	private String baseUrl = "http://www.kayak.com";
	private List<String> generatedUrls = new ArrayList<String>();

	// Constructor
	public Kayak( Trip trip ) {
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
		// A Kayak URL looks like this: http://www.kayak.com/flights/PHL-SOF/2015-07-11/MAD-PHL/2015-08-08;
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
		for( PermutationResult depart : departList ) {
			for( PermutationResult returning : returningList ) {
				String currentUrl = String.format( "%s/flights/%s-%s/%s/%s-%s/%s",
						baseUrl,
						depart.getFrom(),
						depart.getTo(),
						depart.getDate(),
						returning.getFrom(),
						returning.getTo(),
						returning.getDate()
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
