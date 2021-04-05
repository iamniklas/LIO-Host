package procedures;

import led.ColorHSV;
import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class SineAbsProcedure extends Procedure {

	ColorRGB mColor;
	float mSineB = 0.05f;
	float mMoveAdditioner = 0;
	
	public SineAbsProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
		
		mColor = (ColorRGB) _bundle.get(ProcedureBundleFields.COLOR_PRIMARY); 
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mMoveAdditioner = mMoveAdditioner > Math.PI*4 ? 0 : mMoveAdditioner + 0.1f;
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			mStrip.setPixel(i, new ColorHSV(0, 0.0f, (float) Math.max(0.0f, Math.sin((float)i * mSineB + mMoveAdditioner))).ToRGB().toSystemColor());
		}
	}
}