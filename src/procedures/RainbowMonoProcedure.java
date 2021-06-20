package procedures;

import led.ColorHSV;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class RainbowMonoProcedure extends Procedure {

	ColorHSV mColorHSV = new ColorHSV(0, 1.0f, 1.0f);
	
	int mHueCounter = 0;
	
	float mRepetitions = 1;
	float mSpeed = 1;
	
	public RainbowMonoProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
		
		mSpeed = (float) ((double)_bundle.get(ProcedureBundleFields.SPEED));
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mColorHSV.h = (int) (mColorHSV.h > 360.0f ? 0 : mColorHSV.h + mSpeed);
		
		mStrip.setAllPixels(mColorHSV.ToRGB().toSystemColor());
	}
}