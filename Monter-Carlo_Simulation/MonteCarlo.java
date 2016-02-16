//Sultan Alreyashi
//assignment 2 part 1; 7/7/2015
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class MonteCarlo{
	
	public static void main(String[] args) {
	
		ArrayList<Double> gasrannum = new ArrayList<Double>(); //create an ArrayList 
		Simulation s = new Simulation(); //create a variable of class Simulation; easier to access
				
		int countnum = 10000; //i got it to work for 100000 but it might take a few seconds for it to appear
		s.generateNormalRandomNumbers(gasrannum, countnum); //make the ArrayList of Gaussian Numbers
				
		int numberofBinsWanted = 11; //the number of bins wanted		
		int[] countArray = s.makeBin(gasrannum, numberofBinsWanted); // this is to make printing the bins cleaner; call makeBin (which will call getBin)
		
		System.out.println("Size="+gasrannum.size() +"    "+"Min="+s.getMin(gasrannum) +"    "+"Max="+s.getMax(gasrannum)+"\n"); //this prints the size, min and max
																																// just to make sure the size is correct
		try{	
			PrintStream out= new PrintStream(new FileOutputStream("Gauss.txt")); //this is the results onto Gauss.txt
			for(int element: countArray){ //enhanced for loop to make gong through the array easy			
				out.println(element); //print onto Data.txt
				System.out.println(element); //print onto the console
			}
		out.close(); //close the out once the printing is done
		}
		catch (FileNotFoundException e){ //catch any mistakes or exceptions
			e.printStackTrace();
		}
		
		System.out.printf("\n%d%s\n",Metrics.verifyDistribution(gasrannum, 0.0, 1.0, 1.0),"%"); //prints with mean 0, standard deviation 1, number of standard deviations from the mean 1 
		System.out.printf("%d%s\n",Metrics.verifyDistribution(gasrannum, 0.0, 1.0, 2.0),"%"); //prints with mean 0, standard deviation 1, number of standard deviations from the mean 2
		System.out.printf("%d%s",Metrics.verifyDistribution(gasrannum, 0.0, 1.0, 3.0),"%"); //prints with mean 0, standard deviation 1, number of standard deviations from the mean 3
	}
}