package NetworkingJava;

import java.net.*;
import java.io.*;

public class Acceptor extends Thread {
    protected boolean open = true;
    protected Hive hive;
    protected ServerSocket server;

    public Acceptor(Hive hive, int port, int backlog, InetAddress address) {
        this.hive = hive;

        // start server
        try {
            this.server = new ServerSocket(port,backlog,address);
        } catch (IOException e) {
            e.printStackTrace();
            this.close();
        }

        // set timeout
        try {
            this.server.setSoTimeout(500);
        } catch (SocketException e) {
            e.printStackTrace();
            this.close();
        }
    }

    public void run() {
        while(open) {
            try {
                Socket sock = this.server.accept();
                Connection conn = new Connection(this.hive,sock);
                conn.start();
                this.hive.accept(conn);
            } catch (SocketTimeoutException e) {
            } catch (SocketException e) {
                if(this.open) {
                    e.printStackTrace();
                    this.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.close();
            }
        }
    }

    public void close() {
        this.open = false;
        try {
            this.server.close();
        } catch (IOException e) {
            // server is closing so ignore any io error
        }
    }
}
