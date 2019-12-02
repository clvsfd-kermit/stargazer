import java.io.BufferedReader;
import java.util.ArrayList;

import patbench.vavr.collection.IndexedSeq;

import singularity.FixedEvalSize;
import singularity.ProblemConfig;
import singularity.StandardSystem.EVect;

import weka.clusterers.Cobweb;
import weka.clusterers.EM;
import weka.clusterers.FarthestFirst;
import weka.clusterers.MakeDensityBasedClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class WekaUtils {

	public WekaUtils(){}
	
//	public static void main(String[] args) {
//		
//		int randomSeed = 0;
//		int fuzzingSize = 100;
//		int sizePolicy = FixedEvalSize(fuzzingSize);
//		
//		
//		
//		String datafile = "";
//		
//		if(args != null){
//		
//			String input = args[0];
//			System.out.println(input);
//			if(input.length() > 0) {
//				input = input.substring(0, 1);
//			} else {
//				input = "";
//			}
//			switch (args[0]) {
//			case "0":
//				datafile = "/home/breno/Documents/complexity_testing/0/dataset_test1.arff";
//				break;
//			case "1":
//				datafile = "/home/breno/Documents/complexity_testing/1/dataset_test1.arff";
//				break;
//	
//			case "2":
//				datafile = "/home/breno/Documents/complexity_testing/2/dataset_test1.arff";
//				break;
//	
//			case "3":
//				datafile = "/home/breno/Documents/complexity_testing/3/dataset_test1.arff";
//				break;
//	
//			case "4":
//				datafile = "/home/breno/Documents/complexity_testing/4/dataset_test1.arff";
//				break;
//	
//			case "5":
//				datafile = "/home/breno/Documents/complexity_testing/5/dataset_test1.arff";
//				break;
//	
//			case "6":
//				datafile = "/home/breno/Documents/complexity_testing/6/dataset_test1.arff";
//				break;
//	
//			case "7":
//				datafile = "/home/breno/Documents/complexity_testing/7/dataset_test1.arff";
//				break;
//		
//			case "8":
//				datafile = "/home/breno/Documents/complexity_testing/8/dataset_test1.arff";
//				break;
//	
//			case "9":
//				datafile = "/home/breno/Documents/complexity_testing/9/dataset_test1.arff";
//				break;
//		
//			default:
//				break;
//			}
//			
//			SimpleKMeans kmeans = generateKmeans(new FileUtils().readDataFile(datafile), 1);
//		}
//		
//	}
	
	public static MakeDensityBasedClusterer generateDb(BufferedReader datafile, int numberOfClusters) {
		
		System.out.println("Running bd");
		MakeDensityBasedClusterer dbsc = new MakeDensityBasedClusterer();
		
		try {
			
			dbsc.setNumClusters(numberOfClusters);
			Instances data = new Instances(datafile);
			dbsc.buildClusterer(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbsc;
		
	}
	
	
	public static SimpleKMeans generateKmeans(BufferedReader datafile, int numberOfClusters) {
		
		System.out.println("Running k_means");
		SimpleKMeans kmeans = new SimpleKMeans();
		
		try {
			
			kmeans.setSeed(1);
			kmeans.setPreserveInstancesOrder(false);//trying to optimize the weka library
			kmeans.setFastDistanceCalc(true);//wasn't here before
			kmeans.setReduceNumberOfDistanceCalcsViaCanopies(true);
			kmeans.setMaxIterations(2);
			kmeans.setNumClusters(numberOfClusters);
			Instances data = new Instances(datafile);
			kmeans.buildClusterer(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return kmeans;
		
	}
	
	public FarthestFirst generateFarthestFirst(BufferedReader datafile, int numberOfClusters) {
		
		System.out.println("Running Cobweb");
		FarthestFirst ff = new FarthestFirst();
		
		try {
			
			ff.setSeed(1);
			ff.setNumClusters(1);
			Instances data = new Instances(datafile);
			ff.buildClusterer(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ff; 
		
	}
	
	public EM generateEM(BufferedReader datafile, int numberOfClusters) {
		
		System.out.println("Running EM");
		EM em = new EM();
		
		try {
			
			em.setSeed(1);
			em.setNumClusters(numberOfClusters);
			Instances data = new Instances(datafile);
			em.buildClusterer(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return em; 
		
	}
	
	
	public ArrayList<String> getCentroidsFromArrayList(MakeDensityBasedClusterer db){
		
		ArrayList<String> centroidsResults = new ArrayList<String>();
		
		try {
			
			for (int i = 0; i < db.numberOfClusters(); i++) {
				
				centroidsResults.add(db.toString());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return centroidsResults;
		
	}
	
	public ArrayList<String> getCentroidsFromArrayList(EM em){
		
		ArrayList<String> centroidsResults = new ArrayList<String>();
		
		try {
			
			for (int i = 0; i < em.getNumClusters(); i++) {
				
				centroidsResults.add(em.toString());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return centroidsResults;
		
	}
	
	public ArrayList<String> getCentroidsFromArrayList(FarthestFirst ff){
		
		ArrayList<String> centroidsResults = new ArrayList<String>();
		
		try {
			
			for (int i = 0; i < ff.getNumClusters(); i++) {
				
				centroidsResults.add(ff.toString());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return centroidsResults;
		
	}
	
	public ArrayList<String> getCentroidsFromArrayList(SimpleKMeans kmeans){
		
		ArrayList<String> centroidsResults = new ArrayList<String>();
		
		try {
			
			for (int i = 0; i < kmeans.getClusterCentroids().size(); i++) {
				
				centroidsResults.add(kmeans.getClusterCentroids().get(i).toString());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return centroidsResults;
		
	}
	
}
