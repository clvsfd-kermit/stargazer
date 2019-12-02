import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RemoteInterface extends Remote {

	public CentroidsResults getCentroidsResults() throws RemoteException;
	public void sendRequest(ArrayList<String> centroids) throws RemoteException;
	public boolean getDone() throws RemoteException;
}
