package procedures;

import com.github.mbelling.ws281x.Color;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

//Set every strip's pixel to a uniform color in a instant 1 frame animation
public class ColorInstantSetProcedure extends Procedure {

	Color mTargetColor;
	
	public ColorInstantSetProcedure(LEDDataBundle _bundle) {		
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
		mTargetColor = (Color) _bundle.get(ProcedureBundleFields.COLOR_PRIMARY);
	}
	
	@Override
	void update() {
		mStrip.setAllPixels(mTargetColor);
		finishProcedure();
	}
}