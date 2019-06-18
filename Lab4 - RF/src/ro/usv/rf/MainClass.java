package ro.usv.rf;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length-1;
			System.out.println(String.format("The learning set has %s patterns and %s features", numberOfPatterns, numberOfFeatures));
			List<double []> patternList = new ArrayList<double[]>();
			double [] classes = new double[numberOfPatterns];
			for (int formIndex = 0; formIndex < numberOfPatterns; formIndex++)
				{
					double [] xCoordinate = new double[numberOfFeatures];
					for(int featureIndex=0; featureIndex<numberOfFeatures; featureIndex++ )
					{
						xCoordinate[featureIndex]=learningSet[formIndex][featureIndex];
						
					}
					classes[formIndex]=learningSet[formIndex][numberOfFeatures];
					//System.out.print(classes[formIndex]+" ");
					patternList.add(xCoordinate);
				}
			//Matricea distantelor
			double [][]mat=new double[numberOfPatterns][numberOfPatterns];
			for (int patternIndex = 0; patternIndex <mat.length ; patternIndex++)
			{
				for(int featureIndex=0; featureIndex<patternIndex; featureIndex++ )
				{
					mat[patternIndex][featureIndex]=mat[featureIndex][patternIndex]=DistanceUtils.EuclidianDistances(patternList.get(patternIndex), patternList.get(featureIndex));
				}
			}
			System.out.println();
		/*for(int i=0;i<mat.length;i++)
			{
				System.out.println();
				for(int k=0;k<mat[0].length;k++)
					System.out.print(mat[i][k]+" ");
			}  */
			//1NN
			int index=0;
			for(int indexClass=0;indexClass<classes.length;indexClass++)
			{
				if(classes[indexClass]==3.00)
					{
						System.out.println();	
						index=DistanceUtils.NN(patternList.get(indexClass));
						learningSet[indexClass][numberOfFeatures]=learningSet[index][numberOfFeatures];
						System.out.println(learningSet[indexClass][numberOfFeatures]);
					}
			}
			
			
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
