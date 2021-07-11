package procedures;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class SleepProcedure extends Procedure {
	
	public SleepProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
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