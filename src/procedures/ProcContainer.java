package procedures;

import java.util.ArrayList;

import led.LEDStripManager;

public class ProcContainer {
	LEDStripManager mLedStrip;
	ArrayList<Procedure> mProcedure = new ArrayList<Procedure>();
	
	public ProcContainer(LEDStripManager _ledStripMng) {
		mLedStrip = _ledStripMng;
	}
	
	public void queueProcedure(Procedure _proc) {
		mProcedure.add(_proc);
		_proc.mCallbacks.onProcedureQueued();
	}
	
	public void removeCurrentProcedure() {
		mProcedure.remove(0);
		
		if (mProcedure.size() > 0) {
			mProcedure.get(0).mCallbacks.onProcedureStart();
		}
	}
	
	public void update() {
		if(mProcedure.size() < 1) return;
		
		mProcedure.get(0).update();
	}
}