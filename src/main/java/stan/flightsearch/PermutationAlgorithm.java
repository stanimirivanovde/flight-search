package stan.flightsearch;

import java.util.List;

/**
 * This is an interface that implements the functionality of a permutation algorithm.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public interface PermutationAlgorithm {
	/**
	 * Generates all permutations of the specified TravelInfo object.
	 * The function will loop through the list of origins, destinations and dates
	 * and craft a new list containig all permutations.
	 * @return List &lt; {@link PermutationResult PermutationResult} &gt; of all combinations of origins, destinations and dates.
	 * @throws NullPointerException If the object that is being permutated doesn't have origins, destinations and dates defined.
	 **/
	public List<PermutationResult> generate();

	/**
	 * Sets a TravelInfo object that will contain the origins, destinations and dates to be permuted.
	 * @param travelInfo A TravelInfo object that will contain the origins, destinations and dates to be permuted. 
	 * @throws NullPointerException If the specified TravelInfo object is null.
	 **/
	public void setTravelInfo( TravelInfo travelInfo );
}
