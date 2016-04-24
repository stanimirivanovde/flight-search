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
	private final String[] m_args = null;
	private Options m_options = new Options();

	// The actual parsed options will go to these variables
	private String m_depart = null;
	private String m_arrival = null;
	private String m_startDate = null;
	private String m_endDate = null;
	private String m_firstWeekDay = null;
	private String m_lastWeekDay = null;
	private int m_moveValue = 0;
	private String m_fileName = null;

	public ProcessCommandLineArguments( String[] args ) {
		m_args = nullCheck( args, "arguments"  );
	}

	public void createOptions() {
		boolean withArguments = true;
		boolean noArguments = false;

		Option help = new Option( "h", "help", noArguments, "Print this message." );
		Option depart = OptionBuilder
			.withArgName( "AIRPORT" ).
			.withLongOpt( "depart-airport" )
			.withDescription( "The airport for departure." ).
			.isRequired()
			.hasArgs( 1 )
			.create( "d" );
		Option arrival = OptionBuilder
			.withArgName( "AIRPORT" )
			.withLongOpt( "arrival-airport" )
			.withDescription( "Arrival airport." ).
			.isRequired()
			.hasArgs( 1 )
			.create( "a" );
		Option startDate = OptionBuilder
			.withArgName( "DATE" )
			.withLongOpt( "start-date" )
			.withDescription( "The date to start generation. Format is: YYYY-MM-DD" )
			.isRequired()
			.hasArgs( 1 )
			.create( "s" );
		Option endDate = OptionBuilder
			.withArgName( "DATE" )
			.withLongOpt( "end-date" )
			.withDescription( "The end date to stop the generation. Format is: YYYY-MM-DD" )
			.isRequired()
			.hasArgs( 1 )
			.create( "e" );
		Option firstDay = OptionBuilder
			.withArgName( "WEEKDAY" )
			.withLongOpt( "first-weekday" )
			.withDescription( "The first weekday of the trip. Valid values are Mon, Tue, Wed, Thu, Fri, Sat, Sun." )
			.isRequired()
			.hasArgs( 1 )
			.create( "f" );
		Option lastDay = OptionBuilder
			.withArgName( "WEEKDAY" )
			.withLongOpt( "last-weekday" )
			.withDescription( "The end weekday of the trip. Valid values are Mon, Tue, Wed, Thu, Fri, Sat, Sun. This weekday can wrap as in first day Thu, last day Mon." )
			.isRequired()
			.hasArgs( 1 )
			.create( "l" );
		Option moveValue = OptionBuilder
			.withArgName( "VALUE" )
			.withLongOpt( "move-value" )
			.withDescription( "The number of days to generate a moving window. For each move value a new Trip will be generated with the first and last days incremented by one. Allowed values is 0 to 6. Default is 0" )
			.hasArgs( 1 )
			.create( "m" );
		Option fileName = OptionBuilder
			.withArgName( "NAME" )
			.withLongOpt( "write-filename" )
			.withDescription( "The filename to use to save the generated JSON objects. The default filename is: depart_airport-arrival_airport-start_date-end_date.json" )
			.hasArgs( 1 )
			.create( "w" );

		options.addOption( depart );
		options.addOption( arrival );
		options.addOption( startDate );
		options.addOption( endDate );
		options.addOption( firstDay );
		options.addOption( endDay );
		options.addOption( moveValue );
		options.addOption( fileName );
	}

	private void printHelp() {
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp("JsonGenerator", options);
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
				System.exit( 0 );
			}
			m_depart = cmd.getOptionValue( "d" );
			m_arrival = cmd.getOptionValue( "a" );
			m_startDate = cmd.getOptionValue( "s" );
			m_endDate = cmd.getOptionValue( "e" );
			m_firstDay = cmd.getOptionValue( "f" );
			m_lastDay = cmd.getOptionValue( "l" );
			if( cmd.hasOption( "m" ) ) {
				m_moveValue = Integer.parseInt( cmd.getOptionValue( "m" ) );
				if( m_moveValue < 0 || m_moveValue > 6 ) {
					throw new ParseException( "The move value is invalid: " + m_moveValue );
				}
			}
			if( cmd.hasOption( "w" ) ) {
				m_fileName = cmd.getOptionValue( "w" );
			}
		} catch (ParseException e) {
			System.err.println( e.getMessage() );
			printHelp();
			System.exit( 1 );
		}
	}

	public String getDepart() {
		return m_depart.toUpperCase();
	}

	public String getArrival() {
		return m_arrival.toUpperCase();
	}

	public String getStartDate() {
		return m_startDate;
	}

	public String getEndDate() {
		return m_endDate;
	}

	public String getFirstDay() {
		return m_firstDay.toUpperCase();
	}

	public String getLastDay() {
		return m_lastDay.toUpperCase();
	}

	public int getMoveValue() {
		return m_moveValue;
	}

	public String getFileName() {
		return m_fileName;
	}
}

