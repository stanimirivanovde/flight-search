package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class MomondoTest {
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

			Site momondo = new Momondo( m_mockedTrip, m_mockedPermutationAlgorithm );
			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.momondo.com/multicity/?Search=true&TripType=multi&SegNo=2&SO0=PHL&SD0=VAR&SDP0=03-12-1984&SO1=MAD&SD1=PHL&SDP1=24-12-1984&AD=1&TK=ECO&NA=false" );
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

			Site momondo = new Momondo();

			// Set the base URL
			String baseUrl = "http://www.momondo.com";
			momondo.setBaseUrl( baseUrl );
			Assert.assertEquals( momondo.getBaseUrl(), baseUrl );

			// Set the permutation algorithm
			momondo.setPermutationAlgorithm( m_mockedPermutationAlgorithm );
			// Set the trip
			momondo.setTrip( m_mockedTrip );

			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.momondo.com/multicity/?Search=true&TripType=multi&SegNo=2&SO0=PHL&SD0=VAR&SDP0=03-12-1984&SO1=MAD&SD1=PHL&SDP1=24-12-1984&AD=1&TK=ECO&NA=false" );
		}

	@Ignore( "enable this when the functionality is implemented" )
	@Test
		public void oneWayDepart() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getDepart() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			Site momondo = new Momondo( m_mockedTrip, m_mockedPermutationAlgorithm );
			momondo.generateUrls();
			List<String> urls = momondo.getGeneratedUrls();
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

			Site momondo = new Momondo( m_mockedTrip, m_mockedPermutationAlgorithm );
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

