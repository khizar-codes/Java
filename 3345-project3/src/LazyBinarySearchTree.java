// Lazy Binary Search Tree class which implements the bst with deletion that
// doesnt remove nodes but marks them as deleted.

public class LazyBinarySearchTree
{
	// valid key value range
	public static int minimumKey = 1;
	public static int maximumKey = 99;

	// root of tree
	private TreeNode root;

	// constructor initializes root to null
	public LazyBinarySearchTree()
	{
		this.root = null;
	}

	// insert node in tree function
		public boolean insert(int key) throws IllegalArgumentException
		{
			// checking if inside range
			if ((key < minimumKey) || (key > maximumKey))
			{
				// if not then throwing exception
				throw new IllegalArgumentException("Error in Insert: IllegalArgumentException raised");
			}

			// if tree is empty then root is null so we insert into root
			if (this.root == null)
			{
				// If there are no nodes in the tree
				this.root = new TreeNode(key);
				
				// returning true
				return true;
			}
			else
			{
				// If there are nodes then calling insert pt 2
				return insert(this.root, key);
			}
		}
	
	// inserting node in tree 2nd function
	private boolean insert(TreeNode thisNode, int key)
	{
		// return variable
		boolean insertReturn = false;

		// checking if key is less than
		if (key < thisNode.getKey())
		{
			if (thisNode.getLeftChild() != null)
			{
				insertReturn = insert(thisNode.getLeftChild(), key);
			}
			else
			{
				thisNode.setLeftChild(new TreeNode(key));
				insertReturn = true;
			}

		}
		// checking if key is greater than
		else if (key > thisNode.getKey())
		{
			if (thisNode.getRightChild() != null)
				insertReturn = insert(thisNode.getRightChild(), key);
			else
			{
				thisNode.setRightChild(new TreeNode(key));
				insertReturn = true;
			}
		}
		// if there is one already check if deleted or undeleted
		else
		{
			// if deleted then undelete
			if (thisNode.checkDeleted())
			{
				thisNode.setDeleted(false);
				insertReturn = true;

			}
			// if not deleted then return false
			else
			{
				insertReturn = false;
			}
		}

		// increment node height
		thisNode.setHeight(1 + Math.max(height(thisNode.getLeftChild()), height(thisNode.getRightChild())));
		
		// returning to boolean accordingly
		return insertReturn;
	}
	
		// delete key from tree
		public boolean delete(int key) throws IllegalArgumentException
		{
			// checking if key is in range
			if ((key < minimumKey) || (key > maximumKey))
			{
				throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
			}

			// making sure tree isnt empty
			if (this.root != null)
			{
				return delete(this.root, key);
			}
			else
			{
				return false;
			}
		}
	

	// delete key from tree pt 2
	private boolean delete(TreeNode thisNode, int key)
	{
		// making a delete boolean
		boolean deleted = true;

		// checking if key is less than
		if (key < thisNode.getKey())
		{
			if (thisNode.getLeftChild() != null)
			{
				deleted = delete(thisNode.getLeftChild(), key);
			}
			else
			{
				deleted = false;
			}

		}
		// checking if key is greater than
		else if (key > thisNode.getKey())
		{
			if (thisNode.getRightChild() != null)
			{
				deleted = delete(thisNode.getRightChild(), key);
			}
			else
			{
				// if its not null then return is false
				deleted = false;
			}

		}
		else
		{
			// if its deleted then return is false
			if (thisNode.checkDeleted())
			{
				deleted = false;
			}
			else
			{
				// if we delete then return is true
				thisNode.setDeleted(true);
				deleted = true;
			}
		}

		// returning accordingly
		return deleted;
	}
	
	// to check if key is in the tree pt 1
		public boolean contains(int key) throws IllegalArgumentException
		{
			// Checking if key is in range
			if ((key < minimumKey) || (key > maximumKey))
			{
				throw new IllegalArgumentException("Error in contains: IllegalArgumentException raised");
			}

			// checking tree not empty
			if (this.root != null)
			{
				// if not then calling pt2 and returning
				return contains(this.root, key);
			}
			else
			{
				// if empty then returning false
				return false;
			}
		}

	// check if non deleted key is there
	private boolean contains(TreeNode thisNode, int key)
	{
		// intializing variable to false
		boolean found = false;

		// if key is less than then going on left side
		if (key < thisNode.getKey())
		{
			// making sure left isnt null
			if (thisNode.getLeftChild() != null)
			{
				found = contains(thisNode.getLeftChild(), key);
			}
			else
			{
				// if null then return is false not found
				found = false;
			}

		}
		// if key is greater than then going on right side
		else if (key > thisNode.getKey())
		{
			// making sure right isnt null
			if (thisNode.getRightChild() != null)
			{
				found = contains(thisNode.getRightChild(), key);
			}
			else
			{
				// if null then return is false
				thisNode.setRightChild(new TreeNode(key));
				found = false;
			}
		}
		else
		{
			// check if node is deleted
			if (thisNode.checkDeleted())
			{
				// if deleted return is false not found
				found = false;
			}
			else
			{
				// if not deleted then return is true, found.
				found = true;
			}
		}

		// returns accordingly
		return found;
	}

	
	// height of node
	private int height(TreeNode node)
		{
			// check if null
			if (node == null)
			{
				return 0;
			}
			else
			{
				// return node height
				return node.getHeight();
			}
		}

	// function to get tree height
	public int height()
	{
		if (this.root != null)
		{
			return (this.root.getHeight() - 1);
		}
		else
		{
			return -1;
		}
	}

