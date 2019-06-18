package ro.usv.rf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.rmi.CORBA.Util;

public class MainClass {
	
	
	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			String [] classes= new String[numberOfPatterns];
			List<Double> distances =new ArrayList<Double>();

			double nota=5.75;
			int k=14;
			for(int indexPatterns=0; indexPatterns<numberOfPatterns;indexPatterns++)
			{
					distances.add(Math.abs(Double.valueOf(learningSet[indexPatterns][0])-nota));
					classes[indexPatterns]=learningSet[indexPatterns][1];
			}
			Vecin[] arrVecini = new Vecin[numberOfPatterns];
			List<String>clasa=new ArrayList<>();
			for(int i=0; i<arrVecini.length;i++)
			{
				arrVecini[i] = new Vecin();
				arrVecini[i].clasa = classes[i];
				arrVecini[i].distanta = distances.get(i);
			}
			Arrays.sort(arrVecini);
			for(int i=0; i<k;i++)
				clasa.add(arrVecini[i].clasa);	
		 System.out.println("For k = 9 & grade = 5.75, family = " + FileUtils.calculateKNN(clasa));
			
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
