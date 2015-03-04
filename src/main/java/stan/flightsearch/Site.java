package stan.flightsearch;

import java.util.List;

public interface Site {
	public void generateUrls();
	public void setBaseUrl( String url );
	public String getBaseUrl();
	public List<String> getGeneratedUrls();
	public void setTrip( Trip trip );
}
