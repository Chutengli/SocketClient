import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketClient {

	public static void main(String[] args) {
		if (args.length < 1) return;

		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		List<String> inputList = new ArrayList<>();
		for(int i = 2; i < args.length; i++) {
			inputList.add(args[i]);
		}

		String input = String.join(" ", inputList);
		try {
			Socket socket = new Socket(hostname, port);
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			writer.println(input);

			InputStream socketInputStream = socket.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(socketInputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			socket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}

