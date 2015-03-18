package stan.flightsearch;

import java.lang.StringBuilder;
import java.lang.IllegalArgumentException;

/**
 * Represents a flight date.
 * This class represents a flight date. It conviniently stores a generic representation of a date
 * including the day, month and year.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class FlightDate {
	private int m_day = 0;
	private int m_month = 0;
	private int m_year = 0;

	/**
	 * The default constructor.
	 **/
	public FlightDate() {}

	/**
	 * The preferred constructor.
	 * @param day An integer representing the day. Should be between 1 and 31.
	 * @param month An integer representing the month. Should be between 1 and 12.
	 * @param year An integer representing the year. Should be between 1970 and 2100.
	 * @throws IllegalArgumentException If any of the parameters are not within their valid range.
	 * @see #setDay
	 * @see #setMonth
	 * @see #setYear
	 *
	 **/
	public FlightDate( int day, int month, int year ) {
		m_day = day;
		m_month = month;
		m_year = year;
	}

	// TODO: have setters for string and int individually

	// Setters {{{
	/**
	 * This is the setter for the "from" string of the flight.
	 * @param fromString A String object representing the "from" destination the flight. The string should not be null or empty.
	 * @throws IllegalArgumentException An exception if the string is empty or null.
	 **/
	public void setDay( String s ) { m_day = Integer.parseInt( s ); }
	public void setMonth( String s ) { m_month = Integer.parseInt( s ); }
	public void setYear( String s ) { m_year = Integer.parseInt( s ); }
	// }}}

	// Getters {{{
	public int getDay() { return m_day; }
	public int getMonth() { return m_month; }
	public int getYear() { return m_year; }
	// }}}

	@Override public String toString() {
		return String.format( "FlightDate: %d-%02d-%02d", m_year, m_month, m_day );
	}
}
