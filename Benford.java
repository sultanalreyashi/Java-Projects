//By: Sultan Alreyashi
//Csc 221, Due:6/18/2015

import java.io.*; 
import java.util.Scanner;

public class Benford {
	
public static double firstsigfig(double x){
		//this is to find the first significant figure; it's broken into four parts
		//the four part might seem unneeded but they're there for security just in case you threw any of the following options

		if(x==0) return x;//if the digit is zero
		
		else if(Math.abs(x)>=10.0){ //if its great then 10; ie 2336.23..would return 2
			
			while(x>=10.0){
			x = x/10.0;			
			}
			
			return Math.abs(x); //absolute value ; ie -236.3->2
		}
		
		else if(Math.abs(x) > 0 && Math.abs(x) < 1){//this is were most of the numbers given to us go threw
			
			while(Math.abs(x) > 0 && Math.abs(x) < 1){ //it multiples it until the double until the first digit is to the left of the decimal 
			x = x*10.0;  //ie .00023 gives us 2.3
			}
			
			return Math.abs(x); //would return 2
		}
		
		else{
			
			return Math.abs(x);//123456789; ie -9.32..gives us 9
			
		}
		
	}
	
public static void stars(double str){
		
		System.out.print(":  ");//print this once
		
		while (str > 0.0){
			
			System.out.printf("*"); //keep printing until str is 0

			str--;
		}
	}
	
public static void main(String[] args){
		
	double[] count = new double[10]; //use an array to keep count of the number of times each figure is returned from firstsigfig
	int Num = 0; //count of the sigfigs; for the percentage
		
	try { //use try catch to read in data.txt and to catch any errors, like the file not being found
		
		File temp = new File("data.txt"); //use File class to get the desired file
		Scanner file = new Scanner(temp);//allow us to read the file
		
		while (file.hasNext()){ //read it line by line
			double x = file.nextDouble();//let x be the number being read, and keeps going to the nextDoubl each time called
		
			if (x!=0){ //this is if your not including the number 0 in your calculation 
						//if you want to include 0 just comment out the if statement
				
				int sigfig= (int) firstsigfig(x); //this calls the fisrtsigfig object and returns the an int of the double (casting) 
				count[sigfig]++; //update the count in the array for the proper index
				Num++; //update the count of numbers for the percentage
		}
		}
		file.close(); 
	}
	catch (FileNotFoundException error){
		error.printStackTrace();
	}//returns an error statement if a problem occurs
	
		for (int i=1; i < 10; i++){//prints out the entries, there percentage and number of stars next to it 
			System.out.printf("%d (%.3f%%)\t", i, ((100 * count[i]) / Num));			
			Benford.stars(Math.round((100 * count[i]) / Num)); //round off accordingly
			System.out.print("\n"); //make a new line
	}
}
}