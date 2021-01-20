package network;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Receiver extends Thread {
	
	Socket socket;
	InputStream inputStream;
	DataInputStream dataInputStream;
	ReceiveCallback callback;
	
	public Receiver(Socket _socket, InputStream _inStream, ReceiveCallback _callback) {
		socket = _socket;
		inputStream = _inStream;
		dataInputStream = new DataInputStream(_inStream);
		callback = _callback;
	}
	
	public void run() {
		while(!socket.isClosed()) {
			try {
				//For Java Client
				//callback.onReceiveMessage(inStream.readUTF());
				
				System.out.println("Fick dich CS");
				
				//Java 8 for C# Client
				//Java 8 for C# Client
				int incomingsize = dataInputStream.readByte();
				System.out.println("Incoming: " + incomingsize);
				
				byte[] msgBinary = new byte[incomingsize];
				for (int i = 0; i < msgBinary.length; i++) {
					msgBinary[i] = dataInputStream.readByte();
				}
				
				//byte[] b = inputStream.readNBytes(incomingsize);
				
				String msg = new String(msgBinary, StandardCharsets.UTF_8);
				callback.onReceiveMessage(msg);
				
				//Java 9 for C# Client
				//byte[] byteInput = inStream.readAllBytes();
				//String msg = new String(byteInput, StandardCharsets.UTF_8);
				
				//if(!msg.isEmpty()) {
				//	callback.onReceiveMessage(msg);
				//}
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