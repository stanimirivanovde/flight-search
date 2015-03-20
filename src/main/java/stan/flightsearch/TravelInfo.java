package stan.flightsearch;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * Represents a travel direction configuration.
 * This class represents a travel direction configuration. This includes a list of origins, destinations and dates.
 * The lists will be used to calculate all the permutations associated with the travel direction configuration.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class TravelInfo {
	private List<String> m_origins = new ArrayList<String>();
	private List<String> m_destinations = new ArrayList<String>();
	private List<FlightDate> m_dates = new ArrayList<FlightDate>();

	/**
	 * Default Constructor.
	 **/
	public TravelInfo() {}

	/**
	 * Preferred Constructor.
	 * The constructor should be set with the lists of origins, destinations and dates. Those
	 * lists should not be null.
	 * @param origins A List&lt;String&gt; of the origins. It should not be null.
	 * @param destinations A List&lt;String&gt; of the destinations. It should not be null.
	 * @param dates A List&lt;String&gt; of the dates. It should not be null.
	 * @throws NullPointerException If any of the lists are null.
	 **/
	public TravelInfo( List<String> origins, List<String> destinations, List<FlightDate> dates ) {
		this.setOrigins( origins );
		this.setDestinations( destinations );
		this.setDates( dates );
	}

	// Setters {{{
	/**
	 * The setter for the origins list.
	 * @param list A List&lt;String&gt; list with the origins. This should not be null.
	 * @throws NullPointerException If the list is null.
	 **/
	public void setOrigins( List<String> list ) {
		if( list == null ) {
			throw new NullPointerException( "The origins list is null." );
		}
		m_origins = list;
	}

	/**
	 * The setter for the destinations list.
	 * @param list A List&lt;String&gt; list with the destinations. This should not be null.
	 * @throws NullPointerException If the list is null.
	 **/
	public void setDestinations( List<String> list ) {
		if( list == null ) {
			throw new NullPointerException( "The destinations list is null." );
		}
		m_destinations = list;
	}

	/**
	 * The setter for the destinations list.
	 * @param list A List&lt;FlightDate&gt; list with the dates. This should not be null.
	 * @throws NullPointerException If the list is null.
	 **/
	public void setDates( List<FlightDate> list ) {
		if( list == null ) {
			throw new NullPointerException( "The dates list is null." );
		}
		m_dates = list;
	}
	// }}}

	// Getters {{{
	/**
	 * Getter for the origins list.
	 * @return A List&lt;String&gt; object filled with the origins.
	 **/
	public List<String> getOrigins() { return m_origins; }

	/**
	 * Getter for the destinations list.
	 * @return A List&lt;String&gt; object filled with the destinations.
	 **/
	public List<String> getDestinations() { return m_destinations; }

	/**
	 * Getter for the flight dates list.
	 * @return A List&lt;String&gt; object filled with the dates.
	 **/
	public List<FlightDate> getDates() { return m_dates; }
	// }}}

	// Adders {{{
	/**
	 * Adds a string to the origins list.
	 * @param origin A String object that will be added to the origins list. Should not be null.
	 * @throws NullPointerException If the String object is null. For a list of exception that are thrown check the standard List&lt;&gt; implementation.
	 **/
	public void addOrigin( String origin ) {
		if( origin == null ) {
			throw new NullPointerException( "The origin string is null." );
		}
		m_origins.add( origin );
	}

	/**
	 * Adds a string to the destinations list.
	 * @param destination A String object that will be added to the destinations list. Should not be null.
	 * @throws NullPointerException If the String object is null. For a list of exception that are thrown check the standard List&lt;&gt; implementation.
	 **/
	public void addDestination( String destination ) {
		if( destination == null ) {
			throw new NullPointerException( "The destination string is null." );
		}
		m_destinations.add( destination );
	}

	/**
	 * Adds a string to the dates list.
	 * @param date A FlightDate object that will be added to the dates list. Should not be null.
	 * @throws NullPointerException If the FlightDate object is null. For a list of exception that are thrown check the standard List&lt;&gt; implementation.
	 **/
	public void addDate( FlightDate date ) {
		if( date == null ) {
			throw new NullPointerException( "The flight date is null." );
		}
		m_dates.add( date );
	}
	// }}}

	/**
	 * Return a string representing the travel information.
	 * This is mostly useful for debugging purposes.
	 * @return A String object representing the travel information.
	 **/
	@Override public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		stringBuilder.append( "TravelInfo:" + newLine );
		// Indent the content.
		stringBuilder.append( " " + m_origins + newLine );
		stringBuilder.append( " " + m_destinations + newLine );
		stringBuilder.append( " " + m_dates + newLine + newLine );

		return stringBuilder.toString();
	}

}
