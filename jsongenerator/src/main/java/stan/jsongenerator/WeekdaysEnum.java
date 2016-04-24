package stan.jsongenerator;

public enum WeekdaysEnum {
	MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

	private final int m_day;

	WeekdaysEnum( int day ) {
		m_day = day;
	}

	public int getValue() {
		return m_day;
	}
}
