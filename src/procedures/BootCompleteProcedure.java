package procedures;

import com.github.mbelling.ws281x.Color;

//A signal animation for the user that the strip is ready for use
public class BootCompleteProcedure extends Procedure {

	int stepsTotal = 720;
	
	@Override
	public void update() {
		running = true;
		double d = Math.abs(Math.sin(Math.toRadians(step)));
		
		strip.setAllPixels(new Color(0, (int) (d * 255), 0));
		
		step+=5;
		
		if(step >= stepsTotal) {
			strip.setAllPixels(Color.BLACK);
			strip.procContainer.removeCurrentProcedure();
			finishProcedure();
		}
		
		running = false;
	}
}