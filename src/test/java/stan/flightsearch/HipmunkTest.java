package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class HipmunkTest {
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
//			trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site hipmunk = new Hipmunk( mockedTrip, mockedPermutationAlgorithm );
			hipmunk.generateUrls();
			List<String> urls = hipmunk.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.hipmunk.com/flights#f0=phl;t0=var;d0=1984-12-03;f1=mad;t1=phl;d1=1984-12-24" );
		}

	@Test
		public void multiCityTripSetters() throws Exception {
			// Generate the trip
//			trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site hipmunk = new Hipmunk();

			// Set the base URL
			String baseUrl = "https://www.hipmunk.com/flights#";
			hipmunk.setBaseUrl( baseUrl );
			Assert.assertEquals( hipmunk.getBaseUrl(), baseUrl );

			// Set the permutation algorithm
			hipmunk.setPermutationAlgorithm( mockedPermutationAlgorithm );
			// Set the trip
			hipmunk.setTrip( mockedTrip );

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
//			trip = tripGenerator.generateOneWayTrip( isDeparting );

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			//Site hipmunk = new Hipmunk( trip );
			Site hipmunk = new Hipmunk( mockedTrip, mockedPermutationAlgorithm );
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
			//trip = tripGenerator.generateOneWayTrip( isDeparting );

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site hipmunk = new Hipmunk( mockedTrip, mockedPermutationAlgorithm );

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

