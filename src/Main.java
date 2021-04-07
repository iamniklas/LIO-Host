import java.util.ArrayList;
import java.util.List;

import led.json.interpreter.FileVersions;
import led.json.interpreter.LEDInterpreter;

public class Main {

	static List<String> mArguments = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			mArguments.add(args[i]);
		}
		
		LEDInterpreter.interpretLine(FileVersions.V0_0_0, "");
		
		boolean clearOnExit = false;
		
		if (mArguments.contains("clearonexit")) {
			clearOnExit = true;
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