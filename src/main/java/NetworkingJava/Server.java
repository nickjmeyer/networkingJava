package NetworkingJava;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String [] args) {
        Hive hive = new EchoHive();
        try {
            InetAddress address;
            address = InetAddress.getByName("127.0.0.1");
            Acceptor acceptor = new Acceptor(hive,7777,50,address);

            acceptor.start();

            int N = 20;
            for (int i = 0; i < N; i++) {
                Thread.sleep(1000);
            }
            acceptor.close();
            acceptor.join();
            hive.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            hive.close();
        }
    }
}

// public class Server extends Thread {
//     private ServerSocket serverSocket;

//     public Server(int port) throws IOException {
//         serverSocket = new ServerSocket(port);
//         // serverSocket.setSoTimeout(10000);
//     }

//     public void run() {
//         while(true) {
//             try {
//                 System.out.println("Waiting for client on port " +
//                         serverSocket.getLocalPort() + "...");
//                 Socket server = serverSocket.accept();

//                 System.out.println("Just connected to "
//                         + server.getRemoteSocketAddress());
//                 DataInputStream in = new DataInputStream(
//                         server.getInputStream());

//                 System.out.println(in.readUTF());
//                 DataOutputStream out = new DataOutputStream(
//                         server.getOutputStream());
//                 out.writeUTF("Thank you for connecting to "
//                         + server.getLocalSocketAddress()
//                         + "\nGoodbye!");
//                 server.close();

//             }catch(SocketTimeoutException s) {
//                 System.out.println("Socket timed out!");
//                 break;
//             }catch(IOException e) {
//                 e.printStackTrace();
//                 break;
//             }
//         }
//     }

//     public static void main(String [] args) {
//         int port;
//         if (args.length > 0)
//             port = Integer.parseInt(args[0]);
//         else
//             port = 7777;

//         try {
//             Thread t = new Server(port);
//             t.start();
//         }catch(IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
