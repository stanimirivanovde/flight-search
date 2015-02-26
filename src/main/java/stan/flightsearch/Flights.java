package stan.flightsearch;

public class Flights {
	public static void main( String[] args ) {
		try {
			FlightsReader flightsReader = new FlightsReader( args[ 0 ] );
			Trip trip = flightsReader.parseJson();
			Site kayak = new Kayak( trip );
			Site google = new Google( trip );
			kayak.executeSearch();
			google.executeSearch();
		} catch( Exception e ) { 
			System.out.println( "Error in opening the page: " + e );
			e.printStackTrace();
		}
	}  
}
