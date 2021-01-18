// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Nov 23rd, 2020

// Represents a DataItem object that stores the key and content
public class DataItem {
	
	// variables that store the key and content
	private Key theKey;
	private String content;
	
	// constructs the data item with the key and content
	public DataItem(Key k, String data) {
		theKey = k;
		content = data;
	}
	
	// returns the key
	public Key getKey() {
		return theKey;
	}
	
	// returns the content
	public String getContent() {
		return content;
	}

}
