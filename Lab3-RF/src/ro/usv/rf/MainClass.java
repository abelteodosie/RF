package ro.usv.rf;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			List<Double[]> featureList = new ArrayList<Double[]>();
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patterns and %s features", numberOfPatterns, numberOfFeatures));
			//euclidian
			double []xCoordinate=new double[numberOfPatterns];
			double []yCoordinate=new double[numberOfPatterns];

				for (int formIndex = 0; formIndex < numberOfPatterns; formIndex++)
				{
					xCoordinate[formIndex] = learningSet[formIndex][0];
					yCoordinate[formIndex] = learningSet[formIndex][1];
				}
				double [] distE = DistanceUtils.EuclidianDistances(xCoordinate, yCoordinate);
				//cebisev & city block
				List<Double> paternList;
				List<Double> paternList1;
				double [] distCebisev=new double [numberOfPatterns];
				double [] distCityBlock=new double [numberOfPatterns];
				double [] MahalanobisDistances=new double [numberOfPatterns];
				for (int paternIndex = 1; paternIndex < learningSet.length; paternIndex++)
				{
					paternList = new ArrayList<Double>();
					paternList1 = new ArrayList<Double>();
					for (int featureIndex = 0; featureIndex < learningSet[0].length; featureIndex++)
					{
						paternList.add(Math.abs(learningSet[0][featureIndex]-learningSet[paternIndex][featureIndex]));
						paternList1.add(Math.pow(learningSet[0][featureIndex]-learningSet[paternIndex][featureIndex], learningSet.length));
					}
					distCebisev[paternIndex]=DistanceUtils.CebisevDistances(paternList);
					distCityBlock[paternIndex]=DistanceUtils.CityBlockDistances(paternList);
					MahalanobisDistances[paternIndex]=DistanceUtils.MahalanobisDistances(paternList1,numberOfPatterns);
				}
				System.out.println();
				for(int i=0;i<learningSet.length;i++)
				{
					System.out.println("Euclidian distance: "+distE[i]);
					System.out.println("Cebisev distance: "+distCebisev[i]);
					System.out.println("City Block distance: "+distCityBlock[i]);
					System.out.println("Mahalanobis distances: "+MahalanobisDistances[i]);
					
					System.out.println();
				}
				
				
				
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