	// finding minimum key in tree
	public int findMin()
	{
		// check tree empty or not
		if (this.root != null)
		{

			int minimum = 100;
			
			if(!this.root.checkDeleted())
			{
				minimum = this.root.getKey();
			}			

			// Checking if left child is there
			if (this.root.getLeftChild() != null)
			{
				return findMin(this.root, minimum);
			}
			else
			{
				// returning the minumum value
				return minimum;
			}
		}

		// if not then return -1
		return -1;
	}
	
	// function to find minimum keyvalue in tree
	private int findMin(TreeNode node, int minimum)
	{
		if (node.getLeftChild() != null)
		{
			// Checking if node is not deleted
			//if (!node.checkDeleted() && node.getLeftChild().checkDeleted() == false)
			
			// checking if left node is deleted or not
			if (node.getLeftChild().checkDeleted() == false)
			{
				// if not then checking if less than minimum and setting it equal
				if (node.getLeftChild().getKey() < minimum)
				{
					minimum = node.getLeftChild().getKey();
				}
			}
			else
			{
				// checking if left node's right child exists
				if (node.getLeftChild().getRightChild() != null)
				{
					// if it does then checking if it is less then minimum
					if(node.getLeftChild().getRightChild().getKey() < minimum 
							&& node.getLeftChild().getRightChild().checkDeleted() == false)
					{
						// setting minimum equal to if it is less than minimum
						minimum = node.getRightChild().getLeftChild().getKey();
					}
				}
				
			}
			// then recursive
			return findMin(node.getLeftChild(), minimum);
		}
		else
		{
			// returning minimum value in tree
			return minimum;
		}
	}

	// find max key pt 1
	public int findMax()
	{
		
		// Check if root is not null
		if (this.root != null)
		{
			// setting max to 0
			int maximum = 0;
			
			// checking if root is deleted or not
			if (!this.root.checkDeleted())
			{
				maximum = this.root.getKey();
			}

			// Checking right side is there
			if (this.root.getRightChild() != null)
			{
				return findMax(this.root, maximum);
			}
			else
			{
				// reutrns max node
				return maximum;
			}
		}

		// if not then return -1
		return -1;
	}
	
	// find maximum key in tree function
	private int findMax(TreeNode node, int maximum)
	{
		
		if (node.getRightChild() != null)
		{
			// check on right side and if node isnt deleted
			if (node.getRightChild().checkDeleted() == false)
			{
				// if its greater then setting max to it
				if (node.getRightChild().getKey() > maximum)
				{
					maximum = node.getRightChild().getKey();
				}
			}
			else
			{
				// checking left child of right if its not deleted
				if( node.getRightChild().getLeftChild() != null)
				{
					// if not deleted then checking if its bigger then max 
					if(node.getRightChild().getLeftChild().getKey() > maximum 
							&& node.getRightChild().getLeftChild().checkDeleted() == false)
					{
						maximum = node.getRightChild().getLeftChild().getKey();
					}
				}
		
			}
			
			// returning maximum value by recursive calling
			return findMax(node.getRightChild(), maximum);
		}
		else
		{
			// return maximum value
			return maximum;
		}
	}

	
	// counts all elements in tree 
	private int size(TreeNode node, int total)
	{
		// make sure node isnt null
		if (node != null)
		{
			total += 1;
			
			// for right side
			total = size(node.getRightChild(), total);

			// for left side
			total = size(node.getLeftChild(), total);
		}
		
		// returning total
		return total;
	}

	
	// total elements in tree
	public int size()
	{
		// make sure tree not empty
		if (this.root != null)
		{
			return size(this.root, 0);
		}
		
		// if empty return 0
		return 0;
	}
	
	// Prints the returning string buffer of the traversal by converting to string 
	public String toString()
	{
		// creating string buffer
		StringBuffer total = new StringBuffer();
		
		// calling pre order to fill buffer
		total = preOrderTraversal(this.root, total);
		
		// making string buffer to string for return
		return total.toString();
	}

	// does pre order traversal and returns the string with all in it
	public StringBuffer preOrderTraversal(TreeNode node, StringBuffer traversal)
	{
		if (node != null)
		{
			// Append key at node
			// Check if node is deleted
			if (node.checkDeleted())
			{
				traversal.append("*" + node.getKey() + " ");
			}
			else
			{
				traversal.append(node.getKey() + " ");
			}

			// Traverse left first
			preOrderTraversal(node.getLeftChild(), traversal);

			// Traverse right
			preOrderTraversal(node.getRightChild(), traversal);
			
			// returns string
			return traversal;
		}
		
		// returning empty string buffer
		return traversal;

	}
	
	//###########################################################################
	
	// Tree Node class
	
		private class TreeNode
		{
			// Instance variables
			private int key;
			private TreeNode leftChild;
			private TreeNode rightChild;
			private boolean deleted;
			private int height;

			// constructor for every node
			public TreeNode(int key)
			{
				this.key = key;
				this.leftChild = null;
				this.rightChild = null;
				this.deleted = false;
				this.height = 1;
			}

			// functions to get the variables inside
			public int 		getKey() { return key; }
			public TreeNode getLeftChild() { return leftChild; }
			public TreeNode getRightChild() { return rightChild; }
			public int 		getHeight() { return height; }
			public boolean  checkDeleted() { return deleted; }

			// functions to set the variables
			public void setLeftChild(TreeNode leftChild) { this.leftChild = leftChild; }
			public void setRightChild(TreeNode rightChild) { this.rightChild = rightChild; }
			public void setDeleted(boolean deleted) { this.deleted = deleted; }
			public void setHeight(int height) { this.height = height; }

			// to get the string value of the key
			public String toString() { return String.valueOf(this.key); }

		}
	
}

