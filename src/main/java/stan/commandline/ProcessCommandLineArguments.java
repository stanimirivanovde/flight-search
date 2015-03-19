package stan.commandline;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.util.ArrayList;

import stan.flightsearch.SupportedSitesEnum;

public class ProcessCommandLineArguments {
	private Options options = new Options();
	private String[] args = null;

	// The actual parsed options will go to these variables
	private File jsonFile;
	private ArrayList<SupportedSitesEnum> sites;
	private boolean generateOnly = false;
	private int numberOfPagesToOpenAtOnce = 50;

	public ProcessCommandLineArguments( String[] args ) {
		this.args = args;
		this.createOptions();
	}

	private void createOptions() {
		boolean withArguments = true;
		boolean noArguments = false;

		Option help = new Option( "h", "help", noArguments, "Print this message." );
		Option generate = new Option( "g", "generate-urls", noArguments, "Generate the URLs only without executing them." );
		Option site = OptionBuilder.withArgName( "SITE" )
							.withLongOpt( "site" )
							.withDescription( "The site you want to search. Currently supported sites are: Kayak, Google, Momondo, Hipmunk, FlightHub." )
							.isRequired()
							.hasArgs()
							.create( "s" );
		Option file = OptionBuilder.withArgName( "FILE" )
							.withLongOpt( "file" )
							.withDescription( "The JSON file that contains your search configuration." )
							.isRequired()
							.hasArgs( 1 )
							.create( "f" );
		Option paginate = OptionBuilder.withArgName( "NUMBER OF PAGES" )
							.withLongOpt( "paginate" )
							.withDescription( "The number of pages in a page. When that number of pages has been opened the user will be prompted to press enter in order to continue with the next page. The default number is 50 pages." )
							.hasArgs( 1 )
							.create( "p" );

		options.addOption( help );
		options.addOption( site );
		options.addOption( file );
		options.addOption( generate );
		options.addOption( paginate );
	}

	private void printHelp() {
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp("FlightSearch", options);
		System.exit(0);
	}

	private ArrayList<SupportedSitesEnum> getSupportedSites( String[] sitesArray ) {
		ArrayList<SupportedSitesEnum> list = new ArrayList<SupportedSitesEnum>();
		int arrayIndex = 0;
		try {
			while( arrayIndex < sitesArray.length ) {
				list.add( SupportedSitesEnum.valueOf( sitesArray[ arrayIndex ].toUpperCase() ) );
				++arrayIndex;
			}
		} catch( IllegalArgumentException e ) {
			System.err.println( "Unsupported site has been specified: " + sitesArray[ arrayIndex ] );
			printHelp();
		}
		return list;
	}

	public void parse() {
		CommandLineParser parser = new BasicParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			if( cmd.hasOption( "h" ) ) {
				printHelp();
			}
			jsonFile = new File( cmd.getOptionValue( "f" ) );
			if( !jsonFile.exists() ) {
				throw new ParseException( "The specified JSON file doesn't exist." );
			}
			sites = getSupportedSites( cmd.getOptionValues( "s" ) );
			generateOnly = cmd.hasOption( "g" );
			numberOfPagesToOpenAtOnce = Integer.parseInt( cmd.getOptionValue( "p" ) );
		} catch (ParseException e) {
			System.err.println( e.getMessage() );
			printHelp();
		}
	}

	public File getJsonFile() { return jsonFile; }
	public ArrayList<SupportedSitesEnum> getSites() { return sites; }
	public boolean getGenerateOnly() { return generateOnly; }
	public int getNumberOfPagesToOpenAtOnce() { return numberOfPagesToOpenAtOnce; }
}

