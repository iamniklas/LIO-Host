package procedures;

import com.github.mbelling.ws281x.Color;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

//A signal animation to notify the user that the strip is no longer ready for any reason (internal error, network disconnect, etc.)
public class NoLongerReadyProcedure extends Procedure {

	int mTotalSteps = 60;
	
	boolean mRedLightActive = false;
	
	public NoLongerReadyProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
	}
	
	@Override
	public void update() {
		if (mStep % 5 == 0) {
			if (mRedLightActive) {
				mStrip.setAllPixels(Color.BLACK);
			}
			else {
				mStrip.setAllPixels(Color.RED);
			}
			mRedLightActive = !mRedLightActive;
		}
		mStep++;
		
		if (mStep > mTotalSteps) {
			mStrip.setAllPixels(Color.BLACK);
			finishProcedure();
		}
	}
}