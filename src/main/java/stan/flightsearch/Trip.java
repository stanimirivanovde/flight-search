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
	private int m_numberOfPassangers = 1;

	/**
	 * Default Constructor. This is used by the JSON reading library. Stupid but necessary at the moment unless I refactor it.
	 **/
	public Trip() {
	}

	/**
	 * Preferred Constructor.
	 * Takes a depart and return TravelInfo objects and sets them to its private members.
	 * @param depart A {@link TravelInfo TravelInfo} object containing the depart information.
	 * @param returning A {@link TravelInfo TravelInfo} object containing the returning information.
	 * @throws IllegalArgumentException If both the depart or returning TravelInfo objects are null.
	 **/
	public Trip( TravelInfo depart, TravelInfo returning, int numberOfPassangers ) {
		if( depart == null ) {
			throw new NullPointerException( "The depart travel info object is null." );
		}
		if( returning == null ) {
			throw new NullPointerException( "The returning travel info object is null." );
		}
		if( numberOfPassangers < 1 ) {
			throw new IllegalArgumentException( "The number of passangers is invalid." );
		}
		m_depart = depart;
		m_returning = returning;
		m_numberOfPassangers = numberOfPassangers;
	}

	// Setters {{{
	/**
	 * The setter for the depart travel information.
	 * @param travelInfo A {@link TravelInfo TravelInfo} object representing departing information. This should be non-null.
	 * @throws NullPointerException If the passed object is null.
	 **/
	public void setDepart( TravelInfo travelInfo ) {
		if( travelInfo == null ) {
			throw new NullPointerException( "The depart TravelInfo object is null." );
		}
		m_depart = travelInfo;
	}

	/**
	 * The setter for the returning travel information.
	 * @param travelInfo A {@link TravelInfo TravelInfo} object representing returning information. This should be non-null.
	 * @throws NullPointerException If the passed object is null.
	 **/
	public void setReturning( TravelInfo travelInfo ) {
		if( travelInfo == null ) {
			throw new NullPointerException( "The returning TravelInfo object is null." );
		}
		m_returning = travelInfo;
	}

	public void setNumberOfPassangers( int passangers ) {
		if( passangers < 1 ) {
			throw new IllegalArgumentException( "Bad number of passangers: " + passangers );
		}
		m_numberOfPassangers = passangers;
	}
	// }}}
	
	// Getters {{{
	/**
	 * Getter for the departing travel information.
	 * @return A {@link TravelInfo TravelInfo} object representing the departing information.
	 **/
	public TravelInfo getDepart() { return m_depart; }

	/**
	 * Getter for the returning travel information.
	 * @return A {@link TravelInfo TravelInfo} object representing the returning information.
	 **/
	public TravelInfo getReturning() { return m_returning; }

	/**
	 * Getter for the number of passangers.
	 * @return An integer representing the number of passangers.
	 **/
	public int getNumberOfPassangers() {
		return m_numberOfPassangers;
	}
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
