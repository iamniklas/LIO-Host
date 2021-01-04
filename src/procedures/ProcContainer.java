package procedures;

import led.LEDStripManager;

public class ProcContainer {
	LEDStripManager ledStrip;
	Procedure procedure;
	
	public ProcContainer(LEDStripManager _ledStripMng) {
		ledStrip = _ledStripMng;
	}
	
	public void setProcedure(Procedure _proc) {
		procedure = _proc;
		procedure.callbacks.onProcedureStart();
	}
	
	public void update() {
		if(procedure == null) return;
		
		procedure.update();
	}
}