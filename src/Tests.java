import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;

import led.*;

public class Tests {

	@Test
	public void test() {
		LEDDataBundle ledDataBundle = new LEDDataBundle();
		ledDataBundle.set(ProcedureBundleFields.COLOR_PRIMARY, ColorRGB.red);
		ledDataBundle.set(ProcedureBundleFields.COLOR_SECONDARY, ColorRGB.black);
		String output = "FadeInFadeOut:";
		output += new Gson().toJson(ledDataBundle);
		System.out.println(output);
	}

}
