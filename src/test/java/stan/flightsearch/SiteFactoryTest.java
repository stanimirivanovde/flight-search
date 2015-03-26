package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;


public class SiteFactoryTest {
	// Define the mocks
	private static final PermutationAlgorithm mockedPermutationAlgorithm = mock( PermutationAlgorithm.class );
	private static final Trip mockedTrip = mock( Trip.class );

	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void SiteFactoryCreateSiteKayak() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.KAYAK, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteGoogle() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.GOOGLE, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteMomondo() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.MOMONDO, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteHipmunk() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.HIPMUNK, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteFlightHub() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.FLIGHTHUB, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@After
		public void tearDown() throws Exception {
		}
}
