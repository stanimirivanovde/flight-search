package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Hipmunk implements Site {
	private Trip trip;
	private String baseUrl = "https://www.hipmunk.com/flights#";
	private List<String> generatedUrls = new ArrayList<String>();

	// Constructors
	public Hipmunk() {}
	public Hipmunk( Trip trip ) {
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
		// A Hipmunk URL looks like this: https://www.hipmunk.com/flights#f0=phl;t0=var;d0=2015-07-11;f1=mad;t1=phl;d1=2015-08-08
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
						baseUrl,
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
