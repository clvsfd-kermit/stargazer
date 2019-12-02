import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import lipermi.handler.CallHandler;
import lipermi.net.Client;
import lipermi.net.Server;


public class Main {

	public static boolean done;
	public static int numberOfclients = 0;
	
	
	public static void main(String[] args) {
		
		done = false;
		System.out.println("Starting FL server....");
		
		try {
			
				
			ArrayList<ServerConnection> scs = new ArrayList<>();
			
			for(int i = 1; i < Constants.numberOfDevices + 1; i++){
				
				ServerConnection sc = new ServerConnection(i);
				sc.start();
				scs.add(sc);
				
			}
			
			for(int i = 0; i < scs.size(); i++){
				scs.get(i).join();
			}
			
			System.out.println("creating threads to handle reply");
			ClientCommunicator cc = new ClientCommunicator();
			cc.start();
			cc.join();
			
		} catch (Exception e) {
		
			e.printStackTrace();
		
		} finally {
			
		    System.out.println("terminating or closing java program");
		    System.exit(1);
			
		}
	}
}

class ServerConnection extends Thread {
	
	private int clientId;
	
	ServerConnection(int clientId) {
		
		this.clientId = clientId;
		
	}
	
	public void run(){
		
		try {
			
			CallHandler callHandler = new CallHandler();
			System.out.println ("Thread " + Thread.currentThread().getId() + " is running"); 
			RemoteInterface remote;
			remote = RemoteImplementation.getInstance();
			
			System.out.println("//" + Constants.serverIp);
			int port = 1900 + this.clientId;
			callHandler.registerGlobal(RemoteInterface.class, remote);
			Server server = new Server();
			server.bind(port, callHandler);
			System.out.println("Creating threads to handle incoming information");
			
			while(!Main.done){
				System.out.println("waiting...");
				sleep(1000);
			}
			
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class ClientCommunicator extends Thread {
	
	public void run(){
		
		try {
			
			
			int numberOfClients = Constants.numberOfDevices;
			System.out.println ("Thread " + Thread.currentThread().getId() + " is running"); 
			//RemoteInterface remote = RemoteImplementation.getInstance();
			
			
			while(!(Main.numberOfclients == Constants.numberOfDevices)){
				System.out.println("waiting...");
				sleep(1000);
			}
			
			Utils utils = new Utils();
			
			for(int i = 1; i < numberOfClients + 1; i++){
				
				CallHandler callHandler = new CallHandler();
				String clientIp = utils.getIp(i);
				
				try {
					
					int portNumber = 1900 + 10 + i;
					
					Client client = new Client(clientIp, portNumber, callHandler);
					System.out.println("reaching for client: " + "//" + clientIp + ":" + portNumber + "/federated-learning-client-" + i);
					CentroidsResults centroidsResults = CentroidsResults.getInstance();
					RemoteInterface access; 
					access = (RemoteInterface) client.getGlobal(RemoteInterface.class);
					access.sendRequest(centroidsResults.getCentroids());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
