package led;

import com.github.mbelling.ws281x.*;

import led.json.LEDStatus;
import procedures.*;

public class LEDStripManager implements ProcedureCalls {
	private final LEDStripConfig mLedStripConfig = new LEDStripConfig();
	
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
	
	public static LEDStatus mLEDStatus = new LEDStatus();
	
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
		
//		LEDDataBundle jsonBundle = new LEDDataBundle();
//		jsonBundle.set(ProcedureBundleFields.STRIP,  this);
//		jsonBundle.set(ProcedureBundleFields.CALLBACK,  this);
//		jsonBundle.set(ProcedureBundleFields.PATH, "input.json");
//		JsonProcedure jsonproc = new JsonProcedure(jsonBundle);
//		mProcContainer.queueProcedure(jsonproc);
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