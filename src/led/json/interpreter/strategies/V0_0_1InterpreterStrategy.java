package led.json.interpreter.strategies;

import java.util.ArrayList;
import java.util.stream.Stream;

import led.ColorRGB;
import led.json.LEDJsonProcedure;

public class V0_0_1InterpreterStrategy implements IInterpreterStrategy {

	@Override
	public ColorRGB[] interpretLine(String _line) {
		String[] colorData = _line.split(" ");
		
		ArrayList<ColorRGB> colors = new ArrayList<ColorRGB>();
		for (int i = 0; i < colorData.length; i++) {
			String[] ledData = colorData[i].split("-");
			
			int[] arr = Stream.of(ledData).mapToInt(Integer::parseInt).toArray();
			
			colors.add(new ColorRGB(arr[0], arr[1], arr[2]));
		}
		
		return colors.toArray(new ColorRGB[colors.size()]);
	}

	@Override
	public LEDJsonProcedure interpretJson(String _json) {
		// TODO Auto-generated method stub
		return null;
	}

}
