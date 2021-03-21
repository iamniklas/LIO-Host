import led.ProcedureBundleFields;
import led.LEDDataBundle;
import led.LEDStripManager;
import network.ReceiveCallback;
import network.Server;
import procedures.FillStripInterpolatedProcedure;

public class Program implements ReceiveCallback {
	
	LEDStripManager mStrip;
	Server mServer;
	
	public Program(boolean _clearOnExit) throws InterruptedException {
		System.out.println("Program \tINIT \tSTART");
		mServer = new Server(3333);
		mServer.setListener(this);
		mServer.start();
		
		mStrip = new LEDStripManager(_clearOnExit);
		System.out.println("Program \tINIT \tDONE");
	}
	
	public void update() {
		mStrip.update();
	}
	
	@Override
	public void onReceiveMessage(String _message) {
		System.out.println("Message received " + _message);
		String[] parts = _message.split(" ");
//		Color color = new Color(
//				Integer.parseInt(parts[1]), 
//				Integer.parseInt(parts[2]), 
//				Integer.parseInt(parts[3]));
		
		int mod = Integer.parseInt(parts[0]);

		LEDDataBundle bundle = new LEDDataBundle();
		bundle.set(ProcedureBundleFields.STRIP, mStrip);
		bundle.set(ProcedureBundleFields.CALLBACK, mStrip);
		bundle.set(ProcedureBundleFields.MODULO, mod);
		
		FillStripInterpolatedProcedure fillStripInterpolated = new FillStripInterpolatedProcedure(bundle);
		mStrip.mProcContainer.queueProcedure(fillStripInterpolated);
	}
}
