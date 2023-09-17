import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread
{
	Socket socket;
	SendThread(Socket socketin)
	{
		socket = socketin;
	}
	
	@Override
	public void run() 
	{
		Scanner scanner = null;
		try 
		{
			PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true );

			scanner = new Scanner(System.in);
			while (true)
			{
				System.out.print(">"); // implement this to start every message
				String msg = scanner.nextLine();
				sOut.println(msg);
				
				if (msg.equals("/quit") )
				{
					break;
				}
			}

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			scanner.close();
		}
	}
}
