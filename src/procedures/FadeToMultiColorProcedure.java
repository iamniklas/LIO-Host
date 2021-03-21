package procedures;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

//Action is like FadeToUniformColorProcedure, but individual for every pixel
public class FadeToMultiColorProcedure extends Procedure {

	public FadeToMultiColorProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
	}
	
	@Override
	void update() {
		
	}
}