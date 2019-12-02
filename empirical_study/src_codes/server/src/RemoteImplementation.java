import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {
	
	private static RemoteImplementation singleton = null;
	private RemoteImplementation () throws RemoteException {}
	private CentroidsResults centroidsResults = CentroidsResults.getInstance();
	private int numberOfClients = 0;
	private boolean done = false;
	
	public int getNumberOfClients(){
		return this.numberOfClients;
	}
	
	public synchronized boolean getDone() {
		return this.done;
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
		
		this.centroidsResults.addElementsToCentroid(centroids);
		Main.numberOfclients++;
		if(Main.numberOfclients == Constants.numberOfDevices){
			this.done = true;
			Main.done = true;
		}
	}
	
	public CentroidsResults getCentroidsResults() {
		
		return this.centroidsResults;
		
	}
	
}
