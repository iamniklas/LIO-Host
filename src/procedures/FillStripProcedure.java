package procedures;

import com.github.mbelling.ws281x.Color;

//Animation to fill the strip from a given direction (left, right, center, bounds) with a given color 
public class FillStripProcedure extends Procedure {

	int totalSteps = 300; //matches strip length
	Color fillColor = Color.BLACK;
	public int modulo = 1;
	
	public FillStripProcedure(Color _fillColor, int _modulo) {
		fillColor = _fillColor;
		modulo = _modulo;
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