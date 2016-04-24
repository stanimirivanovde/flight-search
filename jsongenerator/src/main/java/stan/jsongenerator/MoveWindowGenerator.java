package stan.jsongenerator;

import java.util.Calendar;

public class MoveWindowGenerator {
	private final Calendar m_beginningOfWeek;

	private int m_numberOfMoveOperations;
	private int m_firstDayValue;
	private int m_lastDayValue;
	private Calendar m_firstDayCalendar;
	private Calendar m_lastDayCalendar;

	public MoveWindowGenerator( Calendar beginningOfWeek, WeekdaysEnum firstDay, WeekdaysEnum lastDay, int numberOfMoveOperations ) {
		m_beginningOfWeek = Util.nullCheck( beginningOfWeek, "beginning of week" );
		m_firstDayValue = Util.nullCheck( firstDay, "first day" ).getValue();
		m_lastDayValue = Util.nullCheck( lastDay, "last day" ).getValue();

		if( numberOfMoveOperations < 0 ) {
			throw new IllegalArgumentException( "The number of move operations is invalid: " + numberOfMoveOperations );
		}
		m_numberOfMoveOperations = numberOfMoveOperations;

		// Advanced to the next week if we wrap.
		if( m_lastDayValue < m_firstDayValue ) {
			m_lastDayValue += 7;
		}
	}

	// Returns true if we advanced successfully or false if there is no more advancing possible.
	public boolean advance() {
		if( m_numberOfMoveOperations < 1 ) {
			return false;
		}

		// Perform the sliding window
		m_firstDayCalendar = (Calendar)m_beginningOfWeek.clone();
		m_firstDayCalendar.add( Calendar.DAY_OF_YEAR, m_firstDayValue );

		m_lastDayCalendar = (Calendar)m_firstDayCalendar.clone();
		m_lastDayCalendar.add( Calendar.DAY_OF_YEAR, m_lastDayValue - m_firstDayValue );

		++m_firstDayValue;
		++m_lastDayValue;
		--m_numberOfMoveOperations;

		return true;
	}
	public Calendar getFirstDayCalendar() {
		return m_firstDayCalendar;
	}

	public Calendar getLastDayCalendar() {
		return m_lastDayCalendar;
	}
}
