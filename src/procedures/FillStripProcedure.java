package procedures;

import com.github.mbelling.ws281x.Color;

//Animation to fill the strip from a given direction (left, right, center, bounds) with a given color 
public class FillStripProcedure extends Procedure {

	int totalSteps = 290; //matches strip length
	
	@Override
	void update() {
		for (int i = 0; i < 5; i++) {
			strip.setPixel(step + i, Color.MAGENTA);
		}
		
		step+=5;
		
		if (step >= totalSteps) {
			strip.setAllPixels(Color.BLACK);
			strip.procContainer.removeCurrentProcedure();
			finishProcedure();
		}
	}
}

/*
 * Ideas:
 * - Ease interpolation function
 * - Tiling (single fill as normal, double fill with e.g. "two center points" to fill from
 */