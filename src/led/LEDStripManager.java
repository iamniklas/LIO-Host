package led;

import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStripType;
import com.github.mbelling.ws281x.Ws281xLedStrip;

import procedures.ColorInstantSetProcedure;
import procedures.FadeInFadeOutProcedure;
import procedures.FillStripProcedure;
import procedures.ProcContainer;
import procedures.ProcedureCalls;
import procedures.BootCompleteProcedure;

public class LEDStripManager implements ProcedureCalls {
	final static int LED_COUNT = 300;
	final static int GPIO_PIN = 18;
	final static int FREQ = 800000;
	final static int DMA = 10;
	final static int BRIGHTNESS = 255;
	final static int PWM_CHANNEL = 0;
	final static boolean INVERT = false;
	final static LedStripType LED_STRIP_TYPE = LedStripType.WS2811_STRIP_GRB;
	boolean clearOnExit = true;

	boolean runRedAlertOnlyOnce = false;
	
	private static Ws281xLedStrip strip;
	
	LEDStrip stripData = new LEDStrip(LED_COUNT);
	
	public ProcContainer procContainer = new ProcContainer(this);
	
	public LEDStripManager(boolean _clearOnExit) throws InterruptedException {
		System.out.println("LED Strip \tINIT \tSTART");
		
		clearOnExit = _clearOnExit;
		
		strip = new Ws281xLedStrip(
						LED_COUNT, 
						GPIO_PIN, 
						FREQ, 
						DMA, 
						BRIGHTNESS, 
						PWM_CHANNEL, 
						INVERT,
						LED_STRIP_TYPE, 
						clearOnExit);
		
		System.out.println("LED Strip \tINIT \tDONE");
		
		FadeInFadeOutProcedure procR = new FadeInFadeOutProcedure(Color.RED);
		procR.strip = this;
		procR.callbacks = this;
		procContainer.queueProcedure(procR);
		
		FadeInFadeOutProcedure procG = new FadeInFadeOutProcedure(Color.GREEN);
		procG.strip = this;
		procG.callbacks = this;
		procContainer.queueProcedure(procG);
		
		FadeInFadeOutProcedure procB = new FadeInFadeOutProcedure(Color.BLUE);
		procB.strip = this;
		procB.callbacks = this;
		procContainer.queueProcedure(procB);
		
		FadeInFadeOutProcedure procM = new FadeInFadeOutProcedure(Color.MAGENTA);
		procM.strip = this;
		procM.callbacks = this;
		procContainer.queueProcedure(procM);
		
		FillStripProcedure fillProc = new FillStripProcedure();
		fillProc.strip = this;
		fillProc.callbacks = this;
		procContainer.queueProcedure(fillProc);
		
		BootCompleteProcedure proc = new BootCompleteProcedure();
		proc.strip = this;
		proc.callbacks = this;
		procContainer.queueProcedure(proc);
	}
	
	public void update() {
		procContainer.update();
		
		for (int i = 0; i < stripData.strip.size(); i++) {
			strip.setPixel(i, stripData.strip.get(i));
		}
		strip.render();
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param _pxl The pixel index
	 * @param _color The color to set the pixel
	 */
	public void setPixel(int _pxl, Color _color) {
		stripData.strip.set(_pxl, _color);
	}
	
	/**
	 * @param _startPxl The start index for the area
	 * @param _endPxl The end index for the area
	 * @param _color The color to fill the strip area with
	 */
	public void setArea(int _startPxl, int _endPxl, Color _color) {
		for (int i = _startPxl; i < _endPxl; i++) {
			stripData.strip.set(i, _color);
		}
	}
	
	/**
	 * @param _color The color to fill the strip with
	 */
	public void setAllPixels(Color _color) {
		for (int i = 0; i < LED_COUNT; i++) {
			stripData.strip.set(i, _color);
		}
	}

	@Override
	public void onProcedureStart() {
		System.out.println("Procedure started");
	}

	@Override
	public void onProcedureQueued() {
		System.out.println("Procedure queued");
	}
	
	@Override
	public void onProcedureFinish() {
		System.out.println("Procedure done");
	}

}