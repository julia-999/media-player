// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Nov 23rd, 2020

// Represents a Binary Search Tree
public class BinarySearchTree {

	// variable for the root of the tree
	private Node root;

	// creates a BST with specified root
	public BinarySearchTree(Node r) {
		root = r;
	}

	// sets the root
	public void setRoot(Node r) {
		root = r;
	}

	// gets the root
	public Node getRoot() {
		return root;
	}

	// returns the node with the smallest key
	public Node smallest(Node r) {
		if (r.isLeaf()) return null;
		else { // returns the leftmost child
			Node p = r;
			while (!p.getLeftChild().isLeaf())
				p = p.getLeftChild();
			return p;
		}
	}

	// returns the node with the largest key
	public Node largest(Node r) {
		if (r.isLeaf()) return null;
		else { // returns the rightmost child
			Node p = r;
			while (!p.getRightChild().isLeaf())
				p = p.getRightChild();
			return p;
		}
	}

	// returns the node with the specified key if it exists
	public Node get(Node r, Key k) {
		if (r.isLeaf()) return r; // if its a leaf return the leaf
		else {
			if (k.compareTo(r.getData().getKey()) == 0) return r; // if the key is equal returns the current node, otherwise goes left/right depending on the key
			else if (k.compareTo(r.getData().getKey()) < 0)
				return get(r.getLeftChild(), k); // recursively searches from the left child
			else
				return get(r.getRightChild(), k); // recursively searches from the right child
		}
	}

	// inserts a data item with a specified key into the tree, returns false if it already exists
	public boolean put(Node r, Key k, DataItem d) {
		Node p = get(r, k); // gets the place where k should go
		if (!p.isLeaf()) return false; // if it's not a leaf, it existed before so returns false
		else { // sets the data and updates node pointers
			p.setData(d);
			p.setLeftChild(new Node(new DataItem(null, null)));
			p.getLeftChild().setParent(p);
			p.setRightChild(new Node(new DataItem(null, null)));
			p.getRightChild().setParent(p);
			return true;
		}
	}

	// removes a node with specified key from the list, returns false if it does not exist
	public boolean remove(Node r, Key k) {
		Node p = get(r, k); // gets the node with the key k
		if (p.isLeaf()) return false; // if the key returns a leaf, it does not exist so it returns false
		else {
			if (p.getLeftChild().isLeaf() || p.getRightChild().isLeaf()) { // if a child is a leaf

				if (p.getLeftChild().isLeaf() && !p.getRightChild().isLeaf()) { // if the left child is the leaf, updates the pointers
					Node c2 = p.getRightChild();
					Node p2 = p.getParent();
					if (p == root) // if necessary updates the root variable
						root = c2;
					else {
						p2.setRightChild(c2);
						c2.setParent(p2);
					}	
				}	
				else if (p.getRightChild().isLeaf() && !p.getLeftChild().isLeaf()) { // if the right child is the leaf, updates the pointers
					Node c2 = p.getLeftChild();
					Node p2 = p.getParent();
					if (p == root) // if necessary updates the root variable
						root = c2;
					else {
						p2.setLeftChild(c2);
						c2.setParent(p2);
					}	
				}
				else { // if both children are leaves, just sets the node to be a leaf
					p.setData(new DataItem(null, null));
					p.setLeftChild(null);
					p.setRightChild(null);
				}
				
			}
			else { // otherwise replaces the node with the smallest relative node, and removes that smallest relative node
				Node s = smallest(p.getRightChild());
				p.setData(s.getData());
				remove(s, s.getData().getKey());
			}
			return true;
		}
	}

	// returns the node of the successor of a specified key, null if it does not exist
	public Node successor(Node r, Key k) {
		if (r.isLeaf()) return null; // returns null if the node is a leaf
		else {
			Node p = get(r, k);
			if (!p.isLeaf() && !p.getRightChild().isLeaf()) // if the right child exists
				return smallest(p.getRightChild()); // returns the smallest from the right child
			else { // otherwise moves up until the root or until p is a left child
				Node p2 = p.getParent();
				while (p != r && p2.getRightChild() == p) {
					p = p2;
					p2 = p.getParent();
				}
				if (p == r) return null; // if p is the root
				else return p2; // if p is a left child returns it's parent
			}
		}
	}

	// returns the node of the predecessor of a specified key, null if it does not exist
	public Node predecessor(Node r, Key k) {
		if (r.isLeaf()) return null; // returns null if the node is a leaf
		else {
			Node p = get(r, k);
			if (!p.isLeaf() && !p.getLeftChild().isLeaf()) // if the left child exists
				return largest(p.getLeftChild()); // returns the smallest from the left child
			else { // otherwise moves up until the root or until p is a right child
				Node p2 = p.getParent();
				while (p != r && p2.getLeftChild() == p) {
					p = p2;
					p2 = p.getParent();
				}
				if (p == r) return null; // if p is the root
				else return p2; // if p is a right child returns it's parent
			}
		}
	}

}
