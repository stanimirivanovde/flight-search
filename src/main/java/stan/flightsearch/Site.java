package stan.flightsearch;

public interface Site {
	public void generateURLs();
	public void executeSearch();
	public void setBaseUrl( String url );
	public String getBaseUrl();
}
