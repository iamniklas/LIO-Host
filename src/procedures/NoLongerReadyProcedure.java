package procedures;

import com.github.mbelling.ws281x.Color;

public class NoLongerReadyProcedure extends Procedure {

	int totalSteps = 60;
	
	boolean redLightActive = false;
	
	@Override
	public void update() {
		running = true;
		
		if (step % 5 == 0) {
			if (redLightActive) {
				strip.setAllPixels(Color.BLACK);
			}
			else {
				strip.setAllPixels(Color.RED);
			}
			redLightActive = !redLightActive;
		}
		step++;
		
		if (step > totalSteps) {
			strip.setAllPixels(Color.BLACK);
			strip.procContainer.procedure = null;
			finishProcedure();
		}
		
		running = false;
	}
}