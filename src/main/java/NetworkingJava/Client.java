package NetworkingJava;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String [] args) {
        Hive hive = new EchoHive();
        try {
            InetAddress address = InetAddress.getByName("localhost");
            Connection conn = Connection.connect_to(hive,address,7777);

            int N = 5;
            for (int i = 0; i < N; i++) {
                String msg = "Message " + i;
                hive.send(conn,msg.getBytes("UTF-8"));
                Thread.sleep(500);
            }
            conn.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// public class Client {

//     public static void main(String [] args) {
//         String serverName = "127.0.0.1";
//         int port;
//         if (args.length > 0)
//             port = Integer.parseInt(args[0]);
//         else
//             port = 7777;
//         try {
//             System.out.println("Connecting to " + serverName
//                     + " on port " + port);
//             Socket client = new Socket(serverName, port);

//             System.out.println("Just connected to "
//                     + client.getRemoteSocketAddress());
//             OutputStream outToServer = client.getOutputStream();
//             DataOutputStream out = new DataOutputStream(outToServer);

//             out.writeUTF("Hello from " + client.getLocalSocketAddress());
//             InputStream inFromServer = client.getInputStream();
//             DataInputStream in = new DataInputStream(inFromServer);

//             System.out.println("Server says " + in.readUTF());
//             client.close();
//         }catch(IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
