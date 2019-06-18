package ro.usv.rf;



public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			
			int [] classes = din.clas(learningSet, 2, numberOfFeatures);
			//int [] classes = DynamicKernels.classify(learningSet, 2, numberOfFeatures);

			//System.out.println();
			for (int i = 0; i < learningSet.length; i++)
			{
				System.out.println(classes[i]);
			}
			
			System.out.println(String.format("The learning set has %s patterns and %s features", numberOfPatterns, numberOfFeatures));	
		} catch (ex e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
