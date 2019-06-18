package ro.usv.rf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length-1;
			List<double []> patternList = new ArrayList<double[]>();
			
			Double [] classes = new Double[numberOfPatterns];
			for (int formIndex = 0; formIndex < numberOfPatterns; formIndex++)
				{
					double [] xCoordinate = new double[numberOfFeatures];
					for(int featureIndex=0; featureIndex<numberOfFeatures; featureIndex++ )
					{
						xCoordinate[featureIndex]=learningSet[formIndex][featureIndex];
					}
					classes[formIndex]=learningSet[formIndex][numberOfFeatures];
					patternList.add(xCoordinate);
				}
			Map<Double, Integer> map=MathUtils.getDistinctClasses(classes);
			List<double[]>avgList=MathUtils.getAVG(classes, patternList);
			double[][]W= MathUtils.getWeightsMatrix(avgList,map.size());
			double[]pattern1={11,12,1};
			System.out.println();
			System.out.println(MathUtils.getPatternClass(W, pattern1));
	

	
			
			
	
			
			System.out.println(String.format("The learning set has %s patterns and %s features", numberOfPatterns, numberOfFeatures));	
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
