import java.util.ArrayList;


public class Metrics {
	//I seen that this only had to be static
	public static int verifyDistribution(ArrayList<Double> gasnum, double mean, double stdDeviation, double numberofStdDeviation){
		
		int index = 0;//this will be used to go to the next index of the ArrayList
		int count = 0; //to count the number of randomnumbers that deviation away from the mean; also for calculating percentage
		
		while(index < gasnum.size()){ //keep running so long as we don't go beyond the ArrayList size
			
			//this is pretty much the same logic as in the assignment sheet; check if the numbers fall between (0.0-(1.0*1.0)) and (0.0+(1.0*1.0))
			if((gasnum.get(index) < (mean + (stdDeviation*numberofStdDeviation))) && (gasnum.get(index) > (mean - (stdDeviation*numberofStdDeviation)))){
				count++; //if show increase count by one
			}
			index++;//go to the next index of the ArrayList
		}
		
		return ((count*100)/gasnum.size()); //calculate the percentage within the standard deviation
	}
}
