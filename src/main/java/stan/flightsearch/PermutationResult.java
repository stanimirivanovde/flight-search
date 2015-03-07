package stan.flightsearch;

public class PermutationResult {
	private String from;
	private String to;
	private FlightDate date;

	public PermutationResult() {}
	public PermutationResult( String from, String to, FlightDate date ) {
		this.from = from;
		this.to = to;
		this.date = date;
	}

	public void setFrom( String s ) { from = s; }
	public void setTo( String s ) { to = s; }
	public void setDate( FlightDate d ) { date = d; }

	public String getFrom() { return from; }
	public String getTo() { return to; }
	public FlightDate getDate() { return date; }
}

