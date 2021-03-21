package led;
import java.util.ArrayList;

import com.github.mbelling.ws281x.Color;

public class LEDStrip {
	ArrayList<Color> mStrip = new ArrayList<Color>(300);
	
	public LEDStrip(int _ledCount) {
		mStrip = new ArrayList<Color>(_ledCount);
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			mStrip.add(Color.BLACK);
		}
	}
	
	public Color getColorPyPixel(int _id) {
		return mStrip.get(_id);
	}
}