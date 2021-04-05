package led;

import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStripType;
import com.github.mbelling.ws281x.Ws281xLedStrip;

import procedures.ProcContainer;
import procedures.Procedure;
import procedures.ProcedureCalls;
import procedures.ProcedureFactory;
import procedures.ProcedureTypes;
import procedures.RainbowProcedure;
import procedures.SleepProcedure;
import procedures.BootCompleteProcedure;
import procedures.FadeInFadeOutProcedure;
import procedures.FadeToMultiColorProcedure;
import procedures.FillStripProcedure;
import procedures.JsonProcedure;
import procedures.MultiProcedure;

public class LEDStripManager implements ProcedureCalls {
	public static final int LED_COUNT = 300;
	static final int GPIO_PIN = 18;
	static final int FREQ = 800000;
	static final int DMA = 10;
	static final int BRIGHTNESS = 255;
	static final int PWM_CHANNEL = 0;
	static final boolean INVERT = false;
	static final LedStripType LED_STRIP_TYPE = LedStripType.WS2811_STRIP_GRB;
	private boolean mClearOnExit = true;
	
	private static Ws281xLedStrip mStrip;
	
	public LEDStrip mStripData = new LEDStrip(LED_COUNT);
	
	public ProcContainer mProcContainer = new ProcContainer(this);
	
	private int mFrametime = 16;
	
	public LEDStripManager(boolean _clearOnExit) throws InterruptedException {
		System.out.println("LED Strip \tINIT \tSTART");
		
		mClearOnExit = _clearOnExit;
		
		mStrip = new Ws281xLedStrip(
						LED_COUNT, 
						GPIO_PIN, 
						FREQ, 
						DMA, 
						BRIGHTNESS, 
						PWM_CHANNEL, 
						INVERT,
						LED_STRIP_TYPE, 
						mClearOnExit);
		
		System.out.println("LED Strip \tINIT \tDONE");
		
		LEDDataBundle bundle = new LEDDataBundle();
		bundle.set(ProcedureBundleFields.STRIP, this);
		bundle.set(ProcedureBundleFields.CALLBACK, this);
		bundle.set(ProcedureBundleFields.SPEED, 3.0f);
		bundle.set(ProcedureBundleFields.REPETITIONS, 1.0f);
		
		BootCompleteProcedure proc = (BootCompleteProcedure) ProcedureFactory.getProcedure(ProcedureTypes.BootComplete, bundle);
		mProcContainer.queueProcedure(proc);
		
		LEDDataBundle b1 = new LEDDataBundle();
		b1.set(ProcedureBundleFields.STRIP, this);
		b1.set(ProcedureBundleFields.CALLBACK, this);
		b1.set(ProcedureBundleFields.PATH, "testprocedure.json");
		JsonProcedure json = (JsonProcedure) ProcedureFactory.getProcedure(ProcedureTypes.JsonProcedure, b1);
		mProcContainer.queueProcedure(json);
		
		//Multi Procedure Test
		LEDDataBundle b2 = new LEDDataBundle();
		b2.set(ProcedureBundleFields.STRIP, this);
		b2.set(ProcedureBundleFields.CALLBACK, this);
		b2.set(ProcedureBundleFields.MODULO, 1);
		b2.set(ProcedureBundleFields.COLOR_PRIMARY, ColorRGB.red.toSystemColor());
		b2.set(ProcedureBundleFields.IS_SUB_PROCEDURE, true);
		FillStripProcedure fill1 = (FillStripProcedure) ProcedureFactory.getProcedure(ProcedureTypes.Fill, b2);
		LEDDataBundle b3 = new LEDDataBundle();
		b3.set(ProcedureBundleFields.STRIP, this);
		b3.set(ProcedureBundleFields.CALLBACK, this);
		b3.set(ProcedureBundleFields.MODULO, 2);
		b3.set(ProcedureBundleFields.COLOR_PRIMARY, ColorRGB.blue.toSystemColor());
		b3.set(ProcedureBundleFields.IS_SUB_PROCEDURE, true);
		FillStripProcedure fill2 = (FillStripProcedure) ProcedureFactory.getProcedure(ProcedureTypes.Fill, b3);
		LEDDataBundle multiBundle = new LEDDataBundle();
		multiBundle.set(ProcedureBundleFields.STRIP, this);
		multiBundle.set(ProcedureBundleFields.CALLBACK, this);
		multiBundle.set(ProcedureBundleFields.SUB_BUNDLE, new Procedure[] {fill1, fill2});
		MultiProcedure multiProcedure = (MultiProcedure) ProcedureFactory.getProcedure(ProcedureTypes.MultiProcedure, multiBundle);
		mProcContainer.queueProcedure(multiProcedure);
		
		LEDDataBundle b4 = new LEDDataBundle();
		b4.set(ProcedureBundleFields.STRIP, this);
		b4.set(ProcedureBundleFields.CALLBACK, this);
		b4.set(ProcedureBundleFields.COLOR_PRIMARY, ColorRGB.black);
		b4.set(ProcedureBundleFields.DURATION, 0.5f);
		FadeToMultiColorProcedure multiuniform = (FadeToMultiColorProcedure) ProcedureFactory.getProcedure(ProcedureTypes.FadeToMultiColor, b4);
		mProcContainer.queueProcedure(multiuniform);
		
		LEDDataBundle fio = new LEDDataBundle();
		fio.set(ProcedureBundleFields.STRIP, this);
		fio.set(ProcedureBundleFields.CALLBACK, this);
		fio.set(ProcedureBundleFields.COLOR_PRIMARY, new Color(128, 0, 0));
		FadeInFadeOutProcedure fioproc = (FadeInFadeOutProcedure) ProcedureFactory.getProcedure(ProcedureTypes.FadeInFadeOut, fio);
		mProcContainer.queueProcedure(fioproc);
		
		LEDDataBundle waitBundle = new LEDDataBundle();
		waitBundle.set(ProcedureBundleFields.REPETITIONS, 60);
		waitBundle.set(ProcedureBundleFields.CALLBACK, this);
		waitBundle.set(ProcedureBundleFields.STRIP, this);
		SleepProcedure sleepProc = new SleepProcedure(waitBundle);
		mProcContainer.queueProcedure(sleepProc);
		
		LEDDataBundle b5 = new LEDDataBundle();
		b5.set(ProcedureBundleFields.STRIP, this);
		b5.set(ProcedureBundleFields.CALLBACK, this);
		b5.set(ProcedureBundleFields.SPEED, 5.0f);
		b5.set(ProcedureBundleFields.REPETITIONS, 2.0f);
		RainbowProcedure rainbowProc = (RainbowProcedure) ProcedureFactory.getProcedure(ProcedureTypes.Rainbow, b5);
		mProcContainer.queueProcedure(rainbowProc);
	}
	
