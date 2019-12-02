import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {

	private RemoteImplementation () throws RemoteException {}
	private static RemoteImplementation singleton = null;
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
	
	
	public boolean start(int datasetId, int id) throws RemoteException {
		
		try {
		
			Constants.datasetId = datasetId;
			
			String cmd = Constants.homePath + "/driver-" + Constants.clientId + "-" + Constants.datasetId + ".sh";
			String[] cmd2 = {Constants.homePath + "/metric" + ".sh", Constants.clientId + "_" + id + "_" + Constants.datasetId + "_DBSC"};
			System.out.println("Running --> sar -u -r -b -n DEV 1 > $1.txt");
			
			System.out.println("testing " + cmd);
			Process p = new ProcessBuilder(cmd).start();
			System.out.println("1");
			System.out.println("metrics " + cmd2);
			Process p1 = new ProcessBuilder(cmd2).start();
			System.out.println("2");
		    p.waitFor();
		    p1.destroy();
		    
		    System.out.println("Script executed successfully");	    
		   
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}
		
		return true;
	}

}
