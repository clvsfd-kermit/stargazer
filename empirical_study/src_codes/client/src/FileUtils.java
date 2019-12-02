import java.io.BufferedReader;
import java.io.FileReader;


public class FileUtils {

	public FileUtils(){}
	
	public BufferedReader readDataFile(String pathToFile) {
		
		BufferedReader bufferedReader = null;
		
		try {
			
			bufferedReader = new BufferedReader(new FileReader(pathToFile));
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return bufferedReader;
		
	}
	
}
