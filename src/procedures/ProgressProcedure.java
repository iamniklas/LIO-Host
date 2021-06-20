package procedures;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;
import procedures.models.progress.ProgressBarData;

public class ProgressProcedure extends Procedure {

	public ProgressBarData mProgressData = new ProgressBarData();
	
	public ProgressProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
	}

	@Override
	public void start() {
		
	}

	@Override
	void update() {
		
	}
}