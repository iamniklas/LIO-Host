package procedures;

import java.util.Arrays;

import com.github.mbelling.ws281x.Color;

import led.LEDStripManager;

//Fade every pixel from its current state to a given uniform color
public class FadeToUniformColorProcedure extends Procedure {
	
	Color mTargetColor;
	
	Boolean[] mFinishedPixels = new Boolean[LEDStripManager.LED_COUNT];
	
	public FadeToUniformColorProcedure(Color _tarColor) {
		mTargetColor = _tarColor;
	}

	@Override
	void update() {
		//TODO Fix
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			Color currentColor = strip.stripData.getColorPyPixel(i);
			
			Color outputPixel = new Color(currentColor.getRed() + (mTargetColor.getRed() > currentColor.getRed() ? 1 : -1) ,
					currentColor.getGreen() + (mTargetColor.getGreen() > currentColor.getGreen() ? 1 : -1), 
					currentColor.getBlue() + (mTargetColor.getBlue() > currentColor.getBlue() ? 1 : -1));
			
			if (outputPixel == mTargetColor) {
				mFinishedPixels[i] = true;
			}
			
			strip.setPixel(i, outputPixel);
		}
		
		if (!Arrays.asList(mFinishedPixels).contains(false)) {
			strip.procContainer.removeCurrentProcedure();
			finishProcedure();
		}
	}
}