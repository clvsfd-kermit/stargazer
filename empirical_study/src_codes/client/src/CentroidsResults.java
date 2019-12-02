import java.util.ArrayList;


public class CentroidsResults {

	private static CentroidsResults singleton = null;
	private CentroidsResults () {}
	private ArrayList<String> centroids = new ArrayList<String>();

	public static CentroidsResults getInstance(){
		
		if(singleton == null) {
			synchronized(CentroidsResults.class) {
				try {
					singleton = new CentroidsResults();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return singleton;
	}
	
	public ArrayList<String> getCentroids(){
		
		return this.centroids;
	}
	
	public void addElementsToCentroid(ArrayList<String> inputArray){
		
		for (int i = 0; i < inputArray.size(); i++) {
			this.centroids.add(inputArray.get(i));
		}
	}
	
}
