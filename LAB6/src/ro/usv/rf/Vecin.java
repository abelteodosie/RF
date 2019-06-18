package ro.usv.rf;

public class Vecin implements Comparable<Vecin>{
	double distanta; 
	String clasa;
		
	@Override
	public int compareTo(Vecin vecin) {
		// TODO Auto-generated method stub
		return Double.compare(this.distanta, vecin.distanta);
	}	
}
