package network;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;

public class Receiver extends Thread {
	
	Socket socket;
	DataInputStream inStream;
	ReceiveCallback callback;
	
	public Receiver(Socket _socket, InputStream _inStream, ReceiveCallback _callback) {
		socket = _socket;
		inStream = new DataInputStream(_inStream);
		callback = _callback;
	}
	
	public void run() {
		while(!socket.isClosed()) {
			try {
				callback.onReceiveMessage(inStream.readUTF());
			}
			catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}