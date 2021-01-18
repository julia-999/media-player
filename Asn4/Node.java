// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Nov 23rd, 2020

// Creates a Node object to be used in the binary search tree
public class Node {

	// variables for the linked nodes and the data within
	private Node leftChild, rightChild, parent;
	private DataItem data;
	
	// Constructs an empty node
	public Node() {
		leftChild = null;
		rightChild = null;
		parent = null;
		data = null;
	}
	
	// Constructs a node with data
	public Node(DataItem d) {
		leftChild = null;
		rightChild = null;
		parent = null;
		data = d;
	}
	
	// returns the left node
	public Node getLeftChild() {
		return leftChild;
	}
	
	// sets the left node
	public void setLeftChild(Node node) {
		leftChild = node;
	}
	
	// returns the right node
	public Node getRightChild() {
		return rightChild;
	}
	
	// sets the right node
	public void setRightChild(Node node) {
		rightChild = node;
	}
	
	// returns the parent node
	public Node getParent() {
		return parent;
	}
	
	// sets the parent node
	public void setParent(Node node) {
		parent = node;
	}
	
	// returns the data stored in the node
	public DataItem getData() {
		return data;
	}
	
	// sets the data of the node
	public void setData(DataItem d) {
		data = d;
	}
	
	// returns true if the node is a leaf
 	public boolean isLeaf() {
 		return (leftChild == null) && (rightChild == null);
 	}
	
}
