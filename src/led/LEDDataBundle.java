package led;

import java.util.HashMap;
import java.util.Map;

public class LEDDataBundle {
	Map<ProcedureBundleFields, Object> mBundle = new HashMap<ProcedureBundleFields, Object>();
	
	public void set(ProcedureBundleFields _fieldType, Object _object) {
		mBundle.put(_fieldType, _object);
	}
	
	public boolean hasKey(ProcedureBundleFields _field) {
		return mBundle.containsKey(_field);
	}
	
	public Object get(ProcedureBundleFields _fieldType) {
		return mBundle.getOrDefault(_fieldType, null);
	}
}