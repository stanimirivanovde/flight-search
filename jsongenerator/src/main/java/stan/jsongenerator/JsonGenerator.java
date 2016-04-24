package stan.jsongenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import stan.flightsearch.Trip;

public class JsonGenerator {
	private JsonGenerator() {
	}

	public static void main( String[] args ) {
		try {
			ProcessCommandLineArguments argumentProcessor = new ProcessCommandLineArguments( args );
			argumentProcessor.createOptions();
			argumentProcessor.parse();

			DateParser startDateParser = new DateParser( argumentProcessor.getStartDate() );
			startDateParser.parse();
			System.out.println( "The parsed start date: " + startDateParser );

			DateParser endDateParser = new DateParser( argumentProcessor.getEndDate() );
			endDateParser.parse();
			System.out.println( "The parsed end date: " + endDateParser );

			Calendar startDate = Calendar.getInstance();
			startDate.set( startDateParser.getYear(), startDateParser.getMonth() - 1, startDateParser.getDay() );

			Calendar endDate = Calendar.getInstance();
			// Subtract one from the month in order to accurately use the correct month. For some reason is uses the next month.
			// I'll attribute this to the month starting from 0 instead of 1 and I am too lazy to double check it :/
			endDate.set( endDateParser.getYear(), endDateParser.getMonth() - 1, endDateParser.getDay() );

			WeekdaysEnum firstDayOfWeek = argumentProcessor.getFirstWeekDay();
			WeekdaysEnum lastDayOfWeek = argumentProcessor.getLastWeekDay();
			int numberOfMoveOperations = argumentProcessor.getMoveValue();

			TripGenerator tripGenerator = new TripGenerator( startDate, endDate, numberOfMoveOperations, firstDayOfWeek, lastDayOfWeek, argumentProcessor.getDepart(), argumentProcessor.getArrival(), argumentProcessor.getNumberOfPassangers() );
			List<Trip> tripList = tripGenerator.generateTrips();

			System.out.println( "Writing to file name: " + argumentProcessor.getFileName() );
			JsonWriter writer = new JsonWriter( tripList, argumentProcessor.getFileName() );
			writer.write();
		} catch( Exception e ) { 
			System.out.println( "Unexpected exception: " + e );
			e.printStackTrace();
		}
	}  
}
