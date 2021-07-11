package procedures;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public abstract class Procedure {
	public LEDStripManager mStrip;
	public ProcedureCalls mCallbacks;
	
	int mStep = 0;
	int mSteps = 0;
	
	int mModulo = 1;
	
	protected boolean mIsSubProcedure = false;
	
	public Procedure(LEDDataBundle _bundle) {
		mStrip = (LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP);
		mCallbacks = (ProcedureCalls)_bundle.get(ProcedureBundleFields.CALLBACK);
		
		if (_bundle.hasKey(ProcedureBundleFields.MODULO)) {
			mModulo = (int) ((double) _bundle.get(ProcedureBundleFields.MODULO));
		}
	}
	
	public abstract void start();
	abstract void update();
	void postUpdate() {
		if(mModulo < 2) return;
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			if (i % mModulo == 0) {
				mStrip.setPixel(i, ColorRGB.black.toSystemColor());
			}
		}
	}
	
	protected void finishProcedure() {
		mStrip.mProcContainer.removeCurrentProcedure();
		mCallbacks.onProcedureFinish();
	}
}
