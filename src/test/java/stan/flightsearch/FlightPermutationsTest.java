package stan.flightsearch;

import org.junit.*;
import java.lang.NullPointerException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class FlightPermutationsTest {
	@Before
		public void setUp() throws Exception {
		}

	@Test( expected=NullPointerException.class )
		public void FlightPermutationsNullTravelInfo() {
			FlightPermutations permutations = new FlightPermutations( null );
		}

	@Test( expected=NullPointerException.class )
		public void FlightPermutationsEmptyTravelInfo() {
			FlightPermutations permutations = new FlightPermutations( new TravelInfo() );
			List<PermutationResult> result = permutations.generate();
		}

	@Test
		public void FlightPermutationsValidTravelInfo() {
			String from = "PHL";
			String to = "VAR";
			FlightDate date = new FlightDate( 3, 12, 1984 );
			TravelInfo travelInfo = new TravelInfo(
					Arrays.asList( from ),
					Arrays.asList( to ),
					Arrays.asList( date )
			);
			FlightPermutations permutations = new FlightPermutations( travelInfo );
			List<PermutationResult> results = permutations.generate();
			for( PermutationResult result : results ) {
				Assert.assertEquals( result.getFrom(), from );
				Assert.assertEquals( result.getTo(), to );
				Assert.assertEquals( result.getDate(), date );
			}
		}

	@After
		public void tearDown() throws Exception {
		}
}
