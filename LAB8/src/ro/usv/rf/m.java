package ro.usv.rf;

public class m {
	public static void main(String []args) throws ex {
		double [][]learningset;
		
			learningset=FileUtils.readLearningSetFromFile("in.txt");
			int numardelinii=learningset.length;
			int nrcol=learningset[1].length;
			int []clases =din.clas(learningset, 2, nrcol);
			for(int i=0;i<learningset.length;i++) {
				System.out.println(clases[i]);
			}
		
		
	}

}
