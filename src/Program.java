import com.google.gson.Gson;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;
import network.ReceiveCallback;
import network.Server;
import procedures.Procedure;
import procedures.ProcedureFactory;
import procedures.ProcedureTypes;

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
		String[] parts = _message.split(":", 2);
		String proc = parts[0];
		String data = parts[1];
		
		ProcedureTypes type = ProcedureTypes.valueOf(proc);
		LEDDataBundle bundle = new Gson().fromJson(data, LEDDataBundle.class);
		bundle.set(ProcedureBundleFields.COLOR_PRIMARY, new Gson().fromJson(String.valueOf(bundle.get(ProcedureBundleFields.COLOR_PRIMARY)), ColorRGB.class));
		bundle.set(ProcedureBundleFields.COLOR_SECONDARY, new Gson().fromJson(String.valueOf(bundle.get(ProcedureBundleFields.COLOR_SECONDARY)), ColorRGB.class));
		LEDStripManager.mLEDStatus.mActive = bundle.get(ProcedureBundleFields.COLOR_SECONDARY) == ColorRGB.white50;
		mServer.getClient(0).mSender.send(new Gson().toJson(LEDStripManager.mLEDStatus));
		bundle.set(ProcedureBundleFields.COLOR_TERTIARY, new Gson().fromJson(String.valueOf(bundle.get(ProcedureBundleFields.COLOR_TERTIARY)), ColorRGB.class));
		bundle.set(ProcedureBundleFields.DURATION, new Gson().fromJson(String.valueOf(bundle.get(ProcedureBundleFields.DURATION)), float.class));
		bundle.set(ProcedureBundleFields.STRIP, mStrip);
		bundle.set(ProcedureBundleFields.CALLBACK, mStrip);
		
		Procedure p = ProcedureFactory.getProcedure(type, bundle);
		
		mStrip.mProcContainer.removeCurrentProcedure();
		mStrip.mProcContainer.queueProcedure(p);
	}
}
