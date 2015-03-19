package stan.commandline;

import java.util.ArrayList;

import stan.flightsearch.FlightsReader;
import stan.flightsearch.JsonReader;
import stan.flightsearch.Trip;
import stan.flightsearch.SiteFactory;
import stan.flightsearch.URLOpener;
import stan.flightsearch.Site;
import stan.flightsearch.SupportedSitesEnum;

public class Flights {
	public static void main( String[] args ) {
		try {
			ProcessCommandLineArguments argumentProcessor = new ProcessCommandLineArguments( args );
			argumentProcessor.parse();

			// Get our trip details
			FlightsReader flightsReader = new JsonReader( argumentProcessor.getJsonFile() );
			Trip trip = flightsReader.parseFile();

			// This is used to create each site
			SiteFactory factory = new SiteFactory();
			// This will hold the list of sites we want to search
			ArrayList<Site> sites = new ArrayList<Site>();
			for( SupportedSitesEnum s : argumentProcessor.getSites() ) {
				sites.add( factory.createSite( s, trip ) );
			}

			// Generate the URLs
			for( Site site : sites ) {
				site.generateUrls();
			}
			if( !argumentProcessor.getGenerateOnly() ) {
				// Open the URLs
				URLOpener urlOpener = new URLOpener( argumentProcessor.getNumberOfPagesToOpenAtOnce() );
				for( Site site: sites ) {
					urlOpener.setUrlList( site.getGeneratedUrls() );
					urlOpener.start();
				}
			}
		} catch( Exception e ) { 
			System.out.println( "Unexpected exception: " + e );
			e.printStackTrace();
		}
	}  
}
