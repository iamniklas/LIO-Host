package procedures;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class SleepProcedure extends Procedure {
	
	public SleepProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
		
		mSteps = (int) _bundle.get(ProcedureBundleFields.REPETITIONS);
	}
	
	@Override
	public void start() {
		
	}

	@Override
	void update() {
		mStep++;
		
		if (mStep > mSteps) {
			finishProcedure();
		}
	}
}