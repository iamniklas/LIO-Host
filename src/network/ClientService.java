package network;
import java.io.IOException;
import java.net.Socket;

public class ClientService extends Thread implements ReceiveCallback {
	
	static int mNextId = 0;
	public int mId = 0;
	
	Server mServer;
	Socket mSocket;
	Sender mSender;
	Receiver mReceiver;
	
	public ClientService(Server _server, Socket _socket) {
		mId = mNextId;
		mNextId++;
		
		mServer = _server;
		mSocket = _socket;
		
		System.out.println("New client connected");
		
		try {
			mSender = new Sender(mSocket.getOutputStream());
			mReceiver = new Receiver(mSocket, mSocket.getInputStream(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mReceiver.start();
	}

	@Override
	public void onReceiveMessage(String _message) {
		System.out.println(_message);
		mServer.receiveMessage(_message);
		//Server.makeRPCCallToAll(_message);
	}
}