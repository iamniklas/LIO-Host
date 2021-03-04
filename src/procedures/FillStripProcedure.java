package procedures;

import java.util.Map;

import com.github.mbelling.ws281x.Color;

import led.ProcedureBundleTypes;

//Animation to fill the strip from a given direction (left, right, center, bounds) with a given color 
public class FillStripProcedure extends Procedure {

	int totalSteps = 300; //matches strip length
	Color fillColor = Color.BLACK;
	public int modulo = 1;
	
	public FillStripProcedure(Map<ProcedureBundleTypes, Object> _bundle) {
		if (_bundle.containsKey(ProcedureBundleTypes.COLOR_MAIN)) {
			fillColor = (Color) _bundle.get(ProcedureBundleTypes.COLOR_MAIN);
			modulo = (int) _bundle.get(ProcedureBundleTypes.MODULO);
		}
	}
	
	@Override
	void update() {
		for (int i = 0; i < 5; i++) {
			if ((step+i) % modulo == 0) {
				strip.setPixel(step + i, fillColor);		
			}
		}
		
		step+=5;
		
		if (step >= totalSteps) {
			//strip.setAllPixels(Color.BLACK);
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