package network;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Receiver extends Thread {
	Socket mSocket;
	InputStream mInputStream;
	DataInputStream mDataInputStream;
	ReceiveCallback mCallback;
	
	enum ClientType {UNSPECIFIED, CS, CPP, Java}
	ClientType mClientType = ClientType.UNSPECIFIED;
	
	public Receiver(Socket _socket, InputStream _inStream, ReceiveCallback _callback) {
		mSocket = _socket;
		mInputStream = _inStream;
		mDataInputStream = new DataInputStream(_inStream);
		mCallback = _callback;
	}
	
	public synchronized void run() {
		while(!mSocket.isClosed()) {
			try {
				String stringBuffer = "";
				int incomingSize = 0;
				
				switch (mClientType) {
				case CS:
					incomingSize = mDataInputStream.readByte();
					stringBuffer = readString(incomingSize);
					mCallback.onReceiveMessage(stringBuffer);
					break;
				
				case Java:
					String request = mDataInputStream.readUTF();
					System.out.println(request);
					mCallback.onReceiveMessage(request);
					break;
					
				case CPP:
					incomingSize = mDataInputStream.readByte();
					stringBuffer = readString(incomingSize);
					mCallback.onReceiveMessage(stringBuffer);
					break;
					
				case UNSPECIFIED:
					incomingSize = mDataInputStream.readByte();
					mClientType = ClientType.values()[incomingSize];
					System.out.println("Client is of type " + mClientType);
					break;
				}
			}
			catch (IOException e) {
				try {
					mSocket.close();
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
			messageBinary[i] = mDataInputStream.readByte();
		}
		return new String(messageBinary, StandardCharsets.UTF_8);
	}
}