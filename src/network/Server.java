package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread {
	ServerSocket serverSocket;
	
	static ArrayList<ClientService> clients = new ArrayList<ClientService>();
	
	private int mPort;
	
	ReceiveCallback callback;
	
	public Server(int _port) {
		mPort = _port;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Network \tINIT \tSTART");
			serverSocket = new ServerSocket(mPort);
			System.out.println("Network \tINIT \tDONE");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			try {
				clients.add(new ClientService(this, serverSocket.accept()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setListener(ReceiveCallback _callback) {
		callback = _callback;
	}
	
	public void receiveMessage(String _message) {
		callback.onReceiveMessage(_message);
	}
	
	public static void makeRPCCallToAll(String _message) {
		for (ClientService c : clients) {
			c.sender.send(_message);
		}
	}
	
	public static void makeRPCCallNoHost(int _hostID, String _message) {
		ArrayList<ClientService> targets = new ArrayList<ClientService>();
		
		for(ClientService client : clients) {
			if (client.id != _hostID) {
				targets.add(client);
			}
		}
		
		for(ClientService client : targets) {
			client.sender.send(_message);
		}
	}
}
