package stan.jsongenerator;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import java.text.SimpleDateFormat;

import stan.flightsearch.Trip;
import stan.flightsearch.FlightDate;
import stan.flightsearch.TravelInfo;

public class TripGenerator {
	private final Calendar m_startDate;
	private final Calendar m_endDate;
	private final int m_moveWindowValue;
	private final WeekdaysEnum m_firstWeekday;
	private final WeekdaysEnum m_lastWeekday;
	private final String m_departAirport;
	private final String m_arrivalAirport;
	private final int m_numberOfPassangers;

	public TripGenerator( Calendar startDate, Calendar endDate, int moveWindowValue, WeekdaysEnum firstWeekday, WeekdaysEnum lastWeekday, String departAirport, String arrivalAirport, int numberOfPassangers ) {
		m_startDate = Util.nullCheck( startDate, "start date" );
		m_endDate = Util.nullCheck( endDate, "end date" );
		if( moveWindowValue < 1 ) {
			throw new IllegalArgumentException( "The move window value is invalid: " + moveWindowValue );
		}
		m_moveWindowValue = moveWindowValue;

		m_firstWeekday = Util.nullCheck( firstWeekday, "first weekday" );
		m_lastWeekday = Util.nullCheck( lastWeekday, "last weekday" );
		m_departAirport = Util.nullCheck( departAirport, "start date" );
		m_arrivalAirport = Util.nullCheck( arrivalAirport, "start date" );

		if( numberOfPassangers < 1 ) {
			throw new IllegalArgumentException( "The number of passangers is invalid." );
		}
		m_numberOfPassangers = numberOfPassangers;
	}

	public List<Trip> generateTrips() {
		// Find first date of the week
		Calendar beginningOfWeekCalendar = (Calendar)m_startDate.clone();
		beginningOfWeekCalendar.add( Calendar.DAY_OF_WEEK, beginningOfWeekCalendar.getFirstDayOfWeek() - beginningOfWeekCalendar.get( Calendar.DAY_OF_WEEK ) );

		List<String> departAirportList = new ArrayList<>();
		departAirportList.add( m_departAirport );

		List<String> returnAirportList = new ArrayList<>();
		returnAirportList.add( m_arrivalAirport );

		List<Trip> tripList = new ArrayList<>();
		while( beginningOfWeekCalendar.before( m_endDate ) ) {
			// Perform the sliding window
			MoveWindowGenerator generator = new MoveWindowGenerator( beginningOfWeekCalendar, m_firstWeekday, m_lastWeekday, m_moveWindowValue );

			while( generator.advance() ) {
				Calendar firstDayCalendar = generator.getFirstDayCalendar();
				Calendar lastDayCalendar = generator.getLastDayCalendar();

				FlightDate departDate = new FlightDate( firstDayCalendar.get( Calendar.DAY_OF_MONTH ), Util.calendarToHumanMonth( firstDayCalendar.get( Calendar.MONTH ) ), firstDayCalendar.get( Calendar.YEAR ) ); 
				FlightDate returnDate = new FlightDate( lastDayCalendar.get( Calendar.DAY_OF_MONTH ), Util.calendarToHumanMonth( lastDayCalendar.get( Calendar.MONTH ) ), firstDayCalendar.get( Calendar.YEAR ) ); 
				System.out.println( "The depart date: " + departDate );
				System.out.println( "The return date: " + returnDate );

				List<FlightDate> departDateList = new ArrayList<>();
				departDateList.add( departDate );
				List<FlightDate> returnDateList = new ArrayList<>();
				returnDateList.add( returnDate );

				TravelInfo departTravel = new TravelInfo( departAirportList, returnAirportList, departDateList );
				TravelInfo returnTravel = new TravelInfo( returnAirportList, departAirportList, returnDateList );
				Trip trip = new Trip( departTravel, returnTravel, m_numberOfPassangers );
				tripList.add( trip );
			}
			// Go to the next week.
			beginningOfWeekCalendar.add( Calendar.DAY_OF_WEEK, 7 );
		}
		System.out.println( "Number of trips generated: " + tripList.size() );

		return tripList;
	}
}
