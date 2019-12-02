import java.io.ObjectInputStream.GetField;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import lipermi.handler.CallHandler;
import lipermi.net.Client;
import lipermi.net.Server;

import weka.clusterers.EM;
import weka.clusterers.FarthestFirst;
import weka.clusterers.MakeDensityBasedClusterer;
import weka.clusterers.SimpleKMeans;


public class Main {

	public static CentroidsResults cr = CentroidsResults.getInstance();
	
	public static void main(String[] args) {
		DatasetUtils datasetUtils = new DatasetUtils();
		FileUtils fileUtils = new FileUtils();
		WekaUtils wekaUtils = new WekaUtils();
		
		//TODO replace for args[] 
		int datasetSelector = Integer.parseInt(args[0]);//from 0 -- 18
		int deviceNumber = Integer.parseInt(args[1]);//from 1 - 8
		Constants.deviceNumber = deviceNumber;
		
		DatasetSizes size = datasetUtils.datasetSelector(datasetSelector);
		String pathToFile = datasetUtils.getPathToDataset(deviceNumber, size);
		//SimpleKMeans kmeans = wekaUtils.generateKmeans(fileUtils.readDataFile(pathToFile), 1);
		//EM em = wekaUtils.generateEM(fileUtils.readDataFile(pathToFile), 1);
		
		MakeDensityBasedClusterer db = wekaUtils.generateDb(fileUtils.readDataFile(pathToFile), 1);
		//FarthestFirst ff = wekaUtils.generateFarthestFirst(fileUtils.readDataFile(pathToFile), 1);
		//ArrayList<String> centroidResults = wekaUtils.getCentroidsFromArrayList(kmeans);
		//ArrayList<String> centroidResults = wekaUtils.getCentroidsFromArrayList(em);
		//ArrayList<String> centroidResults = wekaUtils.getCentroidsFromArrayList(ff);
		ArrayList<String> centroidResults = wekaUtils.getCentroidsFromArrayList(db);
		
		try {
			
			CallHandler callHandler = new CallHandler();
			int portNumber = 1900 + Constants.deviceNumber;
			String serverIp = Constants.serverIp;
			System.out.println("Looking up server at: " + "//" + Constants.serverIp);
			System.out.println("Sending results: " + centroidResults.size());
			
			Client client = new Client(serverIp, portNumber, callHandler);
			RemoteInterface access;
			access = (RemoteInterface) client.getGlobal(RemoteInterface.class);
					
			access.sendRequest(centroidResults);
			System.out.println("information was successfully forwarded to server.");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			System.out.println("creating threads to handle reply");
			CommunicationThread ct = new CommunicationThread();
			ct.start();
			ct.join();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			
			System.out.println("-->" + cr.getCentroids().size());
		    System.out.println("terminating or closing java program");
		    System.exit(1);
			
		}
	}
}

class CommunicationThread extends Thread {
	
	public void run(){
		
		Utils utils = new Utils();
		
		try {
			
			CallHandler callHandler = new CallHandler();
			String clientIp = utils.getIp(Constants.deviceNumber);
			int portNumber = 1900 + 10 + Constants.deviceNumber;
			System.out.println("Waiting for response from server at: " + "//" + clientIp + ":" + portNumber + "/federated-learning-client-" + Constants.deviceNumber + "");
			
			RemoteInterface remote;
			remote = RemoteImplementation.getInstance();
			callHandler.registerGlobal(RemoteInterface.class, remote);
			
			Server server = new Server();
			server.bind(portNumber, callHandler);
			
			while(!RemoteImplementation.getInstance().getDone()){
				System.out.println("waiting...");
				sleep(1500);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

