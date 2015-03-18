package stan.flightsearch;

import java.lang.StringBuilder;

public class Trip {
	private TravelInfo depart;
	private TravelInfo returning;

	public Trip() {}
	public Trip( TravelInfo depart, TravelInfo ret ) {
		this.depart = depart;
		this.returning = ret;
	}

	public void setDepart( TravelInfo i ) { this.depart = i; }
	public void setReturning( TravelInfo i ) { this.returning = i; }
	
	public TravelInfo getDepart() { return depart; }
	public TravelInfo getReturning() { return returning; }

	@Override public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		stringBuilder.append( "Trip:" + newLine );
		if( depart != null ) {
			stringBuilder.append( " " + depart + newLine );
		}
		if( returning != null ) {
			stringBuilder.append( " " + returning + newLine );
		}
		return stringBuilder.toString();
	}
}
