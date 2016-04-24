package stan.jsongenerator;

public class DateParser {
	private final String m_dateString;
	// The delimiter for the date.
	private static final String DELIMITER = "-";

	private int m_year = 0;
	private int m_month = 0;
	private int m_day = 0;

	public DateParser( String dateString ) {
		if( dateString == null ) {
			throw new NullPointerException( "The specified date is null." );
		}
		m_dateString = dateString;
	}

	public void parse() {
		String[] dateTokens = m_dateString.split( DELIMITER );
		// We expect the date to have the format YYYY-MM-DD.
		for( int i = 0; i < dateTokens.length; ++i ) {
			String currentToken = dateTokens[ i ];
			switch( i ) {
				// Year
				case 0:
					m_year = Integer.parseInt( currentToken );
					break;
				// Month
				case 1:
					m_month = Integer.parseInt( currentToken );
					break;
				// Day
				case 2:
					m_day = Integer.parseInt( currentToken );
					break;
				default:
					throw new RuntimeException( "Invalid index for the date tokens: " + i );
			}
		}
		assert( m_year != 0 );
		assert( m_month != 0 );
		assert( m_day != 0 );
	}

	public int getYear() {
		return m_year;
	}

	public int getMonth() {
		return m_month;
	}

	public int getDay() {
		return m_day;
	}

	@Override
	public String toString() {
		return new String( m_year + DELIMITER + m_month + DELIMITER + m_day );
	}
}
