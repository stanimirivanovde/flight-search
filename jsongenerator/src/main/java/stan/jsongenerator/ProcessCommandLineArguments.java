package stan.jsongenerator;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;

public final class ProcessCommandLineArguments {
	private final String[] m_args;
	private Options m_options = null;

	// The actual parsed options will go to these variables
	private String m_depart = null;
	private String m_arrival = null;
	private String m_startDate = null;
	private String m_endDate = null;
	private String m_firstWeekDay = null;
	private String m_lastWeekDay = null;
	private int m_moveValue = 1;
	private String m_fileName = null;
	private int m_numberOfPassangers = 1;

	public ProcessCommandLineArguments( String[] args ) {
		if( args == null ) {
			throw new NullPointerException( "Null arguments." );
		}
		// Make a copy of the array.
		m_args = new String[ args.length ];
		System.arraycopy( args, 0, m_args, 0, args.length );
	}

	public void createOptions() {
		boolean withArguments = true;
		boolean noArguments = false;

		Option help = new Option( "h", "help", noArguments, "Print this message." );
		Option depart = OptionBuilder
			.withArgName( "AIRPORT" )
			.withLongOpt( "depart-airport" )
			.withDescription( "The airport for departure." )
			.isRequired()
			.hasArgs( 1 )
			.create( "d" );
		Option arrival = OptionBuilder
			.withArgName( "AIRPORT" )
			.withLongOpt( "arrival-airport" )
			.withDescription( "Arrival airport." )
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
			.withDescription( "The number of days to generate a moving window. For each move value a new Trip will be generated with the first and last days incremented by one. Allowed values is 1 to 6. Default is 1" )
			.hasArgs( 1 )
			.create( "m" );
		Option fileName = OptionBuilder
			.withArgName( "NAME" )
			.withLongOpt( "write-filename" )
			.withDescription( "The filename to use to save the generated JSON objects. The default filename is: depart_airport-arrival_airport-start_date-end_date.json" )
			.hasArgs( 1 )
			.create( "w" );
		Option passangers = OptionBuilder
			.withArgName( "COUNT" )
			.withLongOpt( "number-of-passangers" )
			.withDescription( "The number of passangers." )
			.hasArgs( 1 )
			.create( "p" );

		m_options = new Options();
		m_options.addOption( help );
		m_options.addOption( depart );
		m_options.addOption( arrival );
		m_options.addOption( startDate );
		m_options.addOption( endDate );
		m_options.addOption( firstDay );
		m_options.addOption( lastDay );
		m_options.addOption( moveValue );
		m_options.addOption( fileName );
		m_options.addOption( passangers );
	}

	public void parse() {
		CommandLineParser parser = new BasicParser();
		try {
			CommandLine cmd = parser.parse( m_options, m_args );
			if( cmd.hasOption( "h" ) ) {
				printHelp();
				System.exit( 0 );
			}
			m_depart = cmd.getOptionValue( "d" );
			m_arrival = cmd.getOptionValue( "a" );
			m_startDate = cmd.getOptionValue( "s" );
			m_endDate = cmd.getOptionValue( "e" );
			m_firstWeekDay = cmd.getOptionValue( "f" );
			m_lastWeekDay = cmd.getOptionValue( "l" );
			if( cmd.hasOption( "m" ) ) {
				m_moveValue = Integer.parseInt( cmd.getOptionValue( "m" ) );
				if( m_moveValue < 0 || m_moveValue > 6 ) {
					throw new ParseException( "The move value is invalid: " + m_moveValue );
				}
			}
			if( cmd.hasOption( "w" ) ) {
				m_fileName = cmd.getOptionValue( "w" );
			} else {
				// Craft the default file name
				String delimiter = "-";
				String extension = ".json";
				m_fileName = new String( m_depart + delimiter + m_arrival + delimiter + m_startDate + delimiter + m_endDate + extension );
			}
			if( cmd.hasOption( "p" ) ) {
				m_numberOfPassangers = Integer.parseInt( cmd.getOptionValue( "p" ) );
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

	public WeekdaysEnum getFirstWeekDay() {
		return this.parseWeekDay( m_firstWeekDay.toUpperCase() );
	}

	public WeekdaysEnum getLastWeekDay() {
		return this.parseWeekDay( m_lastWeekDay.toUpperCase() );
	}

	public int getMoveValue() {
		return m_moveValue;
	}

	public String getFileName() {
		return m_fileName;
	}

	public int getNumberOfPassangers() {
		return m_numberOfPassangers;
	}

	private void printHelp() {
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp( "JsonGenerator", m_options );
	}

	private WeekdaysEnum parseWeekDay( String day ) {
		WeekdaysEnum dayOfWeek = null;
		switch( day ) {
			case "MON":
				dayOfWeek = WeekdaysEnum.MONDAY;
				break;
			case "TUE":
				dayOfWeek = WeekdaysEnum.TUESDAY;
				break;
			case "WED":
				dayOfWeek = WeekdaysEnum.WEDNESDAY;
				break;
			case "THU":
				dayOfWeek = WeekdaysEnum.THURSDAY;
				break;
			case "FRI":
				dayOfWeek = WeekdaysEnum.FRIDAY;
				break;
			case "SAT":
				dayOfWeek = WeekdaysEnum.SATURDAY;
				break;
			case "SUN":
				dayOfWeek = WeekdaysEnum.SUNDAY;
				break;
			default:
				throw new RuntimeException( "Invalid first day of the week." );
		}
		assert( dayOfWeek != null );
		return dayOfWeek;
	}
}

