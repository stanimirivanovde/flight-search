package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class FlightHubTest {
	private Trip m_mockedTrip;
	private PermutationAlgorithm m_mockedPermutationAlgorithm;

	@Before
		public void setUp() throws Exception {
			m_mockedTrip = mock( Trip.class );
			m_mockedPermutationAlgorithm = mock( PermutationAlgorithm.class );
		}

	@Test
		public void multiCityTripConstructor() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getDepart() ).thenReturn( new TravelInfo() );
			when( m_mockedTrip.getReturning() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site flightHub = new FlightHub( m_mockedTrip, m_mockedPermutationAlgorithm );
			flightHub.generateUrls();
			List<String> urls = flightHub.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.flighthub.com/flight/search?seg0_from=phl&seg0_date=1984-12-03&seg0_to=var&seg1_from=mad&seg1_date=1984-12-24&seg1_to=phl&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy" );
		}

	@Test
		public void multiCityTripSetters() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getDepart() ).thenReturn( new TravelInfo() );
			when( m_mockedTrip.getReturning() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			Site flightHub = new FlightHub();

			// Set the base URL
			String baseUrl = "http://www.flighthub.com/flight/search?";
			flightHub.setBaseUrl( baseUrl );
			Assert.assertEquals( flightHub.getBaseUrl(), baseUrl );

			// Set the permutation algorithm
			flightHub.setPermutationAlgorithm( m_mockedPermutationAlgorithm );
			// Set the trip
			flightHub.setTrip( m_mockedTrip );

			flightHub.generateUrls();
			List<String> urls = flightHub.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.flighthub.com/flight/search?seg0_from=phl&seg0_date=1984-12-03&seg0_to=var&seg1_from=mad&seg1_date=1984-12-24&seg1_to=phl&num_adults=1&num_children=0&num_infants=0&num_infants_lap=0&seat_class=Economy" );
		}

	@Ignore( "enable this when the functionality is implemented" )
	@Test
		public void oneWayDepart() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getDepart() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site flightHub = new FlightHub( m_mockedTrip, m_mockedPermutationAlgorithm );
			flightHub.generateUrls();
			List<String> urls = flightHub.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@Ignore( "enable this when the functionality is implemented" )
	@Test
		public void oneWayReturn() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getReturning() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site flightHub = new FlightHub( m_mockedTrip, m_mockedPermutationAlgorithm );
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

