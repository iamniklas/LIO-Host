package procedures;

import com.github.mbelling.ws281x.Color;

import interpolation.Interpolation;
import interpolation.InterpolationType;
import led.ProcedureBundleFields;
import led.LEDDataBundle;
import led.LEDStripManager;

public class FillStripInterpolatedProcedure extends Procedure {

	private int mLitLEDs = 0;
	private float mPpercentage = 0.0f;
	
	private Color mFillColor = Color.BLACK;
	InterpolationType mInterpolationType = InterpolationType.EaseOutBounce;

	public FillStripInterpolatedProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		if (_bundle.hasKey(ProcedureBundleFields.COLOR_PRIMARY)) {
			mFillColor = (Color) _bundle.get(ProcedureBundleFields.COLOR_PRIMARY);
		}
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mPpercentage = mStep / (float)LEDStripManager.LED_COUNT;
		mStep++;
		
		mLitLEDs = Math.min(300, (int) (Interpolation.getInterpolationValue(mPpercentage, mInterpolationType) * 300.0f));
		
		System.out.println(mLitLEDs);
		
		mStrip.setAllPixels(Color.BLACK);
		mStrip.setArea(0, mLitLEDs, mFillColor);
		
		if (mStep > LEDStripManager.LED_COUNT) {
			mStrip.mProcContainer.removeCurrentProcedure();
			finishProcedure();
		}
	}
}