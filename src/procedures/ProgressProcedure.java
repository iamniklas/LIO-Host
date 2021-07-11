package procedures;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;
import procedures.models.progress.ProgressBarData;

public class ProgressProcedure extends Procedure {

	public ProgressBarData mProgressData = new ProgressBarData();
	
	private float mProgress = 0.0f;
	private ColorRGB mColor; 
	
	public ProgressProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mColor = (ColorRGB)_bundle.get(ProcedureBundleFields.COLOR_PRIMARY);
		mProgress = (float) ((double)_bundle.get(ProcedureBundleFields.VALUE_1));
		mSteps = 1;
	}

	@Override
	public void start() {
		
	}

	@Override
	void update() {
		mStrip.setAllPixels(ColorRGB.black.toSystemColor());
		mStrip.setArea(0, (int)(LEDStripManager.LED_COUNT * mProgress), mColor.toSystemColor());
		postUpdate();
		
		if (mStep == mSteps) {
			finishProcedure();
		}
		
		mStep++;
	}
}