package procedures;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;
import procedures.models.progress.ProgressBarData;

public class ProgressProcedure extends Procedure {

	public ProgressBarData mProgressData = new ProgressBarData();
	
	private float mStart = 0.0f;
	private float mProgress = 0.0f;
	private ColorRGB mColor;
	private boolean mPulsing = false;
	private float mSineX = 0.0f;
	private float mSpeed = 0.2f;
	
	public ProgressProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mColor = (ColorRGB)_bundle.get(ProcedureBundleFields.COLOR_PRIMARY);
		mStart = (float) ((double)_bundle.get(ProcedureBundleFields.VALUE_1));
		mProgress = (float) ((double)_bundle.get(ProcedureBundleFields.VALUE_2));
		mPulsing = (boolean) _bundle.get(ProcedureBundleFields.VALUE_3);
		mSteps = 1;
		
		//start();
	}

	@Override
	public void start() {		
		mStrip.setAllPixels(ColorRGB.black.toSystemColor());
	}

	@Override
	void update() {
		if (mPulsing) {
			mSineX += mSpeed;
			if (mSineX > Math.PI * 2) mSineX = 0;
			
			mStrip.setArea((int)(LEDStripManager.LED_COUNT * mStart), 
						   (int)(LEDStripManager.LED_COUNT * mProgress), 
						   mColor.dim((float)Math.abs(Math.sin(mSineX))).toSystemColor());
		}
		else {
			mStrip.setArea((int)(LEDStripManager.LED_COUNT * mStart), 
						   (int)(LEDStripManager.LED_COUNT * mProgress), 
						   mColor.toSystemColor());
		}
	}
}