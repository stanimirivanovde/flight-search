package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class MomondoTest {
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
		//	trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site momondo = new Momondo( mockedTrip, mockedPermutationAlgorithm );
			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.momondo.com/multicity/?Search=true&TripType=multi&SegNo=2&SO0=PHL&SD0=VAR&SDP0=03-12-1984&SO1=MAD&SD1=PHL&SDP1=24-12-1984&AD=1&TK=ECO&NA=false" );
		}

	@Test
		public void multiCityTripSetters() throws Exception {
			// Generate the trip
		//	trip = tripGenerator.generateMultiCityTrip();

			// Stub the mocked permutation algorithm with the result we expect.
			when( mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site momondo = new Momondo();

			// Set the base URL
			String baseUrl = "http://www.momondo.com";
			momondo.setBaseUrl( baseUrl );
			Assert.assertEquals( momondo.getBaseUrl(), baseUrl );

			// Set the permutation algorithm
			momondo.setPermutationAlgorithm( mockedPermutationAlgorithm );
			// Set the trip
			momondo.setTrip( mockedTrip );

			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.momondo.com/multicity/?Search=true&TripType=multi&SegNo=2&SO0=PHL&SD0=VAR&SDP0=03-12-1984&SO1=MAD&SD1=PHL&SDP1=24-12-1984&AD=1&TK=ECO&NA=false" );
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

			Site momondo = new Momondo( mockedTrip, mockedPermutationAlgorithm );
			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
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

			Site momondo = new Momondo( mockedTrip, mockedPermutationAlgorithm );
			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@After
		public void tearDown() throws Exception {
		}
}

