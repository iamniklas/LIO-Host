package procedures;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Map;

import led.ProcedureBundleTypes;

public class SimpleBPMProcedure extends Procedure {

	public ArrayList<Area> mAreas = new ArrayList<Area>();
	
	public int bpm;
	public float mBeatStep = 0.0f;
	
	public SimpleBPMProcedure(Map<ProcedureBundleTypes, Object> _bundle) {
		mBeatStep = 60.0f / (float)bpm;
		
	}
	
	@Override
	void update() {
		
	}
}