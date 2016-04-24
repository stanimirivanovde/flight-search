package stan.flightsearch;

import java.awt.Desktop;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.MalformedURLException;

import java.lang.Thread;
import java.util.List;
import java.io.IOException;

/**
 * Opens a URL in the default browser.
 * This class is responsible for opening a URL in the default browser on any platform. The time between
 * opening each URLs is 1s by default. Since openening too many pages can deplete the system's memory there
 * is a limit on how many pages can be opened at once. The current limit is 50 pages, but this can be overwritten.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public class URLOpener {
	//! The urls to open
	private List<String> m_urls;
	//! The time to sleep between openeing each URL
	private int m_sleepTime;
	//! The number of URLs to open at once before pausing.
	private int m_numberOfPagesToOpenAtOnce;
	//! This is the Desktop object used to open URLs in the default system browser.
	private final Desktop m_desktop;

	/**
	 * Preferred Constructor.
	 * It takes an integer of the number of pages to open at once. This number can scale based on the current system
	 * and the available resources.
	 * @param desktop A Desktop object representing the current desktop of the system. It will be used to open the URLs in the system default browser.
	 * @param urlList A {@code List<String>} object representing the list of URLs to open in the browser.
	 * @param numberOfPagesToOpen An integer representing the number of pages to open at once. This number should be greater than 0.
	 * @param sleepTime An integer representing the number of miliseconds to sleep before opening each URL. This number should be positive or 0.
	 * @throws NullPointerException If the desktop is null or the urlList is null
	 * @throws IllegalStateException If the desktop object doesn't support Desktop.Action.BROWSE action.
	 * @throws IllegalArgumentException If the pagination integer is less than or equal to 0.
	 **/
	public URLOpener( Desktop desktop, List<String> urlList, int numberOfPagesToOpen, int sleepTime ) {
		if( desktop == null ) {
			throw new NullPointerException( "The specified Desktop object is null." );
		}
		if( !desktop.isSupported( Desktop.Action.BROWSE ) ) {
			throw new IllegalStateException( "The current system's desktop doesn't support web browsing operations." );
		}
		if( urlList == null ) {
			throw new NullPointerException( "The specified URL list should not be null." );
		}
		if( numberOfPagesToOpen <= 0 ) {
			throw new IllegalArgumentException( "The number of pages to open at once should be greater than 0." );
		}
		if( sleepTime < 0 ) {
			throw new IllegalArgumentException( "The sleep time should be a positive number or 0." );
		}

		m_desktop = desktop;
		m_urls = urlList;
		m_numberOfPagesToOpenAtOnce = numberOfPagesToOpen;
		m_sleepTime = sleepTime;
	}

	// Getters {{{
	/**
	 * Getter for the internal URL list.
	 * @return A List&lt;String&gt; object containing the list of URLs to open.
	 **/
	public List<String> getUrlList() { return m_urls; }

	/**
	 * Getter for the number of pages to open at once.
	 * @return An Integer representing the number of pages to open at once.
	 **/
	public int getNumberOfPagesToOpenAtOnce() { return m_numberOfPagesToOpenAtOnce; }
	// }}}

	/**
	 * Starts the opening of web pages in the default browser.
	 * @throws RuntimeException If reading from the standard input fails, or converting a String object to URL object fails,
	 *                          or converting from an URL object to an URI object fails, or there is an error in the Thread's sleep routine,
	 *                          or there is another problem with opening the web page in the browser. The actual exception is propagated as well.
	 **/
	public void start() { // {{{
		try {
			int count = 0;
			int total = 0;
			for( String url : m_urls ) {
				if( count++ >= m_numberOfPagesToOpenAtOnce ) {
					total += count;
					count = 0;
					System.out.format( "So far I have opened %d pages out of %d. Press ENTER to continue opening the next %d pages.\n", total, m_urls.size(), m_numberOfPagesToOpenAtOnce );
					System.in.read();
				}
				openWebpage( new URL( url ).toURI() );
				Thread.sleep( m_sleepTime );
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException( "Error converting a String object to an URL object: " + e.getMessage(), e );
		} catch( IOException e ) {
			throw new RuntimeException( "Error reading from stdin: " + e.getMessage(), e );
		} catch (URISyntaxException e) {
			throw new RuntimeException( "Error converting the URL to URI: " + e.getMessage(), e );
		} catch( InterruptedException e ) {
			throw new RuntimeException( "Error trying to sleep for " + m_sleepTime + " seconds: " + e.getMessage(), e );
		}
	} // }}}

	/**
	 * Opens a URI in the default browser.
	 * @param uri An URI object to open in the default browser. The URI should not be null.
	 * @throws NullPointerException If the specified URI is null.
	 * @throws IllegalStateException If the current system doesn't support desktop operations, or the system's desktop
	 *                               doesn't support web browsing operations. 
	 * @throws RuntimeException If the user's default browser is not found, or failed to launch.
	 **/
	private void openWebpage(URI uri) { // {{{
		if( uri == null ) {
			throw new NullPointerException( "The specified URI is null." );
		}

		try {
			m_desktop.browse( uri );
		} catch( IOException e ) {
			throw new RuntimeException( "The user default browser is not found, or failed to launch: " + e.getMessage(), e );
		}
	} // }}}
}
