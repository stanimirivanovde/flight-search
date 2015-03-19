package stan.flightsearch;

import org.junit.*;

public class FlightDateTest {
	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void FlightDateTestPreferredConstructor() throws Exception {
			FlightDate date = new FlightDate( 3, 12, 1984 );
			Assert.assertEquals( date.getDay(), 3 );
			Assert.assertEquals( date.getMonth(), 12 );
			Assert.assertEquals( date.getYear(), 1984 );
		}

	@Test
		public void FlightDateSetters() throws Exception {
			FlightDate date = new FlightDate();
			date.setDay( 3 );
			date.setMonth( 12 );
			date.setYear( 1984 );
			Assert.assertEquals( date.getDay(), 3 );
			Assert.assertEquals( date.getMonth(), 12 );
			Assert.assertEquals( date.getYear(), 1984);
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateZeroDay() throws Exception {
			FlightDate date = new FlightDate( 0, 12, 1984 );
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateNegativeDay() throws Exception {
			FlightDate date = new FlightDate( -1, 12, 1984 );
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateHigherThan31Days() throws Exception {
			FlightDate date = new FlightDate( 32, 12, 1984 );
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateZeroMonth() throws Exception {
			FlightDate date = new FlightDate( 1, 0, 1984 );
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateNegativeMonth() throws Exception {
			FlightDate date = new FlightDate( 1, -1, 1984 );
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateHigherThan12Months() throws Exception {
			FlightDate date = new FlightDate( 1, 13, 1984 );
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateZeroYears() throws Exception {
			FlightDate date = new FlightDate( 1, 1, 0 );
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateNegativeYears() throws Exception {
			FlightDate date = new FlightDate( 1, 1, -1 );
		}

	@Test( expected=IllegalArgumentException.class )
		public void FlightDateHigherThan2100Years() throws Exception {
			FlightDate date = new FlightDate( 1, 1, 2101 );
		}

	@After
		public void tearDown() throws Exception {
		}
}
