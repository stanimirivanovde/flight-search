package stan.commandline;

import java.util.ArrayList;
import java.awt.Desktop;

import stan.flightsearch.FlightsReader;
import stan.flightsearch.JsonReader;
import stan.flightsearch.Trip;
import stan.flightsearch.SiteFactory;
import stan.flightsearch.URLOpener;
import stan.flightsearch.SiteTemplate;
import stan.flightsearch.SupportedSitesEnum;
import stan.flightsearch.FlightPermutations;
import stan.flightsearch.PermutationAlgorithm;

public class Flights {
	public static void main( String[] args ) {
		try {
			ProcessCommandLineArguments argumentProcessor = new ProcessCommandLineArguments( args );
			argumentProcessor.parse();

			// Get our trip details
			FlightsReader flightsReader = new JsonReader( argumentProcessor.getJsonFile() );
			Trip[] tripArray = flightsReader.parseFile();
			for( Trip trip : tripArray ) {
				trip.setNumberOfPassangers( argumentProcessor.getNumberOfPassangers() );
			}

			// This will hold the list of sites we want to search
			ArrayList<SiteTemplate> sites = new ArrayList<SiteTemplate>();
			// This will be the permutation algorithm to use
			PermutationAlgorithm algorithm = new FlightPermutations();
			for( Trip trip : tripArray ) {
				for( SupportedSitesEnum s : argumentProcessor.getSites() ) {
					sites.add( SiteFactory.createSiteTemplate( s, trip, algorithm ) );
				}
			}

			// Generate the URLs
			for( SiteTemplate site : sites ) {
				site.generateUrls();
			}

			if( !argumentProcessor.getGenerateOnly() ) {
				if( !Desktop.isDesktopSupported() ) {
					throw new IllegalStateException( "The current system doesn't support a desktop operation." );
				}

				// Grab the current system's desktop.
				Desktop desktop = Desktop.getDesktop();

				for( SiteTemplate site: sites ) {
					// Create a URLOpener object and pass in the generated site urls.
					URLOpener urlOpener = new URLOpener( desktop, site.getGeneratedUrls(), argumentProcessor.getNumberOfPagesToOpenAtOnce(), argumentProcessor.getSleepTime() );
					// Open the URLs
					urlOpener.start();
				}
			}
		} catch( Exception e ) { 
			System.out.println( "Unexpected exception: " + e );
			e.printStackTrace();
		}
	}  
}
