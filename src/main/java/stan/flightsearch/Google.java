package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Google implements Site {
	private Trip trip;
	private String baseUrl = "https://www.google.com/flights/#search;iti=";
	private List<String> generatedUrls = new ArrayList<String>();

	// Constructor
	public Google( Trip trip ) {
		this.trip = trip;
		generateURLs();
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

	public void generateURLs() {
		// Google URL looks like https://www.google.com/flights/#search;iti=PHL_VAR_2015-03-13*MAD_PHL_2015-03-20;tt=m
		if( trip.getDepart() != null && trip.getReturning() != null ) {
			generateURLsMultiCity();
		} else {
			generateURLsOneWay();
		}
	}

	public void executeSearch() {
		URLOpener urlOpener = new URLOpener( generatedUrls );
		urlOpener.start();
	}

	private void generateURLsMultiCity() {
		FlightPermutations departPermutations = new FlightPermutations( trip.getDepart() );
		FlightPermutations returningPermutations = new FlightPermutations( trip.getReturning() );

		List<PermutationResult> departList = departPermutations.generate();
		List<PermutationResult> returningList = returningPermutations.generate();

		System.out.println( "Number of total URLs: " + departList.size() * returningList.size() );
		for( PermutationResult depart : departList ) {
			for( PermutationResult returning : returningList ) {
				String currentUrl = String.format( "%s%s_%s_%s*%s_%s_%s;tt=m",
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
