import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lipermi.handler.CallHandler;
import lipermi.net.Server;


public class Main {
	
	public static void main(String[] args) {
		
		int clientId = Integer.parseInt(args[0]);//from 0 - 8
		//int datasetId = Integer.parseInt(args[1]);//from 0 -- 18
		Constants.clientId = clientId;
		CallHandler callHandler = new CallHandler();
		
		Utils utils = new Utils();
		String clientIp = utils.getIp(clientId);
		
		try {
			int portNumber = 1920 + Constants.clientId;
			RemoteInterface remote;
			remote = RemoteImplementation.getInstance();
			System.out.println("//" + clientIp + ":" + portNumber + "/federated-learning-client-driver-" + Constants.clientId);
			callHandler.registerGlobal(RemoteInterface.class, remote);
			
			Server server = new Server();
			server.bind(portNumber, callHandler);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
