package stan.flightsearch;

import org.junit.*;
import java.util.Arrays;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TripTest {
	private TravelInfo m_depart;
	private TravelInfo m_returning;

	private boolean validateTravelInfo( TravelInfo a, TravelInfo b ) { // {{{
		if( a.getOrigins().size() != b.getOrigins().size() 
				|| a.getDestinations().size() != b.getDestinations().size()
				|| a.getDates().size() != b.getDates().size() ) {
			return false;
		}

		int sizeOfOrigins = a.getOrigins().size();
		for( int i = 0; i < sizeOfOrigins; ++i ) {
			if( a.getOrigins().get( i ) != b.getOrigins().get( i ) ) {
				return false;
			}
		}

		int sizeOfDestinations = a.getDestinations().size();
		for( int i = 0; i < sizeOfDestinations; ++i ) {
			if( a.getDestinations().get( i ) != b.getDestinations().get( i ) ) {
				return false;
			}
		}

		int sizeOfDates = a.getDates().size();
		for( int i = 0; i < sizeOfDates; ++i ) {
			if( a.getDates().get( i ) != b.getDates().get( i ) ) {
				return false;
			}
		}
		return true;
	} // }}}

	@Before
		public void setUp() throws Exception {
			m_depart = new TravelInfo(
					Arrays.asList( "PHL" ),
					Arrays.asList( "VAR" ),
					Arrays.asList( new FlightDate( 1, 1, 2015 ) )
				);
			m_returning = new TravelInfo(
					Arrays.asList( "MAD" ),
					Arrays.asList( "PHL" ),
					Arrays.asList( new FlightDate( 10, 1, 2015 ) )
				);
		}

	@Test
		public void TripPreferredConstructorValidArguments() throws Exception {
			Trip trip = new Trip( m_depart, m_returning );
			Assert.assertEquals( trip.getDepart(), m_depart );
			Assert.assertEquals( trip.getReturning(), m_returning );
		}

	@Test( expected=IllegalArgumentException.class )
		public void TripPreferredConstructorNullArguments() throws Exception {
			Trip trip = new Trip( null, null );
		}

	@Test
		public void TripPreferredConstructorNullDepart() throws Exception {
			Trip trip = new Trip( null, m_returning );
			Assert.assertEquals( trip.getReturning(), m_returning );
		}

	@Test
		public void TripPreferredConstructorNullReturning() throws Exception {
			Trip trip = new Trip( m_depart, null );
			Assert.assertEquals( trip.getDepart(), m_depart );
		}

	@Test
		public void TripValidSetters() throws Exception {
			Trip trip = new Trip();
			trip.setDepart( m_depart );
			trip.setReturning( m_returning );
			Assert.assertEquals( trip.getDepart(), m_depart );
			Assert.assertEquals( trip.getReturning(), m_returning );
		}
	
	@Test
		public void TripSetDepartValid() {
			Trip trip = new Trip();
			trip.setDepart( m_depart );
			Assert.assertEquals( trip.getDepart(), m_depart );
		}

	@Test( expected=NullPointerException.class )
		public void TripSetDepartNull() {
			Trip trip = new Trip();
			trip.setDepart( null );
		}
	
	@Test
		public void TripSetReturningValid() {
			Trip trip = new Trip();
			trip.setReturning( m_returning );
			Assert.assertEquals( trip.getReturning(), m_returning );
		}

	@Test( expected=NullPointerException.class )
		public void TripSetReturningNull() {
			Trip trip = new Trip();
			trip.setReturning( null );
		}

	@Test
		public void TripToString() throws Exception {
			Trip trip = new Trip( m_depart, m_returning );
			URL testDataUrl = this.getClass().getResource("/TripTest-TripToString.txt");
			String outputString = new String( Files.readAllBytes( Paths.get( testDataUrl.getFile() ) ), "UTF-8" );
			Assert.assertEquals( trip.toString(), outputString );
		}

	@Test
		public void TripToStringNullDepart() throws Exception {
			Trip trip = new Trip( null, m_returning );
			URL testDataUrl = this.getClass().getResource("/TripTest-TripToStringNullDepart.txt");
			String outputString = new String( Files.readAllBytes( Paths.get( testDataUrl.getFile() ) ), "UTF-8" );
			Assert.assertEquals( trip.toString(), outputString );
		}

	@Test
		public void TripToStringNullReturning() throws Exception {
			Trip trip = new Trip( m_depart, null );
			URL testDataUrl = this.getClass().getResource("/TripTest-TripToStringNullReturning.txt");
			String outputString = new String( Files.readAllBytes( Paths.get( testDataUrl.getFile() ) ), "UTF-8" );
			Assert.assertEquals( trip.toString(), outputString );
		}

	@After
		public void tearDown() throws Exception {
		}
}
