package procedures;

import com.github.mbelling.ws281x.Color;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

//Set every strip's pixel to a uniform color in a instant 1 frame animation
public class ColorInstantSetProcedure extends Procedure {

	Color mTargetColor;
	
	public ColorInstantSetProcedure(LEDDataBundle _bundle) {		
		super(_bundle);
		mTargetColor = ((ColorRGB) _bundle.get(ProcedureBundleFields.COLOR_PRIMARY)).toSystemColor();
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mStrip.setAllPixels(mTargetColor);
		finishProcedure();
	}
}