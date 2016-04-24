package stan.flightsearch;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.awt.Desktop;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class URLOpenerTest {
	private Desktop m_mockedDesktop;
	private List<String> m_urlsToOpen = new ArrayList<String>( Arrays.asList( "http://test-url.com" ) );
	private int m_numberOfPages = 1;
	private int m_sleepTime = 1000;

	@Before
		public void setUp() throws Exception {
			//! Mock the Desktop class.
			m_mockedDesktop = mock( Desktop.class );
		}

	@Test
		public void URLOpenerConstructorValidArguments() throws Exception {
			// Stub the Desktop mock
			when( m_mockedDesktop.isSupported( Desktop.Action.BROWSE ) ).thenReturn( true );

			URLOpener urlOpener = new URLOpener( m_mockedDesktop, m_urlsToOpen, m_numberOfPages, m_sleepTime );
			Assert.assertEquals( urlOpener.getNumberOfPagesToOpenAtOnce(), m_numberOfPages );
		}

	@Test( expected=NullPointerException.class )
		public void URLOpenerConstructorNullDesktop() throws Exception {
			URLOpener urlOpener = new URLOpener( null, m_urlsToOpen, m_numberOfPages, m_sleepTime );
		}

	@Test( expected=IllegalStateException.class )
		public void URLOpenerConstructorBadDesktop() throws Exception {
			URLOpener urlOpener = new URLOpener( m_mockedDesktop, m_urlsToOpen, m_numberOfPages, m_sleepTime );
		}

	@Test( expected=NullPointerException.class )
		public void URLOpenerConstructorNullUrlsToOpen() throws Exception {
			// Stub the Desktop mock
			when( m_mockedDesktop.isSupported( Desktop.Action.BROWSE ) ).thenReturn( true );

			URLOpener urlOpener = new URLOpener( m_mockedDesktop, null, m_numberOfPages, m_sleepTime );
		}

	@Test( expected=IllegalArgumentException.class )
		public void URLOpenerConstructorIllegalNumberOfPagesToOpen() throws Exception {
			// Stub the Desktop mock
			when( m_mockedDesktop.isSupported( Desktop.Action.BROWSE ) ).thenReturn( true );

			URLOpener urlOpener = new URLOpener( m_mockedDesktop, m_urlsToOpen, 0, m_sleepTime );
		}

	@Test( expected=IllegalArgumentException.class )
		public void URLOpenerConstructorIllegalSleepTime() throws Exception {
			// Stub the Desktop mock
			when( m_mockedDesktop.isSupported( Desktop.Action.BROWSE ) ).thenReturn( true );

			URLOpener urlOpener = new URLOpener( m_mockedDesktop, m_urlsToOpen, m_numberOfPages, -1 );
		}

	@Test
		public void URLOpenerStart() throws Exception {
			// Stub the Desktop mock
			when( m_mockedDesktop.isSupported( Desktop.Action.BROWSE ) ).thenReturn( true );

			URLOpener urlOpener = new URLOpener( m_mockedDesktop, m_urlsToOpen, m_numberOfPages, m_sleepTime );
			urlOpener.start();
		}

	@After
		public void tearDown() throws Exception {
		}
}
