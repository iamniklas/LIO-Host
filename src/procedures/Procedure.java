package procedures;

import led.LEDStripManager;

public abstract class Procedure {
	boolean running = false;
	public LEDStripManager strip;
	public ProcedureCalls callbacks;
	
	int step = 0;
	
	abstract void update();
	
	protected void finishProcedure() {
		callbacks.onProcedureFinish();
	}
}
