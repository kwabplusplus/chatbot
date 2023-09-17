import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class ChattyChatChatClient 
{

	public static void main(String[] args) 
	{
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		
		System.out.println("Connecting to " + hostname + " on port: " + port);
		
		
		try 
		{
			// Create Socket, attempt connection
			// Implicitly binds an ephemeral port
			Socket server = new Socket(hostname, port);
			System.out.println("Connection Success!");
			ReceiveThread t1 = new ReceiveThread(server, -1, false);
			t1.start();
			
			SendThread t2 = new SendThread(server);
			t2.start();
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// At this point in (wall-clock) time, both server
		// and client have a Socket object, which is their
		// endpoint of the connection
		
	}

	
}
