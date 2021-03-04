package procedures;

import java.util.Map;

import com.github.mbelling.ws281x.Color;

import led.ProcedureBundleTypes;

//Set every strip's pixel to a uniform color in a instant 1 frame animation
public class ColorInstantSetProcedure extends Procedure {

	Color tarColor;
	
	public ColorInstantSetProcedure(Map<ProcedureBundleTypes, Object> _bundle) {
		if (_bundle.containsKey(ProcedureBundleTypes.COLOR_MAIN)) {			
			tarColor = (Color) _bundle.get(ProcedureBundleTypes.COLOR_MAIN);
		}
	}
	
	@Override
	void update() {
		strip.setAllPixels(tarColor);
		
		strip.procContainer.procedure = null;
		finishProcedure();
	}

}
