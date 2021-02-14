package procedures;

import led.ColorHSV;

public class HueProcedure extends Procedure {
	
	ColorHSV mColorHSV = new ColorHSV();
	
	int hueCounter = 0;
	
	public HueProcedure() {
		mColorHSV.s = 1.0f;
		mColorHSV.v = 1.0f;
	}
	
	@Override
	void update() {
		hueCounter = hueCounter > 360 ? 0 : hueCounter + 5;
		
		for (int i = 0; i < 300; i++) {
			mColorHSV.h = ((int) (i * 1.2f) + hueCounter) % 360;
			strip.setPixel(i, mColorHSV.ToRGB());
		}
	}
}