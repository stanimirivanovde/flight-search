import java.util.List;
import java.util.ArrayList;

public class Kayak implements Site {
	private Trip trip;
	private String baseUrl = "http://www.kayak.com";
	private List<String> generatedUrls = new ArrayList<String>();

	// Constructor
	public Kayak( Trip trip ) {
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
		// A Kayak URL looks like this: http://www.kayak.com/flights/PHL-SOF/2015-07-11/MAD-PHL/2015-08-08;
		FlightPermutations departPermutations = new FlightPermutations( trip.getDepart() );
		FlightPermutations returningPermutations = new FlightPermutations( trip.getReturning() );
		List<String> departList = departPermutations.generate();
		List<String> returningList = returningPermutations.generate();
		System.out.println( "Number of total URLs: " + departList.size() * returningList.size() );
		for( String depart : departList ) {
			for( String returning : returningList ) {
				String currentUrl = String.format( "%s/flights/%s/%s",
						baseUrl,
						depart,
						returning
				);
				System.out.println( "Generated the URL: " + currentUrl );
				generatedUrls.add( currentUrl );
			}
		}
	}

	public void executeSearch() {
		URLOpener urlOpener = new URLOpener( generatedUrls );
		urlOpener.start();
	}
}
