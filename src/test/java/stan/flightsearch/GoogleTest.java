package stan.flightsearch;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

//import stan.flightsearch.FlightDate;

public class GoogleTest {
	private Trip trip;
	private TripGenerator tripGenerator = new TripGenerator();

	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void multiCityTripConstructor() throws Exception {
			// Generate the trip
			trip = tripGenerator.generateMultiCityTrip();

			Site google = new Google( trip );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.google.com/flights/#search;iti=PHL_VAR_1984-12-03*MAD_PHL_1984-12-24;tt=m" );
		}

	@Test
		public void multiCityTripSetters() throws Exception {
			// Generate the trip
			trip = tripGenerator.generateMultiCityTrip();

			Site google = new Google();

			// Set the base URL
			String baseUrl = "https://www.google.com/flights/#search;iti=";
			google.setBaseUrl( baseUrl );
			Assert.assertEquals( google.getBaseUrl(), baseUrl );

			// Set the trip
			google.setTrip( trip );

			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.google.com/flights/#search;iti=PHL_VAR_1984-12-03*MAD_PHL_1984-12-24;tt=m" );
		}

			// TODO: enable this when the functionality is implemented.
			/*
	@Test
		public void oneWayDepart() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = true;
			trip = tripGenerator.generateOneWayTrip( isDeparting );

			Site google = new Google( trip );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
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

			Site google = new Google( trip );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}
			*/

	@After
		public void tearDown() throws Exception {
		}
}

