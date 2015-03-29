package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class GoogleTest {
	private Trip m_mockedTrip;
	private PermutationAlgorithm m_mockedPermutationAlgorithm;

	@Before
		public void setUp() throws Exception {
			// Set up our mocks before every test.
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

			Site google = new Google( m_mockedTrip, m_mockedPermutationAlgorithm );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.google.com/flights/#search;iti=PHL_VAR_1984-12-03*MAD_PHL_1984-12-24;tt=m" );
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

			Site google = new Google();

			// Set the base URL
			String baseUrl = "https://www.google.com/flights/#search;iti=";
			google.setBaseUrl( baseUrl );
			Assert.assertEquals( google.getBaseUrl(), baseUrl );

			// Set the permutation algorithm
			google.setPermutationAlgorithm( m_mockedPermutationAlgorithm );
			// Set the trip
			google.setTrip( m_mockedTrip );

			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "https://www.google.com/flights/#search;iti=PHL_VAR_1984-12-03*MAD_PHL_1984-12-24;tt=m" );
		}

	@Ignore( "enable this when the functionality is implemented" )
	@Test
		public void oneWayDepart() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getDepart() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site google = new Google( m_mockedTrip, m_mockedPermutationAlgorithm );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
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

			Site google = new Google( m_mockedTrip, m_mockedPermutationAlgorithm );
			google.generateUrls();
			List<String> urls = google.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@After
		public void tearDown() throws Exception {
		}
}

