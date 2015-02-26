package stan.flightsearch;

public class PermutationResult {
	private String from;
	private String to;
	private String date;

	public PermutationResult( String from, String to, String date ) {
		this.from = from;
		this.to = to;
		this.date = date;
	}

	public void setFrom( String s ) { from = s; }
	public void setTo( String s ) { to = s; }
	public void setDate( String s ) { date = s; }

	public String getFrom() { return from; }
	public String getTo() { return to; }
	public String getDate() { return date; }
}

