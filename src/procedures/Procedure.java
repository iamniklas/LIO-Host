package procedures;

import led.LEDStripManager;

public abstract class Procedure {
	public LEDStripManager mStrip;
	public ProcedureCalls mCallbacks;
	
	int mStep = 0;
	
	public Procedure(LEDStripManager _strip, ProcedureCalls _callbacks) {
		mStrip = _strip;
		mCallbacks = _callbacks;
	}
	
	abstract void update();
	
	protected void finishProcedure() {
		mStrip.mProcContainer.removeCurrentProcedure();
		mCallbacks.onProcedureFinish();
	}
}
