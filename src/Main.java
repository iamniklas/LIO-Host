import network.ReceiveCallback;
import network.Server;

public class Main {

	public static void main(String[] args) {
		
		Program program = null;
		try {
			program = new Program();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			program.update();
		}
	}
}