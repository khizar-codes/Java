import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class bst
{
    Node root;

    private class Node
    {
    	
    	// These attributes of the Node class will not be sufficient for those attempting the AVL extra credit.
    	// You are free to add extra attributes as you see fit, but do not remove attributes given as it will mess with help code.
    	
        String keyword;
        Record record;
        int size;
        Node l; 	// left node
        Node r; 	// right node

        // constructor set keyword with string passed in
        private Node(String k)
        {
        	// TODO Instantiate a new Node with keyword k.
        	keyword = k;
        	
        }

        // update function, updates record
        
        private void update(Record r)
        {
        	//Adds the Record r to the linked list of records. You do not have to check if the record is already in the list.
        	//HINT: Add the Record r to the front of your linked list.

        	// if record is empty then record = r;
        	
        	if(record == null)
        	{
        		record = r;
        	}
        	else
        	{
        		// if record is there then it is put in the front
        		r.next = record;
        		record = r;
        	}
        	
        }

       
    }

    // bst constructor
    
    public bst()
    {
    	// root set to null when bst created
        this.root = null;
    }
      
    // insert function for bst
    public void insert(String keyword, FileData fd)
    {
        //recursive insertion that adds recordToAdd to the list of records for the node associated
        //with keyword. If there is no node, this code should add the node.
        
    	Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        keyword = keyword.trim();
        // calling the other insert function.
		insert(keyword, recordToAdd, root);
        
    }
    
    public boolean contains(String keyword)
    {
    	//function which returns true if a particular keyword exists in the bst
    	return contains(keyword, root);
    }

    public Record get_records(String keyword)
    {
        //TODO Returns the first record for a particular keyword. This record will link to other records
    	//If the keyword is not in the bst, it should return null.
    	
    	// current node becomes root
    	Node current = root;
    	
		while(current != null)
		{
			// if less then it goes on the left of the bst
			if(keyword.compareTo(current.keyword) < 0)
			{
				current = current.l;
			}
			// if more then it goes on the right of the bst.
			else if (keyword.compareTo(current.keyword) > 0)
			{
				current = current.r;
			} 
			else 
			{
				// returns the record
				return current.record;
			}
		}
		
		// if none then returns null
		return null;
    	
     }

    public void delete(String keyword)
    {
    	//recursive function which removes the Node with keyword from the binary search tree.
    	//You may not use lazy deletion and if the keyword is not in the bst, the function should do nothing.
    	delete(keyword, root);
    }
    
    // prints the root
    public void print()
    {
        print(root);
    }

    // takes in node t prints r
    private void print(Node t)
    {
        if (t != null)
        {
            print(t.l);
            System.out.println(t.keyword);
            
            Record r = t.record;
            
            while(r != null)
            {
                System.out.printf("\t%s\n",r.title);
                r = r.next;
            }
            
            print(t.r);
        } 
    }

    private boolean contains(String keyword, Node root)
    {
		// checking if node contains the key word
		if(root == null) 
		{
			return false;
		}
		if(keyword.compareTo(root.keyword) < 0)
		{
			return contains(keyword, root.l);
		} 
		else if (keyword.compareTo(root.keyword) > 0)
		{
			return contains(keyword, root.r);
		} 
		else 
		{
			return true;
		}
	}

    // insert recursive function
    private void insert(String keyword, Record recordToAdd, Node root)
    {
    	// if null then root = node
    	
		if (root == null)
		{
			Node node = new Node(keyword);
			node.update(recordToAdd);
			this.root = node;
		} 
		
		//check left
		else if (keyword.compareTo(root.keyword) < 0) 
		{
			if (root.l != null) 
			{
				insert(keyword, recordToAdd, root.l);
			} 
			else 
			{
				Node node = new Node(keyword);
				node.update(recordToAdd);
				root.l = node;
			}
		} 
		// check right
		else if (keyword.compareTo(root.keyword) > 0) 
		{
			if (root.r != null) 
			{
				insert(keyword, recordToAdd, root.r);
			} 
			else 
			{
				Node node = new Node(keyword);
				node.update(recordToAdd);
				root.r = node;
			}
		}
		else 
		{
			// update 
			root.update(recordToAdd);
		}
	}
    
    // recursive for delete
    private Node delete(String keyword, Node current)
    {
    	// if null then does nothing because its not there
		if (current == null)
		{} 
		// check left
		else if(keyword.compareTo(current.keyword) < 0)
		{
			current.l = delete(keyword, current.l);
		}
		// check right
		else if(keyword.compareTo(current.keyword) > 0) 
		{
			current.r = delete(keyword, current.r);
		}
		else 
		{
			// finds the lowest 
			if(current.r == null)
			{
				current = current.l;
			} 
			else
			{
				// replaces with lowest
				Node replacement = lowest(current.r);
				current.keyword = replacement.keyword;
				current.record = replacement.record;
				current.size = replacement.size;
				current.r = delete(replacement.keyword, current.r);
			}
		}
		return current;
	}
    
    // function returns lowest node
    private Node lowest(Node root)
    {
		if(root == null)
		{
			return null;
		}
		if(root.l == null)
		{
			return root;
		}
		
		return lowest(root.l);
	}
    
    
}

