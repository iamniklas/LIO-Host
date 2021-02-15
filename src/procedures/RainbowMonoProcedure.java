package procedures;

import led.ColorHSV;

public class RainbowMonoProcedure extends Procedure {

	ColorHSV mColorHSV = new ColorHSV();
	
	int hueCounter = 0;
	
	float repetitions = 1;
	int speed = 5;
	
	public RainbowMonoProcedure() {
		mColorHSV.s = 1.0f;
		mColorHSV.v = 1.0f;
	}
	
	@Override
	void update() {
		mColorHSV.h = hueCounter > 360 ? 0 : hueCounter + speed;
		
		strip.setAllPixels(mColorHSV.ToRGB());
	}
}