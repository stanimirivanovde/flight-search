package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class GoogleTest {
//	private Trip trip;
//	private static final TripGenerator tripGenerator = new TripGenerator();
	private static final Trip mockedTrip = mock( Trip.class );
	private static final PermutationAlgorithm mockedPermutationAlgorithm = mock( PermutationAlgorithm.class );

	//TODO: mock a permutationalgorithm instance and pass it along

	@BeforeClass
		public void setUpTestCase() throws Exception {
		}

	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void multiCityTripConstructor() throws Exception {
			// Generate the trip
//			trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site google = new Google( mockedTrip, mockedPermutationAlgorithm );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.google.com/flights/#search;iti=PHL_VAR_1984-12-03*MAD_PHL_1984-12-24;tt=m" );
		}

	@Test
		public void multiCityTripSetters() throws Exception {
			// Generate the trip
//			trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site google = new Google();

			// Set the base URL
			String baseUrl = "https://www.google.com/flights/#search;iti=";
			google.setBaseUrl( baseUrl );
			Assert.assertEquals( google.getBaseUrl(), baseUrl );

			// Set the permutation algorithm
			google.setPermutationAlgorithm( mockedPermutationAlgorithm );
			// Set the trip
			google.setTrip( mockedTrip );

			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.google.com/flights/#search;iti=PHL_VAR_1984-12-03*MAD_PHL_1984-12-24;tt=m" );
		}

	@Ignore( "enable this when the functionality is implemented" )
	@Test
		public void oneWayDepart() throws Exception {
			// Generate the trip
			// This denotes weather the one way trip is configured as departing or returning.
			boolean isDeparting = true;
//			trip = tripGenerator.generateOneWayTrip( isDeparting );

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site google = new Google( mockedTrip, mockedPermutationAlgorithm );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
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
//			trip = tripGenerator.generateOneWayTrip( isDeparting );

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site google = new Google( mockedTrip, mockedPermutationAlgorithm );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@After
		public void tearDown() throws Exception {
		}

	@AfterClass
		public void tearDownTestCase() throws Exception {
		}
}

