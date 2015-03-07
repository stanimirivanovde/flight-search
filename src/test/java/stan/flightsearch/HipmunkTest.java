package stan.flightsearch;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class HipmunkTest {
	private Trip trip;
	private TripGenerator tripGenerator = new TripGenerator();

	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void multiCityTripConstructor() throws Exception {
			// Generate the trip
			trip = tripGenerator.generateMultiCityTrip();

			Site hipmunk = new Hipmunk( trip );
			hipmunk.generateUrls();
			List<String> urls = hipmunk.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.hipmunk.com/flights#f0=phl;t0=var;d0=1984-12-03;f1=mad;t1=phl;d1=1984-12-24" );
		}

	@Test
		public void multiCityTripSetters() throws Exception {
			// Generate the trip
			trip = tripGenerator.generateMultiCityTrip();

			Site hipmunk = new Hipmunk();

			// Set the base URL
			String baseUrl = "https://www.hipmunk.com/flights#";
			hipmunk.setBaseUrl( baseUrl );
			Assert.assertEquals( hipmunk.getBaseUrl(), baseUrl );

			// Set the trip
			hipmunk.setTrip( trip );

			hipmunk.generateUrls();
			List<String> urls = hipmunk.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.hipmunk.com/flights#f0=phl;t0=var;d0=1984-12-03;f1=mad;t1=phl;d1=1984-12-24" );
		}

	@Ignore( "enable this when the functionality is implemented" )
	@Test
		public void oneWayDepart() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = true;
			trip = tripGenerator.generateOneWayTrip( isDeparting );

			Site hipmunk = new Hipmunk( trip );
			hipmunk.generateUrls();
			List<String> urls = hipmunk.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@Ignore( "enable this when the functionality is implemented" )
	@Test
		public void oneWayReturn() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = false;
			trip = tripGenerator.generateOneWayTrip( isDeparting );

			Site hipmunk = new Hipmunk( trip );
			hipmunk.generateUrls();
			List<String> urls = hipmunk.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@After
		public void tearDown() throws Exception {
		}
}

