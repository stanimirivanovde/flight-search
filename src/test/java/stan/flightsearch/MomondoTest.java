package stan.flightsearch;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

//import stan.flightsearch.FlightDate;

public class MomondoTest {
	private Trip trip;
	private TripGenerator tripGenerator = new TripGenerator();

	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void multiCityTripConstructor() throws Exception {
			// Generate the trip
			trip = tripGenerator.generateMultiCityTrip();

			Site momondo = new Momondo( trip );
			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.momondo.com/multicity/?Search=true&TripType=multi&SegNo=2&SO0=PHL&SD0=VAR&SDP0=03-12-1984&SO1=MAD&SD1=PHL&SDP1=24-12-1984&AD=1&TK=ECO&NA=false" );
		}

	@Test
		public void multiCityTripSetters() throws Exception {
			// Generate the trip
			trip = tripGenerator.generateMultiCityTrip();

			Site momondo = new Momondo();

			// Set the base URL
			String baseUrl = "http://www.momondo.com";
			momondo.setBaseUrl( baseUrl );
			Assert.assertEquals( momondo.getBaseUrl(), baseUrl );

			// Set the trip
			momondo.setTrip( trip );

			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.momondo.com/multicity/?Search=true&TripType=multi&SegNo=2&SO0=PHL&SD0=VAR&SDP0=03-12-1984&SO1=MAD&SD1=PHL&SDP1=24-12-1984&AD=1&TK=ECO&NA=false" );
		}

	/*
	 // TODO: implement this once the functionality is supported.
	@Test
		public void oneWayDepart() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = true;
			trip = tripGenerator.generateOneWayTrip( isDeparting );

			Site momondo = new Momondo( trip );
			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@Test
		public void oneWayReturn() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = false;
			trip = tripGenerator.generateOneWayTrip( isDeparting );

			Site momondo = new Momondo( trip );
			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}
		*/

	@After
		public void tearDown() throws Exception {
		}
}

