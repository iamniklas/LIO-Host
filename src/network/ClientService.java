package network;
import java.io.IOException;
import java.net.Socket;

public class ClientService extends Thread implements ReceiveCallback {
	
	static int nextId = 0;
	public int id = 0;
	
	Server server;
	Socket socket;
	Sender sender;
	Receiver receiver;
	
	public ClientService(Server _server, Socket _socket) {
		id = nextId;
		nextId++;
		
		server = _server;
		socket = _socket;
		
		System.out.println("New client connected");
		
		try {
			sender = new Sender(socket.getOutputStream());
			receiver = new Receiver(socket, socket.getInputStream(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		receiver.start();
	}

	@Override
	public void onReceiveMessage(String _message) {
		System.out.println(_message);
		server.receiveMessage(_message);
		//Server.makeRPCCallToAll(_message);
	}
}