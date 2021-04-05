package procedures;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;
import led.json.LEDJsonProcedure;

public class JsonProcedure extends Procedure {

	LEDJsonProcedure mLEDJsonProcedure;
	
	public JsonProcedure(LEDDataBundle _bundle) {
		super((LEDStripManager)_bundle.get(ProcedureBundleFields.STRIP), 
			      (ProcedureCalls) _bundle.get(ProcedureBundleFields.CALLBACK));

		mLEDJsonProcedure = loadFromFile((String) _bundle.get(ProcedureBundleFields.PATH));
		mSteps = mLEDJsonProcedure.mLEDStates.length - 1;
		
		//mIsSubProcedure = (boolean) _bundle.get(ProcedureBundleFields.IS_SUB_PROCEDURE);
	}
	
	@Override
	public void start() {
		
	}

	@Override
	void update() {
		for (int i = 0; i < mLEDJsonProcedure.mMetaInfo.mLedCount; i++) {
			mStrip.setPixel(i, mLEDJsonProcedure.mLEDStates[mStep].mLEDState[i].toSystemColor());
		}
		
		mStep++;
		
		if (mStep >= mSteps) {
			finishProcedure();
		}
	}
	
	private LEDJsonProcedure loadFromFile(String _path) {
		  byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(_path));
			return new Gson().fromJson(new String(encoded, StandardCharsets.US_ASCII), LEDJsonProcedure.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}