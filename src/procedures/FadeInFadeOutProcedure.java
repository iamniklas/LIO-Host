package procedures;

import java.util.Map;

import com.github.mbelling.ws281x.Color;

import led.ProcedureBundleTypes;

//Fade one given color in, then fade out to black
public class FadeInFadeOutProcedure extends Procedure {
	
	private float[] mColorPartModifier = {1.0f, 1.0f, 1.0f};
	private int totalSteps = 180;
	
	public FadeInFadeOutProcedure(Map<ProcedureBundleTypes, Object> _bundle) {
		if (_bundle.containsKey(ProcedureBundleTypes.COLOR_MAIN)) {
			Color targetColor = (Color) _bundle.get(ProcedureBundleTypes.COLOR_MAIN);
			mColorPartModifier[0] = (float) targetColor.getRed() / 255.0f;
			mColorPartModifier[1] = (float) targetColor.getGreen() / 255.0f;
			mColorPartModifier[2] = (float) targetColor.getBlue() / 255.0f;			
		}
	}

	@Override
	void update() {
		float d = (float) Math.abs(Math.sin(Math.toRadians(step)));
		
		strip.setAllPixels(new Color(
				(int) (mColorPartModifier[0] * d * 255.0f), 
				(int) (mColorPartModifier[1] * d * 255.0f), 
				(int) (mColorPartModifier[2] * d * 255.0f)));
		
		step+=5;
		
		if (step >= totalSteps) {
			strip.setAllPixels(Color.BLACK);
			strip.procContainer.removeCurrentProcedure();
			finishProcedure();
		}
	}
	
	
}
