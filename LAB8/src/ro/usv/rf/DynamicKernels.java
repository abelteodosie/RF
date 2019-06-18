package ro.usv.rf;

public class DynamicKernels {

	private static double getDistance(double[]pattern, double[]kernel)
	{
		double sum=0.0;
		for(int i=0;i<pattern.length; i++)
		{
			sum+=Math.pow((pattern[i]-kernel[i]),2);
		}
		return Math.sqrt(sum);
	}
	private static double[]initializeKernel(double [] initialValues)
	{
		double[]kernel= new double[initialValues.length];
		for(int i=0;i<initialValues.length; i++)
		{
			kernel[i]=initialValues[i];
		}
		return kernel;
	}
	
	private static double[] updateKernel(double[][]learningSet,double[]centerOfGravity)
	{
		double minDistance=Double.MAX_VALUE;
		int closestPattern=0;
		for(int i=0;i<learningSet.length;i++)
		{
			double distanceToKernel=getDistance(learningSet[i],centerOfGravity);
			if(distanceToKernel<minDistance)
			{
				minDistance=distanceToKernel;
				closestPattern=i;
			}
			
		}
		return learningSet[closestPattern];
	}	
	
	protected static int[] classify(double[][]learningSet, int nrOfClasses, int nrOfFeatures)
	{
		
		double [][]kernels =new double [nrOfClasses][nrOfFeatures];
		int [] iClass=new int[learningSet.length];
		boolean done=false;
		for(int i=0;i<nrOfClasses;i++)
		{ 
			
			kernels[i]=initializeKernel(learningSet[i]);
		}
		while(!done)
		{
			done=true;
			double[][]centersOfGravity=new double[nrOfClasses][nrOfFeatures];
			int[]patternsCount = new int[nrOfClasses];
			int kmin=0;
			for(int i=0;i<learningSet.length;i++)
			{
				double minDistance=Double.MAX_VALUE;
				for(int k=0;k<nrOfClasses;k++)
				{
					double distanceToKernel=getDistance(learningSet[i],kernels[k]);
					if(distanceToKernel<minDistance)
					{
						minDistance=distanceToKernel;
						kmin=k;
					}
				}
				patternsCount[kmin]++;
				for(int j=0;j<nrOfFeatures;j++)
				{
					centersOfGravity[kmin][j]+=learningSet[i][j];
				}
				if(iClass[i]!=kmin) 
				{
					iClass[i]=kmin;
					done=false;
				}
			}
			if(!done)
			{
				for(int k=0;k<nrOfClasses;k++)
				{
					for(int j=0;j<nrOfFeatures;j++)
					{
						centersOfGravity[k][j]/=patternsCount[k];
					}
					kernels[k]=updateKernel(learningSet,centersOfGravity[k]);
				}
			}
		}
		return iClass;
	}
}
