// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Nov 23rd, 2020

public class OrderedDictionary implements OrderedDictionaryADT {

	// variables to be used by the binary search tree
	private Node root;
	private BinarySearchTree bst;

	// constructor, creates an ordered dictionary with a null root and associated BST
	public OrderedDictionary( ) {
		root = null;
		bst = new BinarySearchTree(root);
	}

	// returns the data at the specified key
	@Override
	public DataItem get(Key k) {

		if (bst.get(root, k).isLeaf()) // if it is a leaf there is nothing that matches the key
			return null;
		
		return bst.get(root, k).getData();
	}

	// puts a data item into the BST
	@Override
	public void put(DataItem d) throws DictionaryException {
		
		// if the BST is empty, sets it as the root
		if (bst.getRoot() == null) {
			root = new Node(d);
			root.setLeftChild(new Node(new DataItem(null, null)));
			root.getLeftChild().setParent(root);
			root.setRightChild(new Node(new DataItem(null, null)));
			root.getRightChild().setParent(root);
			bst.setRoot(root);
			return;
		}

		// error if the key already exists
		if (!bst.put(root, d.getKey(), d))
			 throw new DictionaryException("Duplicate key");

	}

	// removes an item with key k from the BST
	@Override
	public void remove(Key k) throws DictionaryException {
		
		// error if the key is not in the BST
		if (!bst.remove(root, k))
			throw new DictionaryException("Non existent key");
	
		// updates the root just in case it was changed
		root = bst.getRoot();

	}

	// returns the successor of key k
	@Override
	public DataItem successor(Key k) {

		return bst.successor(root, k).getData();

	}

	// returns the predecessor of key k
	@Override
	public DataItem predecessor(Key k) {

		return bst.predecessor(root, k).getData();

	}

	// returns the data item with the smallest key in the dictionary
	@Override
	public DataItem smallest() {

		return bst.smallest(root).getData();
	}

	// returns the data item with the largest key in the ordered dictionary
	@Override
	public DataItem largest() {

		return bst.largest(root).getData();
	}

}