// keyword class that hold the keyword and its article

class Keyword
{
	String keyword = "";
	Record recordsInThis = new Record();
	public Record head;
	
	
	//constructor
	
	public Keyword()
	{
		
	}
	
	// insert record function
	
	public void insertRecord(int i, String t, String a, Record r)
	{
		Record newRecord = new Record(i, t, a, r);
		
		newRecord.next = head;
		
		head = newRecord;
	}
	
	// display Record
	
	public void display()
	{
		Record theRecord = head;
		
		while(theRecord != null)
		{
			theRecord.print();
			
			System.out.println("Next Record: " + theRecord.next);
		
			theRecord = theRecord.next;
			
			System.out.println();
		}
	}
}

class Index
{
	// required variables
	
	int MAX_KEYWORD_COUNT = 500;
	int currentCount = 0;
	int totalData = 0;
	int keywordsInRecord = 0;
	
	public Index(String filename, String keywordToFind)
	{
		// try catch opening file
		
		   try 
	       {
			  			 
	         File file = new File(filename);
	         Scanner scanner = new Scanner(file);
	         	       
	         // checking lines in file
	         
	         while(scanner.hasNextLine()) 
	         {
	        	 scanner.nextLine();
	        	 totalData++;
	         }
	         
	         // taking everything in array
	         
	         int i = 0;
	         String[] array1 = new String[totalData];
	        	         
	         String line = "";
	                  
	         Scanner scanner2 = new Scanner(file);
	         
	         // making the blank lines go away
	         
	         while (scanner2.hasNextLine()) 
	         {  
	        	        	 
	        	 line = scanner2.nextLine();
	        	 
	          	 if(!line.isEmpty())
	        	 {
	        		 array1[i]=line;
		        	 i++;
	        	 }
	        	 
	         }
	         
	         scanner2.close();
	         
	         // finding all the keyword numbers
	         
	         int[] array2 = new int[31];
	         int totalKeywords = 0;
	         int u = 0;
	         
	         // getting all data and checking total keywords
	         
	         for(int j = 0; j < totalData; j++)
	         {
	        	 if(isInteger(array1[j]) && Integer.parseInt(array1[j]) < 100)
	        	 {
        			 array2[u] = Integer.parseInt(array1[j]);
	        		 totalKeywords += Integer.parseInt(array1[j]);	 
	        		 u++;
	        	 }
	         }
	         
	         // Array to hold all the keywords
	         
	         String[] keywords = new String[totalKeywords];
	         
	         int x = 0, y = 0, increase = 0;
	         
	         // getting keywords form the file
	         
	         for (int j = 0; j < array1.length; j++)
	         {
	        	 if(y != array2.length)
	        	 {
	        	 
			        	 j += 3;
			        	 
			        	 increase = j + array2[y];
			        	 
			        	 
			        	 if(j < increase)
			        	 {
			        		 while (j < increase)
			        		 {
			        			 j++;
			        			 keywords[x] = array1[j];
			    	        	 x++;
			    	        	
			        		 }
			        		 
			        	 y++;
			        	 
			        	 }
	        	 
	        	 }
	        
	         }
	         
	         // removing repeating keywords
	         // sorting key words alphabetically
	         
	         keywords = new HashSet<String>(Arrays.asList(keywords)).toArray(new String[0]);         
	         Arrays.sort(keywords);
		       
	         // getting keyword array length    	       
	         
	         currentCount = keywords.length;
	         
	         Keyword[] storage = new Keyword[currentCount];
	         
	         String dup = "";
	         
	         // making keyword class object and giving it keywords
	         
	         for (i = 0; i < currentCount; i++)
	         {
	        	 dup = keywords[i];
	        	 storage[i] = new Keyword();
	        	 storage[i].keyword = dup;
	         }
	         
	         // first go through the array
	         // store the record
	         // check the keywords
	         // store record into keywords in the array
	         // move on to the next one
	         
	         int id, index;
	         String title;
	         String author, duplicate;
	         i = 0;
	         y = 0;
	         
	         // getting the articles and assigning them according to their keyword
	         
	         while (i < 216)
	         {
	        	 id = Integer.parseInt(array1[i]); i++;
		         title = array1[i];				   i++;
		         author = array1[i];               i++;
		         i++;
		         
		         // for loop to assign to keywords
		         
		         for (x = 0; x < array2[y]; x++, i++)
		         {
		        	 duplicate = array1[i];
		        	 index = getKeywordIndex(duplicate, keywords);
		        	 
		        	 storage[index].recordsInThis.id = id;
		        	 storage[index].recordsInThis.title = title;
		        	 storage[index].recordsInThis.author = author;
		         }
		         
		         y++;
	         }
		      
	         
	         // finding the keyword asked
	         
		      int findIndex = getKeywordIndex(keywordToFind, keywords);
		      
		      // using that to print the articles for that keyword
		      
		      if(findIndex == -1)
		      {
		    	  System.out.println("No articles found");
		      }
		      else
		      {
		    	  // printing
		    	  
		    	    System.out.println("****************************************************");
		    	  	System.out.println("Keyword: " + storage[findIndex].keyword);
		    	  	System.out.println("****************************************************");
			      	System.out.println("Article id: " + storage[findIndex].recordsInThis.id);
			      	System.out.println("Article title: " + storage[findIndex].recordsInThis.title);
			      	System.out.println("Article author: " + storage[findIndex].recordsInThis.author);
			      	System.out.println("****************************************************");
			      	System.out.println();
		      }
		      
		      // closing scanner
	      
	         scanner.close();
	       } 
		   // catch
	       catch (FileNotFoundException e) 
	       {
	         e.printStackTrace();
	       }
	       
	}
	
	// to get the index of the keyword given
	
	public int getKeywordIndex(String keyword, String[] array)
	{
		int i = 0;
		int index = -1;
		
		for (i = 0; i < array.length; i++)
		{
			// checking if available
			
			if(keyword.equals(array[i]))
			{
				index = i;
			}
			else
			{
				
			}
				
		}
		
		// returning index
		
		return index;
	}
	
	// checking if string has int
		 
	 public boolean isInteger(String str) {
		    if (str == null) {
		        return false;
		    }
		    int length = str.length();
		    if (length == 0) {
		        return false;
		    }
		    int i = 0;
		    if (str.charAt(0) == '-') {
		        if (length == 1) {
		            return false;
		        }
		        i = 1;
		    }
		    for (; i < length; i++) {
		        char c = str.charAt(i);
		        if (c < '0' || c > '9') {
		            return false;
		        }
		    }
		    return true;
		}
	
}