	public void update() {
		mProcContainer.update();
		
		for (int i = 0; i < mStripData.mStrip.size(); i++) {
			mStrip.setPixel(i, mStripData.mStrip.get(i));
		}
		mStrip.render();
		try {
			Thread.sleep(mFrametime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getFrametime() {
		return mFrametime;
	}
	
	/**
	 * @param _pxl The pixel index
	 * @param _color The color to set the pixel
	 */
	public void setPixel(int _pxl, Color _color) {
		_color = new Color(Math.max(0, _color.getRed()), Math.max(0, _color.getGreen()), Math.max(0, _color.getBlue()));
		mStripData.mStrip.set(_pxl, _color);
	}
	
	/**
	 * @param _startPxl The start index for the area
	 * @param _endPxl The end index for the area
	 * @param _color The color to fill the strip area with
	 */
	public void setArea(int _startPxl, int _endPxl, Color _color) {
		for (int i = _startPxl; i < _endPxl; i++) {
			mStripData.mStrip.set(i, _color);
		}
	}
	
	/**
	 * @param _color The color to fill the strip with
	 */
	public void setAllPixels(Color _color) {
		for (int i = 0; i < LED_COUNT; i++) {
			mStripData.mStrip.set(i, _color);
		}
	}

	@Override
	public void onProcedureStart(Procedure _proc) {
		_proc.start();
	}

	@Override
	public void onProcedureQueued() {

	}
	
	@Override
	public void onProcedureFinish() {

	}

}