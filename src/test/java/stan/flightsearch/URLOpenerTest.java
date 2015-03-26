package stan.flightsearch;

import org.junit.*;
import java.util.Arrays;
import java.util.List;

public class URLOpenerTest {
	int m_numberOfPagesToOpenAtOnce = 1;
	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void URLOpenerConstructorValidNumberOfPagesToOpen() throws Exception {
			URLOpener urlOpener = new URLOpener( m_numberOfPagesToOpenAtOnce );
			Assert.assertEquals( urlOpener.getNumberOfPagesToOpenAtOnce(), m_numberOfPagesToOpenAtOnce );
		}

	@Test( expected=IllegalArgumentException.class )
		public void URLOpenerConstructorIllegalNumberOfPagesToOpen() throws Exception {
			URLOpener urlOpener = new URLOpener( 0 );
		}

	@Test
		public void URLOpenerSetUrlListWithValidList() throws Exception {
			URLOpener urlOpener = new URLOpener( m_numberOfPagesToOpenAtOnce );
			List<String> list = Arrays.asList( "www.google.com" );
			urlOpener.setUrlList( list );
			Assert.assertTrue( urlOpener.getUrlList().equals( list )  );
		}

	@Test( expected=NullPointerException.class )
		public void URLOpenerSetUrlListWithNullList() throws Exception {
			URLOpener urlOpener = new URLOpener( m_numberOfPagesToOpenAtOnce );
			urlOpener.setUrlList( null );
		}

	@Test
		public void URLOpenerStart() throws Exception {
			URLOpener urlOpener = new URLOpener( m_numberOfPagesToOpenAtOnce );
			List<String> list = Arrays.asList( "www.google.com" );
			urlOpener.setUrlList( list );
			Assert.assertTrue( urlOpener.getUrlList().equals( list )  );
		}

	@After
		public void tearDown() throws Exception {
		}
}
