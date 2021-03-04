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
	
	//Cuts a channel from the lower side - if the channel value is higher than the filter it will be set to 0
	public ColorRGB cutLow(ColorChannel _channel, int _filter) {
		_filter = Math.max(Math.min(_filter, 255), 0);
		switch (_channel) {
			case Red: return new ColorRGB(cutLow(r, _filter), g, b, a);
			case Green: return new ColorRGB(r, cutLow(g, _filter), b, a);
			case Blue: return new ColorRGB(r, g, cutLow(b, _filter), a);
			case Alpha: return new ColorRGB(r, g, b, cutLow(a, _filter));
			default: return this;
		}
	}
	
	//Cuts a channel from the higher side - if the channel value is lower than the filter it will be set to 0
	public ColorRGB cutHigh(ColorChannel _channel, int _filter) {
		_filter = Math.max(Math.min(_filter, 255), 0);
		switch (_channel) {
			case Red: return new ColorRGB(cutHigh(r, _filter), g, b, a);
			case Green: return new ColorRGB(r, cutHigh(g, _filter), b, a);
			case Blue: return new ColorRGB(r, g, cutHigh(b, _filter), a);
			case Alpha: return new ColorRGB(r, g, b, cutHigh(a, _filter));
			default: return this;
		}
	}
	
	public ColorHSV toHSV() {
		return new ColorHSV(0, 0, 0);
	}
	
	public Color toSystemColor() {
		return new Color(r, g, b);
	}
	
	private int cutLow(int _value, int _filter) {
		if (_value >= _filter) {
			return 0;
		}
		else {
			return _value;
		}
	}
	
	private int cutHigh(int _value, int _filter) {
		if (_value <= _filter) {
			return 0;
		}
		else {
			return _value;
		}
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append(b).append(" - ").toString();
	}
}