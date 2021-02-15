package procedures;

import led.ColorHSV;

public class RainbowMonoProcedure extends Procedure {

	ColorHSV mColorHSV = new ColorHSV();
	
	int hueCounter = 0;
	
	float repetitions = 1;
	int speed = 1;
	
	public RainbowMonoProcedure() {
		mColorHSV.s = 1.0f;
		mColorHSV.v = 1.0f;
	}
	
	@Override
	void update() {
		mColorHSV.h = mColorHSV.h > 360 ? 0 : mColorHSV.h + speed;
		
		strip.setAllPixels(mColorHSV.ToRGB());
	}
}