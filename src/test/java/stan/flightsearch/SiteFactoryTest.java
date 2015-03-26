package stan.flightsearch;

import org.junit.*;

public class SiteFactoryTest {
	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void SiteFactoryCreateSiteKayak() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.KAYAK, new Trip() );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteGoogle() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.GOOGLE, new Trip() );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteMomondo() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.MOMONDO, new Trip() );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteHipmunk() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.HIPMUNK, new Trip() );
			Assert.assertTrue( site != null );
		}

	@Test
		public void SiteFactoryCreateSiteFlightHub() throws Exception {
			SiteFactory siteFactory = new SiteFactory();
			Site site = siteFactory.createSite( SupportedSitesEnum.FLIGHTHUB, new Trip() );
			Assert.assertTrue( site != null );
		}

	@After
		public void tearDown() throws Exception {
		}
}
