package stan.jsongenerator;

import java.util.Calendar;

public class Util {
	private Util() {
	}

	public static <T> T nullCheck( T value, String valueName ) {
		if( value == null ) {
			throw new NullPointerException( "The value " + valueName + " is null." );
		}
		return value;
	}

	public static int humanToCalendarMonth( int month ) {
		int calendarMonth = 0;
		switch( month ) {
			case 1:
				calendarMonth = Calendar.JANUARY;
				break;
			case 2:
				calendarMonth = Calendar.FEBRUARY;
				break;
			case 3:
				calendarMonth = Calendar.MARCH;
				break;
			case 4:
				calendarMonth = Calendar.APRIL;
				break;
			case 5:
				calendarMonth = Calendar.MAY;
				break;
			case 6:
				calendarMonth = Calendar.JUNE;
				break;
			case 7:
				calendarMonth = Calendar.JULY;
				break;
			case 8:
				calendarMonth = Calendar.AUGUST;
				break;
			case 9:
				calendarMonth = Calendar.SEPTEMBER;
				break;
			case 10:
				calendarMonth = Calendar.OCTOBER;
				break;
			case 11:
				calendarMonth = Calendar.NOVEMBER;
				break;
			case 12:
				calendarMonth = Calendar.DECEMBER;
				break;
			default:
				throw new RuntimeException( "Invalid month: " + month );
		}
		assert( calendarMonth != 0 );
		return calendarMonth;
	}

	public static int calendarToHumanMonth( int month ) {
		int humanMonth = 0;
		switch( month ) {
			case Calendar.JANUARY:
				humanMonth = 1;
				break;
			case Calendar.FEBRUARY:
				humanMonth = 2;
				break;
			case Calendar.MARCH:
				humanMonth = 3;
				break;
			case Calendar.APRIL:
				humanMonth = 4;
				break;
			case Calendar.MAY:
				humanMonth = 5;
				break;
			case Calendar.JUNE:
				humanMonth = 6;
				break;
			case Calendar.JULY:
				humanMonth = 7;
				break;
			case Calendar.AUGUST:
				humanMonth = 8;
				break;
			case Calendar.SEPTEMBER:
				humanMonth = 9;
				break;
			case Calendar.OCTOBER:
				humanMonth = 10;
				break;
			case Calendar.NOVEMBER:
				humanMonth = 11;
				break;
			case Calendar.DECEMBER:
				humanMonth = 12;
				break;
			default:
				throw new RuntimeException( "Invalid calendar month: " + month );
		}
		assert( humanMonth != 0 );
		return humanMonth;
	}

	static public WeekdaysEnum parseWeekDay( String day ) {
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
