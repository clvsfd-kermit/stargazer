import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteInterface extends Remote {

	public boolean start(int datasetId, int id) throws RemoteException;
	
}

