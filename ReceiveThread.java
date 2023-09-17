import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;



public class ReceiveThread extends Thread
{
	private Socket socket;
    private int clientNum;
    private String nickname;
    private PrintWriter out;
    boolean isServer;
    
	public ReceiveThread(Socket socketin, int cNum, boolean isServerSelf) 
	{
		socket = socketin;
		clientNum = cNum;
		nickname = "Person " + clientNum;
		out = null;
		isServer = isServerSelf;
	}
	
	
	@Override
	public void run() 
	{
		try 
		{
			BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream() ) );
            out = new PrintWriter(socket.getOutputStream(), true);
           
            if(isServer)
            {
            	out.println("Welcome to the server, you are: " + nickname);
                out.println("Type /quit to quit.");
            }


			while (true)
			{
				String msg = sIn.readLine();
				if (msg == null)
				{
					break;
				}
				
				if (isServer)
				{
					String[] command = msg.split(" ", 3);
					
					// NICKNAME
					if (command[0].equals("/nick") )
					{
                        String oldNickname = nickname;
						nickname = command[1];
						for (ReceiveThread client : ChattyChatChatServer.clientList) 
                        {
							if (clientNum != client.clientNum)
							{
								client.out.println(oldNickname + " has changed their name to " + nickname + ".");
							}
                        }
                        System.out.println(oldNickname + " changed to " + nickname);
					}
					// DIRECT MESSAGE
					else if (command[0].equals("/dm") )
					{
	                	String sendTo = command[1];
	                	String sendMsg = command[2];
	                	
	                	for (ReceiveThread client : ChattyChatChatServer.clientList) 
	                    {
	                		if(client.nickname.equals(sendTo))
	                		{
	                			client.out.println(nickname + " [DM]: " + sendMsg);
	                		}
	                    }
                	}
					// QUIT
					else if (command[0].equals("/quit") )
					{
						for (ReceiveThread client : ChattyChatChatServer.clientList) 
	                    {
	                        client.out.println(nickname + " has left the chat.");
	                    }
	                    break;				
					}
					else
					{
						for (ReceiveThread client : ChattyChatChatServer.clientList)
						{
							if (clientNum != client.clientNum)
							{
								client.out.println(nickname + ": " + msg);
							}
						}
					}
					
		
				}//end of if 
				else
				{
					System.out.println(msg);
				}
			} // end of while
			System.out.println("Disconnected from partner");
		} // end of try
		

		catch (IOException e) 
		{
            System.out.println("Error while talking to " + nickname);
        } 
		finally 
		{
            try 
            {
                socket.close();
            } 
            catch (IOException e) 
            {
            	if (isServer)
            	{
            		System.out.println("Error closing socket with " + nickname);
            	}
            	else
            	{
            		System.out.println("Error closing socket with server");
            	}
            } 
            finally 
            {
            	if (isServer)
            	{
            		System.out.println("Connection to " + nickname + " closed");
            	}
            	else
            	{
            		System.out.println("Connection to server closed");
            	}
            }
		}
	}
	
}
