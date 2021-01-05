package procedures;

import java.util.ArrayList;

import led.LEDStripManager;

public class ProcContainer {
	LEDStripManager ledStrip;
	ArrayList<Procedure> procedure = new ArrayList<Procedure>();
	
	public ProcContainer(LEDStripManager _ledStripMng) {
		ledStrip = _ledStripMng;
	}
	
	public void queueProcedure(Procedure _proc) {
		procedure.add(_proc);
		_proc.callbacks.onProcedureQueued();
	}
	
	public void removeCurrentProcedure() {
		procedure.remove(0);
		
		if (procedure.size() > 0) {
			procedure.get(0).callbacks.onProcedureStart();
		}
	}
	
	public void update() {
		if(procedure.size() < 1) return;
		
		procedure.get(0).update();
	}
}