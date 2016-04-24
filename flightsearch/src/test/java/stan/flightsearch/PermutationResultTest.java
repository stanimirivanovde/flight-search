package stan.flightsearch;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class PermutationResultTest {
	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void PermutationResultConstrucor() throws Exception {
			FlightDate date = new FlightDate( 3, 12, 1984 );
			PermutationResult result = new PermutationResult( "PHL", "VAR", date );
			Assert.assertEquals( result.getFrom(), "PHL" );
			Assert.assertEquals( result.getTo(), "VAR" );
			Assert.assertEquals( result.getDate(), date );
		}

	@Test
		public void PermutationResultSetters() throws Exception {
			FlightDate date = new FlightDate( 3, 12, 1984 );
			PermutationResult result = new PermutationResult();
			result.setFrom( "PHL" );
			result.setTo( "VAR" );
			result.setDate( date );
			Assert.assertEquals( result.getFrom(), "PHL" );
			Assert.assertEquals( result.getTo(), "VAR" );
			Assert.assertEquals( result.getDate(), date );
		}

	@Test( expected=IllegalArgumentException.class )
		public void PermutationResultEmptyFrom() throws Exception {
			FlightDate date = new FlightDate( 3, 12, 1984 );
			PermutationResult result = new PermutationResult( new String(), "VAR", date );
		}

	@Test( expected=IllegalArgumentException.class )
		public void PermutationResultNullFrom() throws Exception {
			FlightDate date = new FlightDate( 3, 12, 1984 );
			PermutationResult result = new PermutationResult( null, "VAR", date );
		}

	@Test( expected=IllegalArgumentException.class )
		public void PermutationResultEmptyTo() throws Exception {
			FlightDate date = new FlightDate( 3, 12, 1984 );
			PermutationResult result = new PermutationResult( "PHL", null, date );
		}

	@Test( expected=IllegalArgumentException.class )
		public void PermutationResultNullTo() throws Exception {
			FlightDate date = new FlightDate( 3, 12, 1984 );
			PermutationResult result = new PermutationResult( "PHL", new String(), date );
		}

	@Test( expected=NullPointerException.class )
		public void PermutationResultNullDate() throws Exception {
			PermutationResult result = new PermutationResult( "PHL", "VAR", null );
		}

	@Test( expected=RuntimeException.class )
		public void PermutationResultEmptyDate() throws Exception {
			PermutationResult result = new PermutationResult( "PHL", "VAR", new FlightDate() );
		}

	@After
		public void tearDown() throws Exception {
		}
}
