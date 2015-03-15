package stan.flightsearch;

public class SiteFactory {
	public Site createSite( SupportedSitesEnum siteId, Trip trip ) {
		switch( siteId ) {
			case KAYAK:
				return new Kayak( trip );
			case GOOGLE:
				return new Google( trip );
			case MOMONDO:
				return new Momondo( trip );
			case HIPMUNK:
				return new Hipmunk( trip );
			case FLIGHTHUB:
				return new FlightHub( trip );
			default:
				System.out.println( "Bad site ID has been provided: " + siteId );
				return null;
		}
	}
}
