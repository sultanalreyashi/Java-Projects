import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Simulation {
		
	public void generateNormalRandomNumbers(ArrayList<Double> gasnum, int sizeofArray){
		
		Random num = new Random(); //this calls the Random class, to generate the random numbers

		for(int i=0; i< sizeofArray; i++){ //fill in the ArrayList with random numbers of size sizeofArray(declared from main)
		
			double randomnum = num.nextGaussian(); //use the nextGasussian function from Random to get a Gaussian random numebr
			gasnum.add(randomnum); // add this number to the ArrayList
			
		}
	}

	//to get min, max or range you must sort then use certain indexes
	public double getMin(ArrayList<Double> gasnum){ //only parameter needed is the ArrayList
		
		return Collections.min(gasnum); //use Collections to get the min
		
	}
	
	public double getMax(ArrayList<Double> gasnum){ //only parameter needed is the ArrayList
		
		return Collections.max(gasnum); //use Collections to get the max
		
	}
	
	public double getRange(ArrayList<Double> gasnum){ //only parameter needed is the ArrayList
		
		return (getMax(gasnum) - getMin(gasnum)); //use the previous two objects to find the range 
		
	}
	
	//the bulk and focus of this class are the next two	
	public int[] makeBin(ArrayList<Double> gasnum, int numberofBins){
		
		int[] count = new int[numberofBins]; //create the array that'll be the return
		double binlength = getRange(gasnum)/numberofBins; //this gets the size of each bin; ie if the range is 10 and number of bins is 10 then binlength is 1
		int index = 0; //this will be for the start of the ArrayList
		
		while(index < gasnum.size()){ //so long as the index doesn't exceed the size of the Arraylist
			
			int bintoIncrement = getBin(gasnum.get(index), getMin(gasnum), getMax(gasnum), binlength, numberofBins); //calls getBin to get the subscript 
			count[bintoIncrement]++; //increases the count of the bin# wanted
			index++; //move on to the next index
		
		}
			return count; //return the array with the numbers all counted for
	}
	
	//interesting note here is that I used something I learned from one of my math classes
	public int getBin(double randomNumber, double min, double max, double binSize, int numberofBins2){//i added a parameter, this is used in the else if(will explain more)
				
		int dummyVariable= 1; // this will be the number used to multiple the number "binlength's" you want to be from the min
		int Subscript = 0;	//the return at the end	
		
		while((min+(binSize*dummyVariable) <= max)){ //run so long as the right hand bound doesn't exceed the max
			
			//Generating formula, in this cases it switches the right hand bound to the left hand every next test
			if((randomNumber >= (min+(binSize*(dummyVariable-1)))) && (randomNumber < (min+(binSize*dummyVariable)))){
				//this takes care of the case [##,##); the else if takes car of when is can be [##,##]; that is when the random number is can be thee max
				
				Subscript = (dummyVariable-1); //make Subscript equal the previous bound; that is the interval the number belongs too  
				break; //leave the loop once it did what it has too
			}
			
			else if(randomNumber == max){ //the only case left is when the number is the max
				Subscript = (numberofBins2-1); //this was the needed for adding this parameter; return the last possible subscript
				break;//leave the loop once it did what it has too
			}
			dummyVariable++; //shift the bounds 
				
		}
		return Subscript; //return the subcript the number belongs too
	}
}
