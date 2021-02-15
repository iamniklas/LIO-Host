package led;

import com.github.mbelling.ws281x.Color;

public class ColorHSV {
	public int h;
	public float s;
	public float v;
	
	public Color ToRGB() {
		int v255 = (int) (v * 255.0f);
		if(s == 0.0f) { return new Color(v255, v255, v255); }
		
		int hueInterval = (int) Math.floor(h / 60.0f);
        float f = (h / 60.0f) - (float) hueInterval;
        
        int p = (int) ((v * (1 - s)) * 255.0f);
        int q = (int) ((v * (1 - s * f)) * 255.0f);
        int t = (int) ((v * (1 - s * (1 - f))) * 255.0f);
        
        Color result = 
        		hueInterval == 0 ||
        		hueInterval == 6 ? 	new Color(v255, t, p) :
    			hueInterval == 1 ? 	new Color(q, v255, p) :
				hueInterval == 2 ? 	new Color(p, v255, t) :
				hueInterval == 3 ? 	new Color(p, q, v255) :
				hueInterval == 4 ? 	new Color(t, p, v255) :
				hueInterval == 5 ? 	new Color(v255, p, q) : 
								   	new Color(0, 0, 0);
        
        return result;
	}
}