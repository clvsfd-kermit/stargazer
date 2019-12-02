import java.rmi.Naming;
import java.util.ArrayList;


public class RemoteUtils {

	public RemoteUtils(){}
	
	public void sendCentroidsToServer(ArrayList<String> centroidResults){ 
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			
			RemoteInterface access = (RemoteInterface)Naming.lookup("rmi://localhost:1900" + "/federated-learning-server");
			access.sendRequest(centroidResults);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
