package network;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Sender extends Thread {
	
	DataOutputStream outStream;
	
	public Sender(OutputStream _outStream) {
		outStream = new DataOutputStream(_outStream);
	}
	
	
	
	public void send(String _message) {
		try {
			outStream.writeUTF(_message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
