package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class FlightHubTest {
//	private Trip trip;
//	private TripGenerator tripGenerator = new TripGenerator();
	private static final Trip mockedTrip = mock( Trip.class );
	private static final PermutationAlgorithm mockedPermutationAlgorithm = mock( PermutationAlgorithm.class );

	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void multiCityTripConstructor() throws Exception {
			// Generate the trip
	//		trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site flightHub = new FlightHub( mockedTrip, mockedPermutationAlgorithm );
			flightHub.generateUrls();
			List<String> urls = flightHub.getGeneratedUrls();
			System.out.println( "The urls: " + urls );
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.flighthub.com/flight/search?seg0_from=phl&seg0_date=1984-12-03&seg0_to=var&seg1_from=mad&seg1_date=1984-12-24&seg1_to=phl&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy" );
		}

	@Test
		public void multiCityTripSetters() throws Exception {
			// Generate the trip
		//	trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site flightHub = new FlightHub();

			// Set the base URL
			String baseUrl = "http://www.flighthub.com/flight/search?";
			flightHub.setBaseUrl( baseUrl );
			Assert.assertEquals( flightHub.getBaseUrl(), baseUrl );

			// Set the permutation algorithm
			flightHub.setPermutationAlgorithm( mockedPermutationAlgorithm );
			// Set the trip
			flightHub.setTrip( mockedTrip );

			flightHub.generateUrls();
			List<String> urls = flightHub.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.flighthub.com/flight/search?seg0_from=phl&seg0_date=1984-12-03&seg0_to=var&seg1_from=mad&seg1_date=1984-12-24&seg1_to=phl&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy" );
		}

	@Ignore( "enable this when the functionality is implemented" )
	@Test
		public void oneWayDepart() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = true;
		//	trip = tripGenerator.generateOneWayTrip( isDeparting );

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site flightHub = new FlightHub( mockedTrip, mockedPermutationAlgorithm );
			flightHub.generateUrls();
			List<String> urls = flightHub.getGeneratedUrls();
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
		//	trip = tripGenerator.generateOneWayTrip( isDeparting );

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site flightHub = new FlightHub( mockedTrip, mockedPermutationAlgorithm );
			flightHub.generateUrls();
			List<String> urls = flightHub.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@After
		public void tearDown() throws Exception {
		}
}

