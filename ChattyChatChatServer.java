import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChattyChatChatServer 
{
	static Vector<ReceiveThread> clientList = new Vector<>();

	static int clientNumber = 0;
	
	public static void main(String[] args) 
	{
		
		int port = Integer.parseInt(args[0]);
		System.out.println("Server opened on port: " + port);
		
		ServerSocket listener = null;
		
		try 
		{
			// Create ServerSocket, bind to given port
			listener = new ServerSocket(port);
			
			while (true)
			{
				// Listen for connection; return when one occurs
				// Returns a Socket representing the connection
				Socket client = listener.accept();
				
				ReceiveThread t1 = new ReceiveThread(client,clientNumber,true);
				t1.start();
				clientList.add(t1);
				clientNumber++;
				//SendThread t2 = new SendThread(client);
				//t2.start();
			}
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				listener.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}


	}

   
	
	
	
		

	// reg chat

}

