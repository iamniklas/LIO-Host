package network;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Receiver extends Thread {
	
	Socket socket;
	InputStream inputStream;
	DataInputStream dataInputStream;
	ReceiveCallback callback;
	
	enum ClientType {UNSPECIFIED, CS, CPP, Java}
	ClientType mClientType = ClientType.UNSPECIFIED;
	
	public Receiver(Socket _socket, InputStream _inStream, ReceiveCallback _callback) {
		socket = _socket;
		inputStream = _inStream;
		dataInputStream = new DataInputStream(_inStream);
		callback = _callback;
	}
	
	public synchronized void run() {
		while(!socket.isClosed()) {
			try {
				String stringBuffer = "";
				int incomingSize = 0;
				
				switch (mClientType) {
				case CS:
					incomingSize = dataInputStream.readByte();
					stringBuffer = readString(incomingSize);
					callback.onReceiveMessage(stringBuffer);
					break;
				
				case Java:
					callback.onReceiveMessage(dataInputStream.readUTF());
					break;
					
				case CPP:
					incomingSize = dataInputStream.readByte();
					stringBuffer = readString(incomingSize);
					callback.onReceiveMessage(stringBuffer);
					break;
					
				case UNSPECIFIED:
					System.out.println("F");
					incomingSize = dataInputStream.readByte();
					mClientType = ClientType.values()[incomingSize];
					System.out.println("Client is of type " + mClientType);
					break;
				}
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
	
	String readString(int _size) throws IOException {
		byte[] messageBinary = new byte[_size];
		for (int i = 0; i < messageBinary.length; i++) {
			messageBinary[i] = dataInputStream.readByte();
		}
		return new String(messageBinary, StandardCharsets.UTF_8);
	}
}