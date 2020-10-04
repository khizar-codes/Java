import java.io.*;
import java.util.*;

// main function for the program, starts here
public class project4
{
	public static void main(String[] args) throws FileNotFoundException
	{
		// if at least 2 arguments arent provided then end program with error
		if (args.length < 2)
		{
			System.out.println("Insufficient Arguments.");
		}
		// else keep going
		else
		{
			// getting file info
			Scanner inputFile = new Scanner(new File(args[0]));
			PrintWriter outputFile = new PrintWriter(new File(args[1]));

			// string to get the command line in the file
			String command = "";

			// getting next line until the end
			if (inputFile.hasNextLine())
				command = inputFile.nextLine();

			// checking if the line says integer
			if (command.equals("Integer"))
			{
				// creating object
				RedBlackTree<Integer> redBlackTreeO = new RedBlackTree<Integer>();

				// going through file
				while (inputFile.hasNextLine())
				{
					String line = inputFile.nextLine();

					if (line.equals("PrintTree"))
					{
						outputFile.println(redBlackTreeO.toString());
					}
					else if (line.contains(":"))
					{
						int index = line.indexOf(':');
						
						String action = line.substring(0, index);
						
						String mid = line.substring(index + 1);
						
						Integer value = Integer.parseInt(mid);

						if (action.equals("Insert"))
						{
							if (redBlackTreeO.insert(value))
							{
								outputFile.println("True");
							}
							else
							{
								outputFile.println("False");
							}
						}
						else if (action.equals("Contains"))
						{
							if (redBlackTreeO.contains(value))
							{
								outputFile.println("True");
							}
							else
							{
								outputFile.println("False");
							}
						}
						else
						{
							outputFile.println("Error in Line: " + line);
						}
					}
					else
					{
						outputFile.println("Error in Line: " + line);
					}
				}
			}
			// checking if string is the command
			else if (command.equals("String"))
			{
				// creating object
				RedBlackTree<String> redBlackTreeO = new RedBlackTree<String>();

				// going throug hfile
				while (inputFile.hasNextLine())
				{
					String line = inputFile.nextLine();

					if (line.equals("PrintTree"))
					{
						outputFile.println(redBlackTreeO.toString());
					}
					else if (line.contains(":"))
					{
						int index = line.indexOf(':');
						
						String action = line.substring(0, index);
						
						String mid = line.substring(index + 1);

						if (action.equals("Insert"))
						{
							
							if (redBlackTreeO.insert(mid))
							{
								outputFile.println("True");
							}
							else
							{
								outputFile.println("False");
							}
							
						}
						else if (action.equals("Contains"))
						{
							if (redBlackTreeO.contains(mid))
							{
								outputFile.println("True");
							}
							else
							{
								outputFile.println("False");
							}
						}
						else
						{
							// printing error msg
							outputFile.println("Error in Line: " + line);
						}
					}
					else
					{
						// print error
						outputFile.println("Error in Line: " + line);
					}
				}
			}
			else
			{
				// if commands doesnt match
				outputFile.println("Only works for objects Integers and Strings");
			}

			// closing both files
			inputFile.close();
			outputFile.close();
		}

	}
}