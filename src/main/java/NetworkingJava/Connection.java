package NetworkingJava;

import java.net.*;
import java.io.*;

public class Connection extends Thread {
    protected Hive hive;
    protected boolean open = true;
    protected Socket sock;
    protected InputStream is;
    protected DataInputStream dis;
    protected OutputStream os;
    protected DataOutputStream dos;

    public Connection(Hive hive, Socket sock) {
        this.hive = hive;
        this.sock = sock;
        try {
            this.sock.setSoTimeout(500);

            this.is = sock.getInputStream();
            this.dis = new DataInputStream(this.is);
            this.os = sock.getOutputStream();
            this.dos = new DataOutputStream(this.os);
        } catch(IOException e) {
            e.printStackTrace();
            this.close();
        }
    }

    public static Connection connect_to(Hive hive,
            InetAddress address, int port) throws IOException {
        Socket sock = new Socket(address, port);
        Connection conn = new Connection(hive,sock);
        hive.connect(conn);
        conn.start();
        return conn;
    }

    public void run() {
        while(open) {
            try {
                int msg_length = this.dis.readInt();
                byte[] buf = new byte[msg_length];
                this.dis.readFully(buf);

                hive.recv(this, buf);

            } catch (SocketException e) {
                e.printStackTrace();
                this.close();
            } catch (SocketTimeoutException e) {
            } catch (EOFException e) {
                this.close();
            } catch (IOException e) {
                e.printStackTrace();
                this.close();
            }
        }

        try {
            this.sock.close();
        } catch (IOException e) {
            // closing socket so ignore io error
        }
    }

    public void write(byte[] buf) throws IOException {
        dos.writeInt(buf.length);
        dos.write(buf,0,buf.length);
        dos.flush();

    }

    public void close() {
        this.open = false;
        // if (!this.sock.isClosed()) {
        //     try {
        //         this.sock.close();
        //     } catch(IOException e) {
        //         e.printStackTrace();
        //         // closing socket so ignore an io error
        //     }
        // }
    }

    public SocketAddress remote() {
        return sock.getRemoteSocketAddress();
    }
}
