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
        float p = v * (1 - s);
        int p255 = (int) (p * 255.0f);
        float q = v * (1 - s * f);
        int q255 = (int) (q * 255.0f);
        float t = v * (1 - s * (1 - f));
        int t255 = (int) (t * 255.0f);
        
        Color result = 
        		hueInterval == 0 ||
        		hueInterval == 6 ? 	new Color(v255, t255, p255) :
    			hueInterval == 1 ? 	new Color(q255, v255, p255) :
				hueInterval == 2 ? 	new Color(p255, v255, t255) :
				hueInterval == 3 ? 	new Color(p255, q255, v255) :
				hueInterval == 4 ? 	new Color(t255, p255, v255) :
				hueInterval == 5 ? 	new Color(v255, p255, q255) : 
								   	new Color(0, 0, 0);
        
        return result;
	}
}