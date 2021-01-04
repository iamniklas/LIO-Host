import java.time.LocalDate;
import java.util.Date;
import java.util.TimerTask;

import com.github.mbelling.ws281x.Color;

import led.LEDStripManager;
import network.ReceiveCallback;
import network.Server;

public class Program implements ReceiveCallback {
	
	LEDStripManager strip;
	Server server;
	
	public Program() throws InterruptedException {
		System.out.println("Program \tINIT \tSTART");
		server = new Server(3333);
		server.setListener(this);
		server.start();
		
		strip = new LEDStripManager();
		System.out.println("Program \tINIT \tDONE");
	}
	
	public void update() {
		strip.update();
	}
	
	@Override
	public void onReceiveMessage(String _message) {
		System.out.println("Message received " + _message);
		String[] parts = _message.split(" ");
		Color color = new Color(
				Integer.parseInt(parts[0]), 
				Integer.parseInt(parts[1]), 
				Integer.parseInt(parts[2]));
		
		strip.setAllPixels(color);
	}
}
