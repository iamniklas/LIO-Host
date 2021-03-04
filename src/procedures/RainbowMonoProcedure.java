package procedures;

import java.util.Map;

import led.ProcedureBundleTypes;
import led.ColorHSV;

public class RainbowMonoProcedure extends Procedure {

	ColorHSV mColorHSV = new ColorHSV(0, 1.0f, 1.0f);
	
	int hueCounter = 0;
	
	float repetitions = 1;
	int speed = 1;
	
	public RainbowMonoProcedure(Map<ProcedureBundleTypes, Object> _bundle) {
		
	}
	
	@Override
	void update() {
		mColorHSV.h = mColorHSV.h > 360 ? 0 : mColorHSV.h + speed;
		
		strip.setAllPixels(mColorHSV.ToRGB().toSystemColor());
	}
}