import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;

import lipermi.handler.CallHandler;
import lipermi.net.Client;


public class Main {

	public static void main(String[] args) {
		
		//Constants.datasetId = Integer.parseInt(args[0]);
		Constants.datasetId = 18;
		//if(args[1] != null) {
		//Constants.runNumber = Integer.parseInt(args[1]);
		//}
		Constants.runNumber = 20;
		int dataset = 0;
		int lastRun = 0;
		
		boolean flag = false;
		
		if(args[0] != null){
		
			dataset = Integer.parseInt(args[0]);
			
			
		} 

		if(args[1] != null) {
			
			lastRun = Integer.parseInt(args[1]);
			flag = true;
		}
		
		for(int n = dataset; n < Constants.datasetId + 1; n++){
			
			for(int j = lastRun; j < Constants.runNumber; j++){ //start(int runNumber, int dataSetid);// 1_18_1
				ArrayList<ClientCommunicator> scs = new ArrayList<>();
				for (int i = 0; i < Constants.numberOfClients + 1; i++) {
					
					ClientCommunicator cc = new ClientCommunicator(i, n, j);
					cc.start();
					scs.add(cc);
					try {
			            
						for (int t = 0; t < 2 ; t++) {
			                Thread.sleep(1000);
			                System.out.print(".");
			            }
			            
			        } catch (InterruptedException ie) {
			            Thread.currentThread().interrupt();
			        }
				}
				try {
					for(int i = 0; i < scs.size(); i++){
						scs.get(i).join();
					}
				} catch (Exception e) {
					
				} finally {
					
					try {
			            
						for (int i = 0; i < 10 ; i++) {
			                Thread.sleep(1000);
			                System.out.print(".");
			            }
			            
			        } catch (InterruptedException ie) {
			            Thread.currentThread().interrupt();
			        }
				}
				System.out.println("run number : " + j);
			}
		
			if(flag){
				flag = false;
				lastRun = 0;
			}
			
			try {
	            
				for (int i = 0; i < 100 ; i++) {
	                Thread.sleep(1000);
	                System.out.print(".");
	            }
	            
	        } catch (InterruptedException ie) {
	            Thread.currentThread().interrupt();
	        }
		}
		
		
	}
}

class ClientCommunicator extends Thread {
	
	//ServerId = 0;
	private int clientId = -1;
	private int datasetId = -1;
	private int runNumber = -1;
	
	public ClientCommunicator(int clientId, int datasetId, int runNumber) {
		
		this.clientId = clientId;
		this.datasetId = datasetId;
		this.runNumber = runNumber;
		
	}
	
	public void run(){
		
		Utils utils = new Utils();
		CallHandler callHandler = new CallHandler();
		
		try {
			
			if(this.clientId != 0){
				
				sleep(2000);
				
			} else {
				sleep(1000);
			}
			
			String clientIp = utils.getIp(this.clientId);
			int portNumber = 1920 + this.clientId; 
			Client client = new Client(clientIp, portNumber, callHandler);
			
			System.out.println("Looking up server at: " + clientIp);
			System.out.println(".......testing......");
			
			RemoteInterface access; 
			access = (RemoteInterface) client.getGlobal(RemoteInterface.class);
			System.out.println("starting...");
			boolean result = access.start(this.datasetId, runNumber);
			
			System.out.println(result);
			
			if(result){
				
				System.out.println("Successful");
				sleep(1000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
