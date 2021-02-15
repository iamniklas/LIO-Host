package procedures;

import com.github.mbelling.ws281x.Color;

import interpolation.Interpolation;
import interpolation.InterpolationType;
import led.LEDStripManager;

public class FillStripInterpolatedProcedure extends Procedure {

	private int litLEDs = 0;
	private float percentage = 0.0f;
	
	private Color fillColor = Color.BLACK;
	InterpolationType interpolationType = InterpolationType.EaseOutBounce;

	public FillStripInterpolatedProcedure(Color _color) {
		fillColor = _color;
	}
	
	@Override
	void update() {
		percentage = step / (float)LEDStripManager.LED_COUNT;
		step++;
		
		litLEDs = Math.min(300, (int) (Interpolation.getInterpolationValue(percentage, interpolationType) * 300.0f));
		
		System.out.println(litLEDs);
		
		strip.setAllPixels(Color.BLACK);
		strip.setArea(0, litLEDs, fillColor);
		
		if (step > LEDStripManager.LED_COUNT) {
			strip.procContainer.removeCurrentProcedure();
			finishProcedure();
		}
	}
}