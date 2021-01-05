package procedures;

import com.github.mbelling.ws281x.Color;

//Set every strip's pixel to a uniform color in a instant 1 frame animation
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
