package stan.flightsearch;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class KayakTest {
	private Trip trip;
	private TripGenerator tripGenerator = new TripGenerator();

	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void kayakMultiCityTripConstructor() throws Exception {
			// Generate the trip
			trip = tripGenerator.generateMultiCityTrip();

			Site kayak = new Kayak( trip );
			kayak.generateUrls();
			List<String> urls = kayak.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03/MAD-PHL/1984-12-24" );
		}

	@Test
		public void kayakMultiCityTripSetters() throws Exception {
			// Generate the trip
			trip = tripGenerator.generateMultiCityTrip();

			Site kayak = new Kayak();

			// Set the base URL
			String baseUrl = "http://www.kayak.com";
			kayak.setBaseUrl( baseUrl );
			Assert.assertEquals( kayak.getBaseUrl(), baseUrl );

			// Set the trip
			kayak.setTrip( trip );

			kayak.generateUrls();
			List<String> urls = kayak.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03/MAD-PHL/1984-12-24" );
		}

	@Test
		public void kayakOneWayDepart() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = true;
			trip = tripGenerator.generateOneWayTrip( isDeparting );

			Site kayak = new Kayak( trip );
			kayak.generateUrls();
			List<String> urls = kayak.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@Test
		public void kayakOneWayReturn() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = false;
			trip = tripGenerator.generateOneWayTrip( isDeparting );

			Site kayak = new Kayak( trip );
			kayak.generateUrls();
			List<String> urls = kayak.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@After
		public void tearDown() throws Exception {
		}
}

