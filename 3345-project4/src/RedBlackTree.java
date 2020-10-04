import java.util.StringJoiner;

// this is the red black tree class
public class RedBlackTree<E extends Comparable<E>>
{
	// unchanging color variables
	private static final boolean BLACK = true;
	private static final boolean RED = false;
	
	// root node initialization
	private Node<E> root;

	// empty node creation
	private Node<E> empty;

	// rbt class default constructor
	public RedBlackTree()
	{
		// initializing
		empty = new Node<E>();
		root = empty;
	}

	// insert method to insert given element
	public boolean insert(E element) throws NullPointerException
	{
		// checking if element is null
		if (element == null)
		{
			throw new NullPointerException("Element is null.");
		}
		
		// creating new node
		Node<E> newNode = new Node<E>(element);

		// empty temporary node
		Node<E> temporaryNode = empty;
		
		// root set
		Node<E> thisRoot = root;

		// iterating through
		while (thisRoot != empty)
		{
			// making temporary node 
			temporaryNode = thisRoot;

			// comparing
			int compare = newNode.element.compareTo(thisRoot.element);

			// going right or left
			if (compare < 0)
			{
				thisRoot = thisRoot.leftChild;
			}
			else if (compare > 0)
			{
				thisRoot = thisRoot.rightChild;
			}
			else
			{
				// returning false
				return false;
			}
		}

		// making parent = to temporary
		newNode.parent = temporaryNode;

		// if temporary is empty then root is new node
		if (temporaryNode == empty)
		{
			root = newNode;
		}
		else if (newNode.element.compareTo(temporaryNode.element) < 0)
		{
			temporaryNode.leftChild = newNode;
		}
		else
		{
			temporaryNode.rightChild = newNode;
		}

		// setting nodes
		newNode.leftChild = empty;
		newNode.rightChild = empty;
		
		// setting color
		newNode.color = RED;

		// calling balance method
		treeBalance(newNode);

		// returning
		return true;
	}

	// calls private contains method to check for object
	public boolean contains(Comparable<E> object)
	{
		if (object == null || contains(root, object) == empty)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	// contain emthod that checks
	private Node<E> contains(Node<E> thisRoot, Comparable<E> object)
	{
		// while root isnt empty and obj not roots element
		while (thisRoot != empty && !object.equals(thisRoot.element))
		{
			if (object.compareTo(thisRoot.element) < 0)
			{
				thisRoot = thisRoot.leftChild;
			}
			else
			{
				thisRoot = thisRoot.rightChild;
			}
		}

		// returning current root
		return thisRoot;
	} 

	// public to string method caller
	public String toString()
	{
		// if root is empty then empty string
		if (root == null)
		{
			return "";
		}
		else
		{
			// if not then calling order print
			return printPreorder(root, new StringJoiner(" ")).toString();
		}
	}

	// to print the tree contents in pre order traversal
	private StringJoiner printPreorder(Node<E> thisRoot, StringJoiner sj)
	{
		// if root and elment arent null then ...
		if (thisRoot != null && thisRoot.element != null)
		{
			// adding to string joiner
			sj.add(thisRoot.toString());
			
			// recursive calling
			printPreorder(thisRoot.leftChild, sj);
			printPreorder(thisRoot.rightChild, sj);
		}

		// returning string joiner
		return sj;
	}
	
	// This method balances the tree
	private void treeBalance(Node<E> thisRoot)
	{
		
		while (thisRoot.parent.color == RED)
		{
			if (thisRoot.parent.equals(thisRoot.parent.parent.leftChild))
			{
				Node<E> temporaryNode = thisRoot.parent.parent.rightChild;

				// if color is red then change colors
				if (temporaryNode.color == RED)
				{
					// changing colors
					thisRoot.parent.color = BLACK;
					temporaryNode.color = BLACK;
					thisRoot.parent.parent.color = RED;
					thisRoot = thisRoot.parent.parent;
				}
				else
				{
					// checking and rotating left
					if (thisRoot.equals(thisRoot.parent.rightChild))
					{
						thisRoot = thisRoot.parent;

						// rotating left
						rotateLeft(thisRoot);
					}

					// color set
					thisRoot.parent.color = BLACK;
					thisRoot.parent.parent.color = RED;

					// rotating right
					rotateRight(thisRoot.parent.parent);
				}
			}
			else
			{
				// setting node
				Node<E> temporaryNode = thisRoot.parent.parent.leftChild;

				// if temporary node is red then changing colors
				if (temporaryNode.color == RED)
				{
					// setting colors
					thisRoot.parent.color = BLACK;
					temporaryNode.color = BLACK;
					thisRoot.parent.parent.color = RED;
					thisRoot = thisRoot.parent.parent;
				}
				else
				{
					// checking if need to rotate right
					if (thisRoot.equals(thisRoot.parent.leftChild))
					{
						thisRoot = thisRoot.parent;

						// rotating right
						rotateRight(thisRoot);
					}

					// color set
					thisRoot.parent.color = BLACK;
					thisRoot.parent.parent.color = RED;

					// rotating right
					rotateLeft(thisRoot.parent.parent);
				}
			}
		}

		// setting root color to black
		root.color = BLACK;
	}

	// rotate left method to assist with balancing
	private void rotateLeft(Node<E> thisRoot)
	{
		// setting nodes
		Node<E> temporaryNode = thisRoot.rightChild;
		thisRoot.rightChild = temporaryNode.leftChild;

		// if temp left child is empty setting to root
		if (!temporaryNode.leftChild.equals(empty))
		{
			temporaryNode.leftChild.parent = thisRoot;
		}

		temporaryNode.parent = thisRoot.parent;

		// checking root
		if (thisRoot.parent.equals(empty))
		{
			root = temporaryNode;
		}
		else if (thisRoot.equals(thisRoot.parent.leftChild))
		{
			thisRoot.parent.leftChild = temporaryNode;
		}
		else
		{
			thisRoot.parent.rightChild = temporaryNode;
		}

		// setting nodes
		temporaryNode.leftChild = thisRoot;
		thisRoot.parent = temporaryNode;
	}

	// rotate right method, assists with balancing
	private void rotateRight(Node<E> thisRoot)
	{
		// setting nodes
		Node<E> temporaryNode = thisRoot.leftChild;
		thisRoot.leftChild = temporaryNode.rightChild;

		// if right child is empty then
		if (!temporaryNode.rightChild.equals(empty))
		{
			temporaryNode.rightChild.parent = thisRoot;
		}

		temporaryNode.parent = thisRoot.parent;

		// checking root if its empty or = to right child 
		if (thisRoot.parent.equals(empty))
		{
			root = temporaryNode;
		}
		else if (thisRoot.equals(thisRoot.parent.rightChild))
		{
			thisRoot.parent.rightChild = temporaryNode;
		}
		else
		{
			thisRoot.parent.leftChild = temporaryNode;
		}

		// setting nodes
		temporaryNode.rightChild = thisRoot;
		thisRoot.parent = temporaryNode;
	}


	// ######################## Node #############################
	// ######################## Class ############################

	private static class Node<E extends Comparable<E>>
	{
		// all variables
		private E element;
		private Node<E> leftChild;
		private Node<E> rightChild;
		private Node<E> parent;
		private boolean color;

		// default constructor
		public Node()
		{
			this(null);
		}

		// argument constructor
		public Node(E element)
		{
			color = BLACK;
			leftChild = null;
			rightChild = null;
			this.element = element;
			parent = null;
		}

		// to string function based on color of node
		public String toString()
		{
			if (color == RED)
			{
				return "*" + this.element;
			}
			else
			{
				return "" + this.element;
			}
		}
	}

}