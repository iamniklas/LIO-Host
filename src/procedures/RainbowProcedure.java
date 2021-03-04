package procedures;

import java.util.Map;

import led.ProcedureBundleTypes;
import led.ColorHSV;
import led.LEDStripManager;

public class RainbowProcedure extends Procedure {
	
	ColorHSV mColorHSV = new ColorHSV(0, 1.0f, 1.0f);
	
	float hueCounter = 0;
	
	float repetitions = 0.75f;
	float speed = 3;
	
	public RainbowProcedure(Map<ProcedureBundleTypes, Object> _bundle) {
		
	}
	
	@Override
	void update() {
		hueCounter = hueCounter > 360 ? 0 : hueCounter + speed;
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			mColorHSV.h = (int) ((int) (i * (repetitions * 1.2f)) + hueCounter) % 360;
			strip.setPixel(i, mColorHSV.ToRGB().toSystemColor());
		}
	}
}