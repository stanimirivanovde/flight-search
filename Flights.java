import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
 
public class Flights {
	public static void main( String[] args ) {
		try {
			FlightsReader flightsReader = new FlightsReader( args[ 0 ] );
			Trip trip = flightsReader.parseJson();
			Site kayak = new Kayak( trip );
			kayak.executeSearch();
		} catch( Exception e ) { 
			System.out.println( "Error in opening the page: " + e );
			e.printStackTrace();
		}
	}  
}
