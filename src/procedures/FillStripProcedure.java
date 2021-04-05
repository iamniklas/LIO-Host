package procedures;

import com.github.mbelling.ws281x.Color;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

//Animation to fill the strip from a given direction (left, right, center, bounds) with a given color 
public class FillStripProcedure extends Procedure {
	private Color mFillColor = Color.BLACK;
	public int mModulo = 1;
	
	public FillStripProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
		mFillColor = (Color) _bundle.get(ProcedureBundleFields.COLOR_PRIMARY);
		mModulo = (int) _bundle.get(ProcedureBundleFields.MODULO);
		mIsSubProcedure = (boolean) _bundle.get(ProcedureBundleFields.IS_SUB_PROCEDURE);
		
		mSteps = 300;
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		for (int i = 0; i < 5; i++) {
			if ((mStep+i) % mModulo == 0) {
				mStrip.setPixel(mStep + i, mFillColor);		
			}
		}
		
		mStep+=5;
		
		if (mStep >= mSteps) {
			//strip.setAllPixels(Color.BLACK);
			if (!mIsSubProcedure) {
				finishProcedure();				
			}
		}
	}
}

/*
 * Ideas:
 * - Ease interpolation function
 * - Tiling (single fill as normal, double fill with e.g. "two center points" to fill from
 */