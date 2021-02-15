import java.util.ArrayList;
import java.util.List;

public class Main {

	static List<String> arguments = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			arguments.add(args[i]);
		}
		
		boolean clearOnExit = false;
		
		if (arguments.contains("clearonexit")) {
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