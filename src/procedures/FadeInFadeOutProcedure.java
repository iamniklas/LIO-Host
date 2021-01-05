package procedures;

import com.github.mbelling.ws281x.Color;

//Fade one given color in, then fade out to black
public class FadeInFadeOutProcedure extends Procedure {
	
	private float[] mColorPartModifier = {1.0f, 1.0f, 1.0f};
	private int totalSteps = 180;
	
	public FadeInFadeOutProcedure(Color _tarColor) {
		mColorPartModifier[0] = (float) _tarColor.getRed() / 255.0f;
		mColorPartModifier[1] = (float) _tarColor.getGreen() / 255.0f;
		mColorPartModifier[2] = (float) _tarColor.getBlue() / 255.0f;
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
