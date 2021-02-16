package led;

import com.github.mbelling.ws281x.Color;

public class ColorRGB {
	
	int r = 255;
	int g = 255;
	int b = 255;
	int a = 255;
	
	public ColorRGB(int _r, int _g, int _b, int _a) {
		r = _r;
		g = _g;
		b = _b;
		a = _a;
	}
	
	public ColorRGB(int _r, int _g, int _b) {
		r = _r;
		g = _g;
		b = _b;
	}
	
	public ColorRGB(ColorRGB _color) {
		r = _color.r;
		g = _color.g;
		b = _color.b;
		a = _color.a;
	}
	
	public ColorRGB filter(ColorChannel _channel) {
		switch (_channel) {
			case Red: return new ColorRGB(0, g, b, a);
			case Green: return new ColorRGB(r, 0, b, a);
			case Blue: return new ColorRGB(r, g, 0, a);
			case Alpha: return new ColorRGB(r, g, b, 0);
			default: return new ColorRGB(0, 0, 0, a);
		}
	}
	
	public void setChannel(ColorChannel _channel, int _value) {
		switch (_channel) {
			case Red:
				r = _value;
				break;
			case Green:
				g = _value;
				break;
			case Blue:
				b = _value;
				break;
			case Alpha:
				a = _value;
				break;
		}
	}
	
	public ColorRGB cutLow(ColorChannel _channel, int _filter) {
		throw new UnsupportedOperationException("Cut low is not operational at the moment");
		//return new ColorRGB(0, 0, 0);
	}
	
	public ColorRGB cutHigh(ColorChannel _channel, int _filter) {
		throw new UnsupportedOperationException("Cut high is not operational at the moment");
		//return new ColorRGB(0, 0, 0);
	}
	
	public ColorHSV toHSV() {
		return new ColorHSV(0, 0, 0);
	}
	
	public Color toSystemColor() {
		return new Color(r, g, b);
	}
}