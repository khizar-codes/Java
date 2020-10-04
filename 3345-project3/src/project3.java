import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// main file

public class project3
{
	// All strings to check for commands in file lines
	public static String INSERT = "Insert:", 
						 DELETE = "Delete:", 
						 PRINT_TREE = "PrintTree", 
						 FIND_MIN = "FindMin", 
						 FIND_MAX = "FindMax", 
						 HEIGHT = "Height", 
						 SIZE = "Size",
						 CONTAINS = "Contains:";

	// main, program starts here
	public static void main(String[] args)
	{

		// Checking provided argument length
		if (args.length == 2)
		{

			// reading file
			Scanner inputFile = null;

			// creating file writer
			PrintWriter printToFile = null;

			// creating output file
			File outputFile = new File(args[1]);

			// try catch
			try
			{
				// reading file
				inputFile = new Scanner(new File(args[0]));

				// Creating file if there is no file
				if (!outputFile.exists())
				{
					outputFile.createNewFile();
				}

				// Opening write file and creating bst object
				printToFile = new PrintWriter(outputFile);
				LazyBinarySearchTree lbstTree = new LazyBinarySearchTree();

				// iterating through every line of file
				while (inputFile.hasNextLine())
				{
					// making line string the line of the file
					String line = inputFile.nextLine().trim();

					// Checking if its insert
					if (line.indexOf(INSERT) == 0)
					{

						// parsing the key
						int key = Integer.parseInt(line.substring(line.indexOf(INSERT) + INSERT.length()));
						
						// trying to call the function and if it does not work then printing error
						try
						{
							printToFile.println(String.valueOf(lbstTree.insert(key)));
						} 
						catch (IllegalArgumentException iae)
						{
							printToFile.println(iae.getMessage());
						}

					} 
					
					// Checking if its delete
					else if (line.indexOf(DELETE) == 0)
					{
						
						// parsing key
						int key = Integer.parseInt(line.substring(line.indexOf(DELETE) + DELETE.length()));
						
						// calling delete function in class and passing key
						try
						{
							printToFile.println(String.valueOf(lbstTree.delete(key)));
						} 
						// printing error message if there is one
						catch (IllegalArgumentException iae)
						{
							printToFile.println(iae.getMessage());
						}

					} 
					
					// checking its asking for contains
					else if (line.indexOf(CONTAINS) == 0)
					{

						// getting ket
						int key = Integer.parseInt(line.substring(line.indexOf(CONTAINS) + CONTAINS.length()));
						
						try
						{
							// calling function and printing return of true or false boolean
							printToFile.println(String.valueOf(lbstTree.contains(key)));
						} 
						catch (IllegalArgumentException iae)
						{
							// printing error
							printToFile.println(iae.getMessage());
						}

					} 
					
					// checking if its print lbstTree
					else if (line.indexOf(PRINT_TREE) == 0)
					{
						// calling functiont to print tree
						printToFile.println(lbstTree.toString());
					} 
					
					// checking if its height
					else if (line.indexOf(HEIGHT) == 0)
					{
						// calling function to print height
						printToFile.println(lbstTree.height());
					} 
					
					// checking if its size
					else if (line.indexOf(SIZE) == 0)
					{
						//calling function to print size
						printToFile.println(lbstTree.size());
					}
					
					// checking if its find min and printing minimum
					else if (line.indexOf(FIND_MIN) == 0)
					{
						printToFile.println(lbstTree.findMin());
					} 
					
					// checking if its find max
					else if (line.indexOf(FIND_MAX) == 0)
					{
						// calling function to print max
						printToFile.println(lbstTree.findMax());
					}
					
					// if none then there is an error in the line, wrong command, so print error in line...
					else
					{
						printToFile.println("Error in line:	" + line);
					}

				}
				
				// printing end message
				System.out.println("Completed successfully, please check outputfile: " + args[1]);
				
				// closing files
				inputFile.close();
				printToFile.close();

			}
			// print error
			catch (FileNotFoundException fnfe)
			{
				System.out.println(fnfe.getMessage());
			} 
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}

		} 
		
		// if args dont match then print error msg and exit
		else
		{
			System.out.println("Invalid Arguments");
		}

	}
}