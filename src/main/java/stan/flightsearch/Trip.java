package stan.flightsearch;

import java.lang.StringBuilder;

/**
 * This class represents a flight Trip.
 * It conviniently stores a flight trip information and is populated by the {@link FlightsReader#parseFile FlightsReader.parseFile()} interface implementation
 * when a flights file is read from disk. At this point in time only JSON files are handled and the class structure represents the structure of the JSON files.
 * Currently the Trip object is not generic enough and can only handle 3 types of scenarious:
 * <ul>
 * 	<li> One-way flights represented by having either the Trip.depart or Trip.returning private objects set.
 * 	These objects are of type TravelInfo. Which object is set gets checked in the {@link Site#generateUrls() Site.generateUrls()} 
 * 	interface implementation where the URL generation occurs.
 * 	<li> Multi-city flights with 2 segments only. This is currently hardcoded into the Trip object and needs to be generalized. Here
 * 	is an example of how it works right now:
 * 		<ul>
 * 			<li>Depart Segment:
 * 				<ul>
 * 					<li>From: PHL</li>
 * 					<li>To: VAR</li>
 * 					<li>Date: 08/30/2015</li>
 * 				</ul>
 * 			</li>
 * 			<li>Returning Segment:
 * 				<ul>
 * 					<li>From: MAD</li>
 * 					<li>To: PHL</li>
 * 					<li>Date: 09/10/2015</li>
 * 				</ul>
 * 			</li>
 * 		</ul>
 * 	<p>Unfortunately the class doesn't support additional segments at this point.<p>
 * 	</li>
 * 	<li>Round-trip flights. These are not implemented yet!</li>
 * </ul>
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class Trip {
	private TravelInfo m_depart;
	private TravelInfo m_returning;

	/**
	 * Default Constructor.
	 **/
	public Trip() {}

	/**
	 * Preferred Constructor.
	 * Takes a depart and return TravelInfo objects and sets them to its private members.
	 * @param depart A TravelInfo object containing the depart information.
	 * @param returning A TravelInfo object containing the returning information.
	 * @throws IllegalArgumentException If both the depart or returning TravelInfo objects are null.
	 **/
	public Trip( TravelInfo depart, TravelInfo returning ) {
		if( depart == null && returning == null ) {
			throw new IllegalArgumentException( "Can't have both the depart and returning TravelInfo objects be null." );
		}
		if( depart != null ) {
			this.setDepart( depart );
		}
		if( returning != null ) {
			this.setReturning( returning );
		}
	}

	// Setters {{{
	public void setDepart( TravelInfo i ) {
		if( i == null ) {
			throw new NullPointerException( "The depart TravelInfo object is null." );
		}
		m_depart = i;
	}

	public void setReturning( TravelInfo i ) {
		if( i == null ) {
			throw new NullPointerException( "The returning TravelInfo object is null." );
		}
		m_returning = i;
	}
	// }}}
	
	// Getters {{{
	public TravelInfo getDepart() { return m_depart; }
	public TravelInfo getReturning() { return m_returning; }
	// }}}

	/**
	 * Return a string representing the trip information.
	 * This is mostly useful for debugging purposes.
	 * @return A String object representing the trip.
	 **/
	@Override public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		stringBuilder.append( "Trip:" + newLine );
		if( m_depart != null ) {
			stringBuilder.append( " " + m_depart + newLine );
		}
		if( m_returning != null ) {
			stringBuilder.append( " " + m_returning + newLine );
		}
		return stringBuilder.toString();
	}
}
