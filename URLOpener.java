import java.awt.Desktop;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.MalformedURLException;

import java.lang.Thread;
import java.util.List;

public class URLOpener {
	private List<String> urls;
	private int sleepTime = 1000;

	public URLOpener( List<String> urlList ) {
		this.urls = urlList;
	}

	public void start() {
		try {
			for( String url : urls ) {
				openWebpage( new URL( url ).toURI() );
				Thread.sleep( sleepTime );
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch( MalformedURLException e ) {
			e.printStackTrace();
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
