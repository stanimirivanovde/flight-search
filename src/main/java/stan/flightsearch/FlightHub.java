package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class FlightHub implements Site {
	private Trip trip;
	private String baseUrl = "http://www.flighthub.com/flight/search?";
	private List<String> generatedUrls = new ArrayList<String>();

	// Constructors
	public FlightHub() {}
	public FlightHub( Trip trip ) {
		this.trip = trip;
	}

	public void setTrip( Trip trip ) {
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
		// A FlightHub URL looks like this:
		// http://www.flighthub.com/flight/search?seg0_from=PHL&seg0_date=2015-07-11&seg0_to=VAR&seg1_from=MAD&seg1_date=2015-08-08&seg1_to=PHL&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy
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
				String currentUrl = String.format( 
						// Base URL
						"%s" +
						// Depart From
						"seg0_from=%s" +
						// Depart Date
						"&seg0_date=%d-%02d-%02d" +
						// Depart To
						"&seg0_to=%s" +
						// Return From
						"&seg1_from=%s" + 
						// Return Date
						"&seg1_date=%d-%02d-%02d" +
						// Return To
						"&seg1_to=%s" +
						// Final parameters
						"&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy",
						baseUrl,
						depart.getFrom().toLowerCase(),
						depart.getDate().getYear(),
						depart.getDate().getMonth(),
						depart.getDate().getDay(),
						depart.getTo().toLowerCase(),
						returning.getFrom().toLowerCase(),
						returning.getDate().getYear(),
						returning.getDate().getMonth(),
						returning.getDate().getDay(),
						returning.getTo().toLowerCase()
				);
				System.out.println( "Generated the URL: " + currentUrl );
				generatedUrls.add( currentUrl );
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
			String currentUrl = String.format( 
					// Base URL
					"%s/flights/" +
					// From
					"%s-" +
					// To
					"%s/" +
					// Date
					"%d-%02d-%02d",
					baseUrl,
					result.getFrom(),
					result.getTo(),
					result.getDate().getYear(),
					result.getDate().getMonth(),
					result.getDate().getDay()
			);
			System.out.println( "Generated the URL: " + currentUrl );
			generatedUrls.add( currentUrl );
		}
		*/
	}
}
