package stan.flightsearch;

import java.lang.IllegalArgumentException;

/**
 * Represents a permutation result.
 * In order to represent permutation results correctly for flights a 'structure' like class
 * needs to be created. This class will have a single combination of from, to and date of the flight.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class PermutationResult {
	private String m_from;
	private String m_to;
	private FlightDate m_date;

	/**
	 * Default Constructor.
	 **/
	public PermutationResult() {}

	/** 
	 * This is the preferred constructor.
	 * @param from A String object representing the "from" location of the flight.
	 *             The string should not be empty.
	 * @param to A String object representing the "to" location of the flight.
	 *             The string should not be empty.
	 * @param date A FlightDate object representing the date of the flight.
	 *             The string should not be empty.
	 * @throws IllegalArgumentException If any of the supplied parameters are null or empty.
	 * @see #setFrom
	 * @see #setTo
	 * @see #setDate
	 **/
	public PermutationResult( String from, String to, FlightDate date ) {
		this.setFrom( from );
		this.setTo( to );
		this.setDate( date );
	}

	// Setters {{{
	/**
	 * This is the setter for the "from" string of the flight.
	 * @param fromString A String object representing the "from" destination the flight. The string should not be null or empty.
	 * @throws IllegalArgumentException An exception if the string is empty or null.
	 **/
	public void setFrom( String fromString ) {
		if( fromString == null || fromString.isEmpty() ) {
			throw new IllegalArgumentException( "The 'from' string is invalid." );
		}
		m_from = fromString;
	}

	/**
	 * This is the setter for the "to" string of the flight.
	 * @param toString A String object representing the "to" destination of the flight. The string should not be null or empty.
	 * @throws IllegalArgumentException An exception if the string is empty or null.
	 **/
	public void setTo( String toString ) {
		if( toString == null || toString.isEmpty() ) {
			throw new IllegalArgumentException( "The 'to' string is invalid." );
		}
		m_to = toString;
	}

	/**
	 * This is the setter for the "date" string of the flight.
	 * @param date A FlightDate object representing the "date" of the flight. The FlightDate object should not be null.
	 * @throws NullPointerException If the date object is null.
	 * @throws IllegalArgumentException If the FlightDate object is null.
	 **/
	public void setDate( FlightDate date ) {
		if( date == null ) {
			throw new NullPointerException( "The 'date' object is null." );
		}
		if( date.getYear() == 0 || date.getMonth() == 0 || date.getDay() == 0 ) {
			throw new IllegalArgumentException( "The flight date is not set up correctly." );
		}
		m_date = date;
	}
	// }}}

	// Getters {{{
	/**
	 * Getter for the "from" flight destination string.
	 * @return A String object representing the "from" flight destination.
	 **/
	public String getFrom() { return m_from; }

	/**
	 * Getter for the "to" flight destination string.
	 * @return A String object representing the "to" flight destination.
	 **/
	public String getTo() { return m_to; }

	/**
	 * Getter for the flight date.
	 * @return A FlightDate object representing the flight date.
	 **/
	public FlightDate getDate() { return m_date; }
	// }}}
}

