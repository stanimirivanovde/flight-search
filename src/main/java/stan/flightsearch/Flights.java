package stan.flightsearch;

public class Flights {
	public static void main( String[] args ) {
		try {
			if( args.length != 1 ) {
				printHelp();
				System.exit( 1 );
			}

		//	ProcessCommandLineArguments argumentProcessor = new ProcessCommandLineArguments( args );
		//	argumentProcessor.parse();

			String fileName = args[ 0 ];

			// Get our trip details
			FlightsReader flightsReader = new FlightsReader( fileName );
			Trip trip = flightsReader.parseJson();

			SiteFactory factory = new SiteFactory();

			// Get our sites ready
			Site kayak = factory.createSite( SupportedSitesEnum.KAYAK, trip );
			Site momondo = factory.createSite( SupportedSitesEnum.MOMONDO, trip );
			Site google = factory.createSite( SupportedSitesEnum.GOOGLE, trip );

			kayak.generateUrls();
			google.generateUrls();
			momondo.generateUrls();

			URLOpener urlOpener = new URLOpener();
			/*
			// Open Kayak links
			urlOpener.setUrlList( kayak.getGeneratedUrls() );
			urlOpener.start();
			// Open Google links
			urlOpener.setUrlList( google.getGeneratedUrls() );
			urlOpener.start();
			*/
			urlOpener.setUrlList( momondo.getGeneratedUrls() );
			urlOpener.start();

		} catch( Exception e ) { 
			System.out.println( "Error in opening the page: " + e );
			e.printStackTrace();
		}
	}  

	private static void printHelp() {
		System.out.print(
				"This is a program that will run flight searches on multiple websites. The supported list so far is Kayak and Google.\n" +
				"The program will generate all the permutations based on a JSON config file that is supplied on the command line.\n\n" +
				"Usage:\n" +
				"    Flights <json_file>\n\n" +
				"JSON File Structure:\n" +
				"    There are currently 2 supported JSON formats: one way flights and multi-city flights. The multi-city flights are\n" +
				"capped at 2 (origin->destination and origin->destination pairs). Check the two example files in the resources directory:\n" +
				"resources/multi-city.json\n" +
				"resources/one-way.json\n"
		);
	}
}
