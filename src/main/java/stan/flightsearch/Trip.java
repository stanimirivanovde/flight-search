package stan.flightsearch;

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

	public void print() {
		if( depart != null ) {
			System.out.println( "The depart set-up:" );
			depart.print();
		}
		if( returning != null ) {
			System.out.println( "The return set-up:" );
			returning.print();
		}
	}
}
