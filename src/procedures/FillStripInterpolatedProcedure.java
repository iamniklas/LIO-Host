package procedures;

import com.github.mbelling.ws281x.Color;

import interpolation.Interpolation;
import interpolation.InterpolationType;

public class FillStripInterpolatedProcedure extends Procedure {

	private int totalSteps = 300;
	private int litLEDs = 0;
	private float percentage = 0.0f;
	
	private Color fillColor = Color.BLACK;
	InterpolationType interpolationType = InterpolationType.EaseOutBounce;

	public FillStripInterpolatedProcedure(Color _color) {
		fillColor = _color;
	}
	
	@Override
	void update() {
		percentage = step / (float)totalSteps;
		step++;
		
		litLEDs = Math.min(300, (int) (Interpolation.getInterpolationValue(percentage, interpolationType) * 300.0f));
		
		System.out.println(litLEDs);
		
		strip.setAllPixels(Color.BLACK);
		strip.setArea(0, litLEDs, fillColor);
		
		if (step > totalSteps) {
			strip.procContainer.removeCurrentProcedure();
			finishProcedure();
		}
	}
}