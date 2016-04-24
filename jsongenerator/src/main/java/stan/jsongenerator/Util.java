package stan.jsongenerator;

public class Util {
	private Util() {
	}

	public static <T> T nullCheck( T value, String valueName ) {
		if( value == null ) {
			throw new NullPointerException( "The value " + valueName + " is null." );
		}
		return value;
	}
}
