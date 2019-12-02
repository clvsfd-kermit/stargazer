
public class Utils {

	public Utils(){}
	
	public String getIp(int clientId){
		
		switch (clientId) {
		case 1:
			return "192.168.1.183";
		case 2:
			return "192.168.1.205";
		case 3:
			return "192.168.1.152";
		case 4:
			return "192.168.1.141";
		case 5:
			return "192.168.1.224";
		case 6:
			return "192.168.1.215";
		case 7:
			return "192.168.1.137";
		case 8:
			return "192.168.1.235";
		default:
			return "192.168.1.239";
		}
		
	}
	
}
