package procedures;

public interface ProcedureCalls {
	public void onProcedureStart();
	public void onProcedureQueued();
	public void onProcedureFinish();
}