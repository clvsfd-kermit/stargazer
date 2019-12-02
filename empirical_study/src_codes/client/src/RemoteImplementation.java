import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {

	private CentroidsResults centroidsResults = CentroidsResults.getInstance();
	private static RemoteImplementation singleton = null;
	private RemoteImplementation () throws RemoteException {}
	private boolean done;
	
	public CentroidsResults getCentroidsResults() throws RemoteException {
		
		return this.centroidsResults;
		
	}
	
	public static RemoteImplementation getInstance(){
		
		if(singleton == null) {
			synchronized(RemoteImplementation.class) {
				try {
					singleton = new RemoteImplementation();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return singleton;
	}
	
	public void sendRequest(ArrayList<String> centroids) throws RemoteException {
		System.out.println("this is the size of the array from the server: " + centroids.size());
		//this.centroidsResults.addElementsToCentroid(centroids);
		Main.cr.addElementsToCentroid(centroids);
		this.done = true;
		
	}
	
	public boolean getDone() {
		return this.done;
	}
	
}
