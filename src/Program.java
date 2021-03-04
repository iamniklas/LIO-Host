import java.util.HashMap;
import java.util.Map;

import com.github.mbelling.ws281x.Color;

import led.ProcedureBundleTypes;
import led.LEDStripManager;
import network.ReceiveCallback;
import network.Server;
import procedures.FillStripInterpolatedProcedure;

public class Program implements ReceiveCallback {
	
	LEDStripManager strip;
	Server server;
	
	public Program(boolean _clearOnExit) throws InterruptedException {
		System.out.println("Program \tINIT \tSTART");
		server = new Server(3333);
		server.setListener(this);
		server.start();
		
		strip = new LEDStripManager(_clearOnExit);
		System.out.println("Program \tINIT \tDONE");
	}
	
	public void update() {
		strip.update();
	}
	
	@Override
	public void onReceiveMessage(String _message) {
		System.out.println("Message received " + _message);
		String[] parts = _message.split(" ");
		Color color = new Color(
				Integer.parseInt(parts[1]), 
				Integer.parseInt(parts[2]), 
				Integer.parseInt(parts[3]));
		
		int mod = Integer.parseInt(parts[0]);

		Map<ProcedureBundleTypes, Object> bundle = new HashMap<ProcedureBundleTypes, Object>();
		bundle.put(ProcedureBundleTypes.COLOR_MAIN, color);
		bundle.put(ProcedureBundleTypes.MODULO, mod);
		
		FillStripInterpolatedProcedure fillStripInterpolated = new FillStripInterpolatedProcedure(bundle);
		fillStripInterpolated.strip = strip;
		fillStripInterpolated.callbacks = strip;
		strip.procContainer.queueProcedure(fillStripInterpolated);
	}
}
