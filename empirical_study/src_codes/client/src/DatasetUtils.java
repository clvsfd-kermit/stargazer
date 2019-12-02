
public class DatasetUtils {
	
	public DatasetUtils(){}
	
	public String getPathToDataset(int deviceNumber, DatasetSizes size){
		
		return Constants.homePath + "/Desktop/Datasets/" + size + "/" + deviceNumber + "/dataset_test" + deviceNumber + ".arff";
	}
	
	public String getPathToDataset2(int deviceNumber, DatasetSizes size){
		
		return Constants.homePath + "/Desktop/Datasets/" + size + "/" + deviceNumber + "/dataset_test" + deviceNumber + ".txt";
	}
	
	public DatasetSizes datasetSelector(int i){
		
		switch (i) {
			case 0:
				return DatasetSizes.D100;		
			case 1:
				return DatasetSizes.D200;			
			case 2:
				return DatasetSizes.D300;			
			case 3:
				return DatasetSizes.D400;			
			case 4:
				return DatasetSizes.D500;
			case 5:
				return DatasetSizes.D600;
			case 6:
				return DatasetSizes.D700;
			case 7:
				return DatasetSizes.D800;
			case 8:
				return DatasetSizes.D900;
			case 9:
				return DatasetSizes.D1000;
			case 10:
				return DatasetSizes.D1500;
			case 11:
				return DatasetSizes.D2000;
			case 12:
				return DatasetSizes.D2500;
			case 13:
				return DatasetSizes.D3000;
			case 14:
				return DatasetSizes.D3500;
			case 15:
				return DatasetSizes.D4000;
			case 16:
				return DatasetSizes.D4500;
			case 17:
				return DatasetSizes.D5000;
			case 18:
				return DatasetSizes.D5500;
			default:
				return null;
		}	
	}
	
}
