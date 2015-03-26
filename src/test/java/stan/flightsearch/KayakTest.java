package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class KayakTest {
	//private Trip trip;
	//private TripGenerator tripGenerator = new TripGenerator();

	private static final Trip mockedTrip = mock( Trip.class );
	private static final PermutationAlgorithm mockedPermutationAlgorithm = mock( PermutationAlgorithm.class );

	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void kayakMultiCityTripConstructor() throws Exception {
			// Generate the trip
	//		trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site kayak = new Kayak( mockedTrip, mockedPermutationAlgorithm );
			kayak.generateUrls();
			List<String> urls = kayak.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03/MAD-PHL/1984-12-24" );
		}

	@Test
		public void kayakMultiCityTripSetters() throws Exception {
			// Generate the trip
//			trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site kayak = new Kayak();

			// Set the base URL
			String baseUrl = "http://www.kayak.com";
			kayak.setBaseUrl( baseUrl );
			Assert.assertEquals( kayak.getBaseUrl(), baseUrl );

			// Set the permutation algorithm
			kayak.setPermutationAlgorithm( mockedPermutationAlgorithm );
			// Set the trip
			kayak.setTrip( mockedTrip );

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
//			trip = tripGenerator.generateOneWayTrip( isDeparting );

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site kayak = new Kayak( mockedTrip, mockedPermutationAlgorithm );
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
//			trip = tripGenerator.generateOneWayTrip( isDeparting );

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site kayak = new Kayak( mockedTrip, mockedPermutationAlgorithm );
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

