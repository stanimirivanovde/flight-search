package stan.flightsearch;

import java.lang.StringBuilder;

public class FlightDate {
	private int day;
	private int month;
	private int year;

	public FlightDate() {}
	public FlightDate( int day, int month, int year ) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public void setDay( String s ) { day = Integer.parseInt( s ); }
	public void setMonth( String s ) { month = Integer.parseInt( s ); }
	public void setYear( String s ) { year = Integer.parseInt( s ); }

	public int getDay() { return day; }
	public int getMonth() { return month; }
	public int getYear() { return year; }

	@Override public String toString() {
		return String.format( "FlightDate: %d-%02d-%02d", this.year, this.month, this.day );
	}
}
