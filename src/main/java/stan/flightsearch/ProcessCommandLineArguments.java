package stan.flightsearch;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.lang.IllegalArgumentException;

public class ProcessCommandLineArguments {
	private Options options = new Options();
	private String[] args = null;

	public ProcessCommandLineArguments( String[] args ) {
		this.args = args;

		options.addOption( "h", "help", false, "Show help.");
		options.addOption( "s", "site", true, "The site you want to search. Currently supported sites are: Kayak, Google, Momondo.");
		options.addOption( "f", "file", true, "The JSON file that contains your search configuration." );
	}

	public void parse() {
		CommandLineParser parser = new BasicParser();

		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);

			if( cmd.hasOption( "h" ) ) {
				help();
			}

			if( cmd.hasOption( "s" ) ) {
				System.out.println( "Using cli argument -s=" + cmd.getOptionValue("s") );
				// Whatever you want to do with the setting goes here
			} else {
				System.out.println( "Missing s option" );
				help();
			}

		} catch (ParseException e) {
			System.out.println( "Failed to parse comand line properties: " + e );
			help();
		}
	}

	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("FlightSearch", options);
		System.exit(0);
	}
}

