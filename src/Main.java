import java.util.HashMap;

import network.ReceiveCallback;
import network.Server;

public class Main {

	static HashMap<String, String> cmdArgs = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		//Read cmd arguments as pairs seperated by = sign
		for (int i = 0; i < args.length; i++) {
			String[] argValCombo = args[i].split("=");
			if (argValCombo.length != 2) {
				throw new Exception("Invalid program arguments");
			}
			cmdArgs.put(argValCombo[0], argValCombo[1]);
		}
		
		
		boolean clearOnExit = false;
		
		if (cmdArgs.containsKey("clearonexit")) {
			clearOnExit = cmdArgs.get("clearonexit").toLowerCase().equals("true");
		}
		
		Program program = null;
		try {
			program = new Program(clearOnExit);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			program.update();
		}
	}
}