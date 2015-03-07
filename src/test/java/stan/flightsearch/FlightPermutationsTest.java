package stan.flightsearch;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class FlightPermutationsTest {
	@Before
		public void setUp() throws Exception {
		}

	@Ignore( "The code needs to be fixed to handle exceptions." )
	@Test
		public void FlightPermutationsEmptyTravelInfo() throws Exception {
			FlightPermutations permutations = new FlightPermutations( new TravelInfo() );
			List<PermutationResult> result = permutations.generate();
		}

	@After
		public void tearDown() throws Exception {
		}
}
