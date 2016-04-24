package stan.flightsearch;

import org.junit.*;
import java.util.Arrays;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TravelInfoTest {

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
		}

	@Test
		public void TravelInfoPreferredConstructor() throws Exception {
			String origin = new String( "PHL" );
			String destination = new String( "VAR" );
			FlightDate date = new FlightDate( 1, 1, 2015 );

			TravelInfo info = new TravelInfo(
					Arrays.asList( origin ),
					Arrays.asList( destination ),
					Arrays.asList( date )
				);
			Assert.assertEquals( info.getOrigins().get( 0 ), origin );
			Assert.assertEquals( info.getDestinations().get( 0 ), destination );
			Assert.assertEquals( info.getDates().get( 0 ), date );
		}

	@Test( expected=NullPointerException.class )
		public void TravelInfoNullOriginsSetter() throws Exception {
			TravelInfo info = new TravelInfo();
			info.setOrigins( null );
		}
	
	@Test
		public void TravelInfoValidOriginsSetter() throws Exception {
			TravelInfo info = new TravelInfo();
			String origin = new String( "PHL" );
			info.setOrigins( Arrays.asList( origin ) );
			Assert.assertEquals( info.getOrigins().get( 0 ), origin );
		}

	@Test( expected=NullPointerException.class )
		public void TravelInfoNullDestinationsSetter() throws Exception {
			TravelInfo info = new TravelInfo();
			info.setDestinations( null );
		}
	
	@Test
		public void TravelInfoValidDestinationsSetter() throws Exception {
			TravelInfo info = new TravelInfo();
			String destination = new String( "VAR" );
			info.setDestinations( Arrays.asList( destination ) );
			Assert.assertEquals( info.getDestinations().get( 0 ), destination );
		}

	@Test( expected=NullPointerException.class )
		public void TravelInfoNullDatesSetter() throws Exception {
			TravelInfo info = new TravelInfo();
			info.setDates( null );
		}
	
	@Test
		public void TravelInfoValidDatesSetter() throws Exception {
			TravelInfo info = new TravelInfo();
			FlightDate date = new FlightDate( 1, 1, 2015 );
			info.setDates( Arrays.asList( date ) );
			Assert.assertEquals( info.getDates().get( 0 ), date );
		}

	@Test( expected=NullPointerException.class )
		public void TravelInfoAddNullOrigin() throws Exception {
			TravelInfo info = new TravelInfo();
			info.addOrigin( null );
		}
	
	@Test
		public void TravelInfoAddValidOrigin() throws Exception {
			TravelInfo info = new TravelInfo();
			String origin = new String( "PHL" );
			info.addOrigin( origin );
			Assert.assertEquals( info.getOrigins().get( 0 ), origin );
		}

	@Test( expected=NullPointerException.class )
		public void TravelInfoAddNullDestination() throws Exception {
			TravelInfo info = new TravelInfo();
			info.addDestination( null );
		}
	
	@Test
		public void TravelInfoAddValidDestination() throws Exception {
			TravelInfo info = new TravelInfo();
			String destination = new String( "PHL" );
			info.addDestination( destination );
			Assert.assertEquals( info.getDestinations().get( 0 ), destination );
		}

	@Test( expected=NullPointerException.class )
		public void TravelInfoAddNullDate() throws Exception {
			TravelInfo info = new TravelInfo();
			info.addDate( null );
		}
	
	@Test
		public void TravelInfoAddValidDate() throws Exception {
			TravelInfo info = new TravelInfo();
			FlightDate date = new FlightDate( 1, 1, 2015 );
			info.addDate( date );
			Assert.assertEquals( info.getDates().get( 0 ), date );
		}

	@Test
		public void TravelInfoToString() throws Exception {
			String origin = new String( "PHL" );
			String destination = new String( "VAR" );
			FlightDate date = new FlightDate( 1, 1, 2015 );

			TravelInfo info = new TravelInfo(
					Arrays.asList( origin ),
					Arrays.asList( destination ),
					Arrays.asList( date )
				);
			URL testDataUrl = this.getClass().getResource("/TravelInfoTest-TravelInfoToString.txt");
			String outputString = new String( Files.readAllBytes( Paths.get( testDataUrl.getFile() ) ), "UTF-8" );
			Assert.assertEquals( info.toString(), outputString );
		}

	@After
		public void tearDown() throws Exception {
		}
}
