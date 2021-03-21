package procedures;

import com.google.gson.annotations.SerializedName;

import led.ColorHSV;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class RainbowProcedure extends Procedure {
	
	@SerializedName(value="color")
	ColorHSV mColorHSV = new ColorHSV(0, 1.0f, 1.0f);
	
	private float mHueCounter = 0;
	
	@SerializedName(value="repetitions")
	public float mRepetitions = 0.75f;
	@SerializedName(value="speed")
	public float mSpeed = 3;
	
	public RainbowProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
		      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
		
		System.out.println("REPETITIONS: " + _bundle.get(ProcedureBundleFields.REPETITIONS));
		System.out.println("SPEED: " + _bundle.get(ProcedureBundleFields.SPEED));
		
		mRepetitions = (float) _bundle.get(ProcedureBundleFields.REPETITIONS);
		mSpeed = (float) _bundle.get(ProcedureBundleFields.SPEED);
	}
	
	@Override
	void update() {
		mHueCounter = mHueCounter > 360 ? 0 : mHueCounter + mSpeed;
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			mColorHSV.h = (int) ((int) (i * (mRepetitions * (360.0f / (float)LEDStripManager.LED_COUNT))) + mHueCounter) % 360;
			mStrip.setPixel(i, mColorHSV.ToRGB().toSystemColor());
		}
	}
}