import java.util.ArrayList;
import java.util.List;

public class Main {

	static List<String> mArguments = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			mArguments.add(args[i]);
		}
		
		boolean clearOnExit = false;
		
		if (mArguments.contains("clearonexit")) {
			clearOnExit = true;
		}
		
		Program program = null;
		try {
			program = new Program(clearOnExit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true) {
			program.update();
		}
	}
}