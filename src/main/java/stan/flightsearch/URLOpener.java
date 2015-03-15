package stan.flightsearch;

import java.awt.Desktop;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.MalformedURLException;

import java.lang.Thread;
import java.util.List;
import java.io.IOException;

public class URLOpener {
	private List<String> urls;
	private int sleepTime = 1000;
	private int numberOfPagesToOpenAtOnce = 50;

	// Empty default constructor
	public URLOpener() {}

	public URLOpener( int pagination ) {
		this.numberOfPagesToOpenAtOnce = pagination;
	}

	public void setUrlList( List<String> list ) { urls = list; }
	public void setNumberOfPagesToOpenAtOnce( int pages ) { numberOfPagesToOpenAtOnce = pages; }
	public List<String> getUrlList() { return urls; }

	public void start() {
		try {
			int count = 0;
			int total = 0;
			for( String url : urls ) {
				if( count++ >= numberOfPagesToOpenAtOnce ) {
					total += count;
					count = 0;
					System.out.format( "So far I have opened %d pages out of %d. Press ENTER to continue opening the next %d pages.\n", total, urls.size(), numberOfPagesToOpenAtOnce );
					System.in.read();
				}
				openWebpage( new URL( url ).toURI() );
				Thread.sleep( sleepTime );
			}
		} catch( IOException e ) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		//} catch( MalformedURLException e ) {
		//	e.printStackTrace();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}

	private static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
