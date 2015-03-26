package stan.flightsearch;

import java.util.List;

/**
 * An interface that all Sites should implement.
 * The interface provides common functions that should be provided by each site.
 *
 * @author Stanimir Ivanov
 * @version %I%, %G%
 **/
public interface Site {
	/**
	 * Generates all the possible URLs.
	 * The generated URLs depend on the provided permutation algorithm
	 * and {@link Trip Trip} details.
	 **/
	public void generateUrls();

	/**
	 * Sets the base URL to use for the site. Normally each site would define
	 * a default URL to use so this is optional.
	 * @param url A String object that contains the base URL of the site.
	 **/
	public void setBaseUrl( String url );

	/**
	 * Sets the Trip details to be used for the site.
	 * Based on the supplied permutation algorithm all trip details will be
	 * permuted.
	 * @param trip A Trip object containing the trip details.
	 **/
	public void setTrip( Trip trip );

	/**
	 * Returns the current base URL for the site.
	 * @return A String containing the current URL for the site.
	 **/
	public String getBaseUrl();

	/**
	 * Returns the generated URLs for the site.
	 * @return A List&lt;String&gt; containing the generated URLs for the site.
	 **/
	public List<String> getGeneratedUrls();
}
