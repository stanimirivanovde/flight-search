package stan.flightsearch;

import java.util.List;
import java.lang.StringBuilder;

public class TravelInfo {
	private List<String> origins;
	private List<String> destinations;
	private List<FlightDate> dates;

	public TravelInfo() {}
	public TravelInfo( List<String> from, List<String> to, List<FlightDate> dateList ) {
		this.origins = from;
		this.destinations = to;
		this.dates = dateList;
	}

	@Override public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		stringBuilder.append( "TravelInfo:" + newLine );
		// Indent the content.
		stringBuilder.append( " " + origins + newLine );
		stringBuilder.append( " " + destinations + newLine );
		stringBuilder.append( " " + dates + newLine + newLine );

		return stringBuilder.toString();
	}

	public void setOrigins( List<String> list ) { this.origins = list; }
	public void setDestinations( List<String> list ) { this.destinations = list; }
	public void setDates( List<FlightDate> list ) { this.dates = list; }

	public List<String> getOrigins() { return origins; }
	public List<String> getDestinations() { return destinations; }
	public List<FlightDate> getDates() { return dates; }

	public void addOrigin( String origin ) {
		origins.add( origin );
	}
	public void addDestination( String destination ) {
		destinations.add( destination );
	}
	public void addDate( FlightDate date ) {
		dates.add( date );
	}
}
