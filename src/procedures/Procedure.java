package procedures;

import led.LEDStripManager;

public abstract class Procedure {
	public LEDStripManager mStrip;
	public ProcedureCalls mCallbacks;
	
	int mStep = 0;
	int mSteps = 0;
	
	protected boolean mIsSubProcedure = false;
	
	public Procedure(LEDStripManager _strip, ProcedureCalls _callbacks) {
		mStrip = _strip;
		mCallbacks = _callbacks;
	}
	
	public abstract void start();
	abstract void update();
	
	protected void finishProcedure() {
		mStrip.mProcContainer.removeCurrentProcedure();
		mCallbacks.onProcedureFinish();
	}
}
