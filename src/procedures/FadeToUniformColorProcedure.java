package procedures;

import led.ProcedureBundleFields;

import com.google.gson.Gson;

import led.ColorRGB;
import led.ColorRGBA;
import led.LEDDataBundle;
import led.LEDStripManager;

//Fade every pixel from a base color to a target color
public class FadeToUniformColorProcedure extends Procedure {
	
	ColorRGB mBaseColor;
	ColorRGB mTargetColor;
	float mDuration = 0.0f;
	
	int mCounter = 0;
	int mSteps = 0;
	
	float mAlphaStep = 0.0f;
	private float mAlphaAddValue = 0.0f;
	
	public FadeToUniformColorProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
		      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
		
		mTargetColor = (ColorRGB) _bundle.get(ProcedureBundleFields.COLOR_PRIMARY);
		mBaseColor = (ColorRGB) _bundle.get(ProcedureBundleFields.COLOR_SECONDARY);
		mDuration = (float) _bundle.get(ProcedureBundleFields.DURATION);
		
//		mTargetColor = new Gson().fromJson(_bundle.get(ProcedureBundleFields.COLOR_PRIMARY).toString(), ColorRGB.class);
//		mBaseColor = new Gson().fromJson(_bundle.get(ProcedureBundleFields.COLOR_SECONDARY).toString(), ColorRGB.class);
//		mDuration = new Gson().fromJson(_bundle.get(ProcedureBundleFields.DURATION).toString(), float.class);
		
		mSteps = (int) Math.ceil(mDuration / (mStrip.getFrametime() / 1000.0f));
		
		mAlphaAddValue = 1 / (float)mSteps;
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mCounter++;
		mAlphaStep += mAlphaAddValue;
		
		ColorRGBA outputColor = new ColorRGBA(mBaseColor.r, mBaseColor.g, mBaseColor.b, (int)(mAlphaStep * 255));
		
		mStrip.setAllPixels(outputColor.toRGB(mTargetColor).toSystemColor());
		
		if (mCounter > mSteps) {
			mStrip.setAllPixels(mBaseColor.toSystemColor());
			finishProcedure();
		}
	}
}