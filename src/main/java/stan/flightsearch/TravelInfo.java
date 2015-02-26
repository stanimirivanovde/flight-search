package stan.flightsearch;

import java.util.List;

public class TravelInfo {
	private List<String> origins;
	private List<String> destinations;
	private List<String> dates;

	public TravelInfo() {}
	public TravelInfo( List<String> from, List<String> to, List<String> date ) {
		this.origins = from;
		this.destinations = to;
		this.dates = date;
	}
	public void print() {
		System.out.println( "The origins:" );
		System.out.println( origins );
		System.out.println( "The destinations:" );
		System.out.println( destinations );
		System.out.println( "The dates:" );
		System.out.println( dates );
	}

	public void setOrigins( List<String> list ) { this.origins = list; }
	public void setDestinations( List<String> list ) { this.destinations = list; }
	public void setDates( List<String> list ) { this.dates = list; }

	public List<String> getOrigins() { return origins; }
	public List<String> getDestinations() { return destinations; }
	public List<String> getDates() { return dates; }

	public void addOrigin( String origin ) {
		origins.add( origin );
	}
	public void addDestination( String destination ) {
		destinations.add( destination );
	}
	public void addDate( String date ) {
		dates.add( date );
	}
}
