package led;
import java.util.ArrayList;
import java.util.Collections;

import com.github.mbelling.ws281x.Color;

public class LEDStrip {
	ArrayList<Color> strip = new ArrayList<Color>(300);
	
	public LEDStrip(int _ledCount) {
		strip = new ArrayList<Color>(_ledCount);
		
		Collections.fill(strip, Color.BLACK);
	}
}