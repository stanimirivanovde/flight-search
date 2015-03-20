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
	 * @see #setDay( int )
	 * @see #setMonth( int )
	 * @see #setYear( int )
	 *
	 **/
	public FlightDate( int day, int month, int year ) {
		this.setDay( day );
		this.setMonth( month );
		this.setYear( year );
	}

	// Setters {{{
	/**
	 * The setter for the flight's day of the month.
	 * The day argument needs to be between 1 and 31. If it is not an exception will be thrown.
	 * @param day An integer representing the day of the month. This needs to be between 1 and 31.
	 * @throws IllegalArgumentException If the day is not between 1 and 31.
	 **/
	public void setDay( int day ) {
		if( day < 1 || day > 31 ) {
			throw new IllegalArgumentException( "Invalid day specified: " + day + ". Must be between 1 and 31." );
		}
		m_day = day;
	}

	/**
	 * The setter for the flight's month of the year.
	 * The month argument needs to be between 1 and 12. If it is not an exception will be thrown.
	 * @param month An integer representing the month of the year. This needs to be between 1 and 12.
	 * @throws IllegalArgumentException If the month is not between 1 and 12.
	 **/
	public void setMonth( int month ) {
		if( month < 1 || month > 12 ) {
			throw new IllegalArgumentException( "Invalid month specified: " + month + ". Must be between 1 and 12." );
		}
		m_month = month;
	}

	/**
	 * The setter for the flight's year.
	 * The year argument needs to be between 1970 and 2100. If it is not an exception will be thrown.
	 * This should give the user enought values to play with and make sure a valid year is passed.
	 * @param year An integer representing the year of the flight. This needs to be between 1970 and 2100.
	 * @throws IllegalArgumentException If the year is not between 1970 and 2100.
	 **/
	public void setYear( int year ) {
		if( year < 1970 || year > 2100 ) {
			throw new IllegalArgumentException( "Invalid year specified: " + year + ". Must be between 1970 and 2100." );
		}
		m_year = year;
	}
	// }}}

	// Getters {{{
	/**
	 * Getter for the flight's day of the month.
	 * @return An Integer representing the flight's day of the month.
	 **/
	public int getDay() { return m_day; }

	/**
	 * Getter for the flight's month of the year.
	 * @return An Integer representing the flight's month of the year.
	 **/
	public int getMonth() { return m_month; }

	/**
	 * Getter for the flight's year.
	 * @return An Integer representing the flight's year.
	 **/
	public int getYear() { return m_year; }
	// }}}

	/**
	 * Return a string representing the flight date.
	 * This is mostly useful for debugging purposes. It displays the flight's date as year-month-day.
	 * The numbers are zero paded like the following: 2015-03-21.
	 * @return A String object representing the travel date.
	 **/
	@Override public String toString() {
		return String.format( "FlightDate: %d-%02d-%02d", m_year, m_month, m_day );
	}
}
