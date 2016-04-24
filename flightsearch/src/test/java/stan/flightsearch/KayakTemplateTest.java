package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class KayakTemplateTest {
	private Trip m_mockedTrip;
	private PermutationAlgorithm m_mockedPermutationAlgorithm;

	@Before
		public void setUp() throws Exception {
			// Set up our mocks before every test.
			m_mockedTrip = mock( Trip.class );
			m_mockedPermutationAlgorithm = mock( PermutationAlgorithm.class );
		}

	@Test
		public void kayakMultiCityTripConstructor() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getDepart() ).thenReturn( new TravelInfo() );
			when( m_mockedTrip.getReturning() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) )
				.thenReturn( Arrays.asList( new PermutationResult( "MAD", "PHL", new FlightDate( 24, 12, 1984 ) ) ) );

			SiteTemplate kayak = SiteFactory.createSiteTemplate( SupportedSitesEnum.KAYAK, m_mockedTrip, m_mockedPermutationAlgorithm );
			kayak.generateUrls();
			List<String> urls = kayak.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03/MAD-PHL/1984-12-24" );
		}

	@Test
		public void kayakOneWayDepart() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getDepart() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			SiteTemplate kayak = SiteFactory.createSiteTemplate( SupportedSitesEnum.KAYAK, m_mockedTrip, m_mockedPermutationAlgorithm );
			kayak.generateUrls();
			List<String> urls = kayak.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}

	@Test
		public void kayakOneWayReturn() throws Exception {
			// Stub the mocked Trip methods to return empty but valid TravelInfo objects.
			when( m_mockedTrip.getReturning() ).thenReturn( new TravelInfo() );

			// Stub the mocked permutation algorithm with the result we expect.
			when( m_mockedPermutationAlgorithm.generate() )
				.thenReturn( Arrays.asList( new PermutationResult( "PHL", "VAR", new FlightDate( 03, 12, 1984 ) ) ) );

			SiteTemplate kayak = SiteFactory.createSiteTemplate( SupportedSitesEnum.KAYAK, m_mockedTrip, m_mockedPermutationAlgorithm );
			kayak.generateUrls();
			List<String> urls = kayak.getGeneratedUrls();
			// Should get only one url
			Assert.assertEquals( urls.size(), 1 );
			Assert.assertEquals( urls.get( 0 ), "http://www.kayak.com/flights/PHL-VAR/1984-12-03" );
		}
}

