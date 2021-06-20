package procedures;

import com.github.mbelling.ws281x.Color;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class BlinkProcedure extends Procedure {
	
	Color mBlinkColor;
	int mFrames = 10;
	int mModulo = 2;
	
	public BlinkProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
		
		mBlinkColor = ((ColorRGB) _bundle.get(ProcedureBundleFields.COLOR_PRIMARY)).toSystemColor();
		mFrames = Math.round(((float)_bundle.get(ProcedureBundleFields.DURATION)));
		mModulo = (int) Math.round((double)_bundle.get(ProcedureBundleFields.MODULO));
		
		mSteps = mFrames;
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mStep++;
		
		if (mStep % mModulo == 0) {
			mStrip.setAllPixels(mBlinkColor);
		}
		else {
			mStrip.setAllPixels(ColorRGB.black.toSystemColor());
		}
		
		if (mStep == mSteps) {
			mStrip.setAllPixels(ColorRGB.black.toSystemColor());
			finishProcedure();
		}
	}
}