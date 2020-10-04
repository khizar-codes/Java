// Muhammad Khizar
// CS 3345 - Sruthi Chappidi
// Sieve of Eratosthenes prime number project.
// Calculates all the primes that come before a number that user inputs.

import java.util.Scanner;

public class Project1 
{
	
   public static void main(String[] args) 
   {
	   // Asking user for maximum number they want primes till
	   int maxNum;
	   
	   Scanner scan = new Scanner(System.in);
	   System.out.print("Enter the max number: ");
	   
	   // Getting Maximum Number
	   maxNum = scan.nextInt();
	   scan.close();
	   
	   // Creating boolean array
	   boolean[] primeNumberArray = new boolean[maxNum];
	   
	   primeNumberArray[0] = false; 	// 0 in array = 1
	   
	   // Initializing whole array to true 
	   for(int x = 1; x < maxNum; x++)
	   {
		   primeNumberArray[x] = true;
	   }
	   
	   // checking all slots and assigning values 
	   for(int i = 2; i <= maxNum; i++)
	   {
		   // if loop to assign prime or not
		   if(primeNumberArray[i - 1] == true)
		   {
			   // Printing out the prime number
			   System.out.println(i);
			   
			   // Making all multiples of i false
			   for (int j = i*i; j <= maxNum; j += i)
			   {
				   // multiples are not primes so declared false
				   primeNumberArray[j - 1] = false;
			   }
		   }
	   }
	   
   }
   
}