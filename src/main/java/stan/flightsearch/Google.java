package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;

public class Google implements Site {
	private Trip trip;
	private String baseUrl = "https://www.google.com/flights/#search;iti=";
	private List<String> generatedUrls = new ArrayList<String>();

	// Constructors
	public Google() {}
	public Google( Trip trip ) {
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
		// Google URL looks like https://www.google.com/flights/#search;iti=PHL_VAR_2015-03-13*MAD_PHL_2015-03-20;tt=m
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
						baseUrl,
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
