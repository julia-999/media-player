// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Nov 23rd, 2020

// Represents a key object that stores the name and kind
public class Key {

	// variables to store the name and kind
	private String name, kind;

	// constructs a key object with the name and kind
	public Key(String word, String type) {
		name = word.toLowerCase();
		kind = type;
	}

	// returns the name
	public String getName() {
		return name;
	}

	// returns the kind
	public String getKind() {
		return kind;
	}

	/// compares one key to the other, returning 0 if they are equal, -1 if this.key is smaller, 1 if this.key is larger
	public int compareTo(Key k) {
		
		// compares each char of the name
		for (int i = 0; i < this.getName().length() && i < k.getName().length(); i++) {
			if ((int)this.getName().charAt(i) != (int)k.getName().charAt(i))
				return ((int)this.getName().charAt(i) - (int)k.getName().charAt(i)) / Math.abs((int)this.getName().charAt(i) - (int)k.getName().charAt(i));
		}
		
		// if the name begins the same but one is longer
		if (this.getName().length() != k.getName().length())
			return (this.getName().length() - k.getName().length()) / Math.abs(this.getName().length() - k.getName().length());
		
		// compares each char of the kind
		for (int i = 0; i < this.getKind().length() && i < k.getKind().length(); i++) {
			if ((int)this.getKind().charAt(i) != (int)k.getKind().charAt(i))
				return ((int)this.getKind().charAt(i) - (int)k.getKind().charAt(i)) / Math.abs((int)this.getKind().charAt(i) - (int)k.getKind().charAt(i));
		}
		
		// if the kind begins the same but one is longer
		if (this.getKind().length() != k.getKind().length())
			return (this.getKind().length() - k.getKind().length()) / Math.abs(this.getKind().length() - k.getKind().length());
		
		// if they are the same returns 0
		return 0;
		
	}

}
