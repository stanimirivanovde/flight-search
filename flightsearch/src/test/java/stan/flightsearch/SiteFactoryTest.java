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
			SiteTemplate site = SiteFactory.createSiteTemplate( SupportedSitesEnum.KAYAK, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteGoogle() throws Exception {
			SiteTemplate site = SiteFactory.createSiteTemplate( SupportedSitesEnum.GOOGLE, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteMomondo() throws Exception {
			SiteTemplate site = SiteFactory.createSiteTemplate( SupportedSitesEnum.MOMONDO, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteHipmunk() throws Exception {
			SiteTemplate site = SiteFactory.createSiteTemplate( SupportedSitesEnum.HIPMUNK, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteFlightHub() throws Exception {
			SiteTemplate site = SiteFactory.createSiteTemplate( SupportedSitesEnum.FLIGHTHUB, mockedTrip, mockedPermutationAlgorithm );
			Assert.assertTrue( site != null );
		}

	@Test( expected=IllegalArgumentException.class )
		public void SiteFactoryCreateSiteUnknown() throws Exception {
			SiteTemplate site = SiteFactory.createSiteTemplate( SupportedSitesEnum.UNKNOWN, mockedTrip, mockedPermutationAlgorithm );
		}

	@Test( expected=NullPointerException.class )
		public void SiteFactoryNullTrip() throws Exception {
			SiteTemplate site = SiteFactory.createSiteTemplate( SupportedSitesEnum.HIPMUNK, null, mockedPermutationAlgorithm );
		}

	@Test( expected=NullPointerException.class )
		public void SiteFactoryNullPermutationAlgorithm() throws Exception {
			SiteTemplate site = SiteFactory.createSiteTemplate( SupportedSitesEnum.HIPMUNK, mockedTrip, null );
		}

	@After
		public void tearDown() throws Exception {
		}
}
