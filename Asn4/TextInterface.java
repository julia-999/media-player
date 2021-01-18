// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Nov 23rd, 2020

// import statements
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// represent the text interface used for understanding commands
public class TextInterface {

	// variables for the dictionary and the classes needed to use the files
	private static OrderedDictionary dict;
	private static SoundPlayer sp = new SoundPlayer();
	private static PictureViewer pv = new PictureViewer();
	private static ShowHTML sh = new ShowHTML();
	private static RunCommand rc = new RunCommand();
	private static String kinds[] = {"sound", "picture", "url", "program", "definition"}; // the different kinds possible

	// main method
	public static void main(String[] args) {

		// creates a new dictionary
		dict = new OrderedDictionary();

		// reads the file
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(args[0])); 
			
			// variables for the types of data
			String name, content, kind;

			// reads lines while it is not empty
			while (true) {
				name = inFile.readLine();
				if (name == null) break; // breaks if it is empty
				content = inFile.readLine();
				kind = getType(content); // finds out the kind of file from the ending

				dict.put(new DataItem(new Key(name, kind), content)); // inserts the data into the dictionary
			}

		}
		catch (IOException e) { 
			System.out.println("Error opening file. " + e.getMessage());
			System.exit(0);
		}
		catch (Exception e) {
			System.out.println("Error in input file: "+e.getMessage());
			System.exit(0);
		}

		// creates a string reader object
		StringReader keyboard = new StringReader();
		String line = null;

		do {
			
			// prompts for and reads the line
			line = keyboard.read("Enter next command: ");
			String words[] = line.split(" "); // splits the line into words

			try {
				userCommands(words); // obeys the command
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (!line.equals("end")); // stops if the command is end
	}

	// determines what type of file it is based on the ending, returns as a string
	public static String getType(String c) {
		if (c.endsWith(".wav") || c.endsWith(".mid"))
			return "sound";
		if (c.endsWith(".gif") || c.endsWith(".jpg"))
			return "picture";
		if (c.endsWith(".html"))
			return "url";
		if (c.endsWith(".exe"))
			return "program";
		return "definition"; // returns as a definition if no file types were matched
	}

	// method that contains procedures for all the commands
	public static void userCommands(String[] s) {
		String command = s[0]; // the command

		// if get
		if (command.equals("get")) {
			
			// variable for whether the key exists
			boolean exists = false;

			// loops through the different kinds of files
			for (int i = 0; i < kinds.length; i++) {
				if (dict.get(new Key(s[1], kinds[i])) != null) { // if the key exists

					exists = true;

					DataItem d = dict.get(new Key(s[1], kinds[i])); // determines the data item

					// does the appropriate action
					try { 
						if (kinds[i].equals("definition")) System.out.println(d.getContent());
						if (kinds[i].equals("url")) sh.show(d.getContent());
						if (kinds[i].equals("program")) rc.run(d.getContent());
						if (kinds[i].equals("sound")) sp.play(d.getContent());
						if (kinds[i].equals("picture")) pv.show(d.getContent());
					} catch (MultimediaException e) {
						e.printStackTrace();
					} 

				}
			}

			// if the key does not exist prints out the preceding and succeeding names
			if (!exists) {
				System.out.println("The word " + s[1] + " is not in the ordered dictionary");
				System.out.print("Preceding word: ");
				if (dict.predecessor(new Key(s[1], "")) != null)
					System.out.println(dict.predecessor(new Key(s[1], "")).getKey().getName());
				System.out.print("Following word:");
				if (dict.successor(new Key(s[1], "")) != null)
					System.out.println(dict.successor(new Key(s[1], "")).getKey().getName());
			}
		}
		
		// if remove
		else if (command.equals("remove")) {

			// tries to remove the key, prints message if it cannot
			try {
				dict.remove(new Key(s[1], s[2]));
			} catch (DictionaryException e) {
				System.out.println("No record in the ordered dictionary has key (" + s[1] + ", " + s[2] +")");
			} 
		}
		
		// if add
		else if (command.equals("add")) {

			// tries to add in a data item, prints message if it cannot
			try {
				dict.put(new DataItem(new Key(s[1], s[2]), s[3]));
			} catch (DictionaryException e) {
				System.out.println("A record with the given key (" + s[1] + ", " + s[2] + ") is already in the ordered dictionary.");
			} 
		}
		
		// if list
		else if (command.equals("list")) {
			
			// finds the successor
			DataItem suc = dict.successor(new Key(s[1], ""));
			
			// if the successor is null or does not start with the prefix, prints message
			if (suc == null || !suc.getKey().getName().startsWith(s[1])) {
				System.out.println("No name attributes in the ordered dictionary start with prefix " + suc.getKey().getName());
			}
			
			// prints the successor and gets the next successor
			System.out.print(suc.getKey().getName());
			suc = dict.successor(suc.getKey());
			
			// loops while there is a successor and it starts with the prefix
			while (suc != null && suc.getKey().getName().startsWith(s[1])) {
				System.out.print(", " + suc.getKey().getName());
				suc = dict.successor(suc.getKey());
			}
			
			System.out.println();
			
		}
		
		// if first
		else if (command.equals("first")) {
			
			//prints the smallest item in the dictionary
			DataItem small = dict.smallest();
			System.out.println(small.getKey().getName() + ", " + small.getKey().getKind() + ", " + small.getContent());
		}
		
		// if last
		else if (command.equals("last")) {
			
			// prints the largest item in the dictionary
			DataItem large = dict.largest();
			System.out.println(large.getKey().getName() + ", " + large.getKey().getKind() + ", " + large.getContent());
		}
		
		// if end
		else if (command.equals("end")) {
			System.out.println("Program terminated.");
			return;
		}
		
		// if not matched to a command
		else {
			System.out.println("Invalid command. Please try again.");
		}

	}


}
