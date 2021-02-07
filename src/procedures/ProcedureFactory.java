package procedures;

import com.github.mbelling.ws281x.Color;

public class ProcedureFactory {
	
	public static ProcedureFactory instance = new ProcedureFactory();
	
	public ProcedureFactory() {
		if (instance == null) {
			instance = this;
		}
	}
	
	public Procedure getProcedure(ProcedureTypes _types, Color _tarColor) {
		switch(_types) {
		case BootComplete: 		 return new BootCompleteProcedure();
		case ColorInstantSet: 	 return new ColorInstantSetProcedure(_tarColor); 
		case FadeInFadeOut: 	 return new FadeInFadeOutProcedure(_tarColor);
		case FadeToMultiColor: 	 return new FadeToMultiColorProcedure(_tarColor);
		case FadeToUniformColor: return new FadeToUniformColorProcedure(_tarColor);
		case Fill: 				 return new FillStripProcedure(_tarColor, 1);
		case FillInterpolated: 	 return new FillStripInterpolatedProcedure(_tarColor);
		case NoLongerReady:		 return new NoLongerReadyProcedure();
		default: 				 return null;
		}
	}
}