package procedures;

import java.util.Map;

import com.github.mbelling.ws281x.Color;

import led.ProcedureBundleTypes;

//A signal animation to notify the user that the strip is no longer ready for any reason (internal error, network disconnect, etc.)
public class NoLongerReadyProcedure extends Procedure {

	int totalSteps = 60;
	
	boolean redLightActive = false;
	
	public NoLongerReadyProcedure(Map<ProcedureBundleTypes, Object> _bundle) {
		
	}
	
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