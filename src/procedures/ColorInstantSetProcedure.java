package procedures;

import com.github.mbelling.ws281x.Color;

public class ColorInstantSetProcedure extends Procedure {

	Color tarColor;
	
	public ColorInstantSetProcedure(Color _tarColor) {
		tarColor = _tarColor;
	}
	
	@Override
	void update() {
		strip.setAllPixels(tarColor);
		
		strip.procContainer.procedure = null;
		finishProcedure();
	}

}
