package ro.usv.rf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistanceUtils {

	protected static double [] EuclidianDistances(double [] feature1, double []feature2)
	{
		double [] distance= new double[feature1.length];
		for(int featureIndex=0; featureIndex<feature1.length-1;featureIndex++)
		{
			distance[featureIndex]=Math.sqrt(Math.pow(feature1[featureIndex]-feature1[featureIndex+1], 2)+Math.pow(feature2[featureIndex]-feature2[featureIndex+1], 2));
		}
		return distance;
	}
	
	protected static double  MahalanobisDistances(List <Double> paternList, int numberOfPatterns)
	{
		double MahalanobisDistances=0.0;
		double sum=0.0;
		for(int indexList=0;indexList<paternList.size();indexList++)
			sum+=paternList.get(indexList);
		MahalanobisDistances=Math.pow(sum, 1.0/numberOfPatterns);
		return MahalanobisDistances;
	}
	protected static double  CebisevDistances(List <Double> paternList)
	{
		double distanceCb=0.0;
		distanceCb=Collections.max(paternList);
		return distanceCb;
	}

	protected static double CityBlockDistances(List <Double> paternList)
	{
		double distanceCBl=0.0;
		for(int indexList=0;indexList<paternList.size();indexList++)
			distanceCBl+=paternList.get(indexList);
		return distanceCBl;
	}
	
}
