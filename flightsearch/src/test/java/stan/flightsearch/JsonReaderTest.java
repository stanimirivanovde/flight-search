package stan.flightsearch;

import org.junit.*;

import java.util.Arrays;
import java.util.List;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReaderTest {
	@Before
		public void setUp() throws Exception {
		}

	@Test
		public void JsonReaderMultiCityConstructorToString() throws Exception {
			// Load the files
			URL multiCityJsonUrl = this.getClass().getResource("/multi-city.json");
			File jsonFile = new File(multiCityJsonUrl.getFile());
			URL multiCityOutputUrl = this.getClass().getResource( "/multi-city.output" );

			FlightsReader flightsReader = new JsonReader( jsonFile );
			Trip trip = flightsReader.parseFile();

			// Read the output file into a string
			String outputString = new String( Files.readAllBytes( Paths.get( multiCityOutputUrl.getFile() ) ), "UTF-8" );
			// Compare the toString() representation of the returned trip with the test file we have.
			Assert.assertEquals( trip.toString(), outputString );
		}

	@Test
		public void JsonReaderMultiCityConstructorSetterToString() throws Exception {
			// Load the files
			URL multiCityJsonUrl = this.getClass().getResource("/multi-city.json");
			File jsonFile = new File(multiCityJsonUrl.getFile());
			URL multiCityOutputUrl = this.getClass().getResource( "/multi-city.output" );

			FlightsReader flightsReader = new JsonReader();
			flightsReader.setFile( jsonFile );

			Trip trip = flightsReader.parseFile();

			// Read the output file into a string
			String outputString = new String( Files.readAllBytes( Paths.get( multiCityOutputUrl.getFile() ) ), "UTF-8" );
			Assert.assertEquals( trip.toString(), outputString );
		}

	@Test
		public void JsonReaderGetter() throws Exception {
			// Load the files
			URL multiCityJsonUrl = this.getClass().getResource("/multi-city.json");
			File jsonFile = new File(multiCityJsonUrl.getFile());

			FlightsReader flightsReader = new JsonReader();
			flightsReader.setFile( jsonFile );

			Assert.assertEquals( flightsReader.getFile(), jsonFile );
		}

	@Test( expected=IOException.class )
		public void JsonReaderInvalidJsonFile() throws Exception {
			// Load the files
			URL multiCityJsonUrl = this.getClass().getResource("/multi-city.output");
			File jsonFile = new File(multiCityJsonUrl.getFile());

			FlightsReader flightsReader = new JsonReader( jsonFile );

			// This should throw an IOException.
			Trip trip = flightsReader.parseFile();
		}

	@Test( expected=IOException.class )
		public void JsonReaderFileDoesntExist() throws Exception {
			// Load a file that doesn't exist.
			FlightsReader flightsReader = new JsonReader( new File( "abcdefg.xyz" ) );
		}

	@After
		public void tearDown() throws Exception {
		}
}
