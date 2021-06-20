package procedures;

import java.util.ArrayList;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;
import procedures.models.navigation.PositionMarker;

public class NavigationProcedure extends Procedure {

	public ArrayList<PositionMarker> mMarkers = new ArrayList<PositionMarker>();
	
	public NavigationProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));
	}

	@Override
	public void start() {
		
	}

	@Override
	void update() {
		
	}
}