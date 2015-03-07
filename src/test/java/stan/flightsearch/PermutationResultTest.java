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

	@After
		public void tearDown() throws Exception {
		}
}
