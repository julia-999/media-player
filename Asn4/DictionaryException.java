// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Nov 23rd, 2020

// Class for a dictionary exception
public class DictionaryException extends RuntimeException {

	// creates the error message
	public DictionaryException (String s) {
		super("Dictionary Exception: " + s);
	}
	
}
