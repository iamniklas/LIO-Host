package procedures;

import com.github.mbelling.ws281x.Color;

import led.ColorChannel;
import led.ColorHSV;
import led.LEDStripManager;

public class RainbowProcedure extends Procedure {
	
	ColorHSV mColorHSV = new ColorHSV();
	
	int hueCounter = 0;
	
	float repetitions = 1;
	int speed = 5;
	
	public RainbowProcedure() {
		mColorHSV.s = 1.0f;
		mColorHSV.v = 1.0f;
	}
	
	@Override
	void update() {
		
		hueCounter = hueCounter > 360 ? 0 : hueCounter + speed;
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			mColorHSV.h = ((int) (i * (repetitions * 1.2f)) + hueCounter) % 360;
			Color cached = mColorHSV.ToRGB().filter(ColorChannel.Red).filter(ColorChannel.Green).toSystemColor();
			Color result = cached;
			strip.setPixel(i, result);
		}
	}
}