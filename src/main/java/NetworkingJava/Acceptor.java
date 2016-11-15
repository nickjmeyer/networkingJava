package NetworkingJava;

import java.net.*;
import java.io.*;

public class Acceptor extends Thread {
    protected boolean open = true;
    protected Hive hive;
    protected ServerSocket server;

    public Acceptor(Hive hive, int port, int backlog, InetAddress address) {
        this.hive = hive;
        this.server = new ServerSocket(port,backlog,address);

        try {
            this.server.setSoTimeout(500);
        } catch (SocketException e) {
            if (!this.server.isClosed()) {
                this.server.close();
            }
        }
    }

    public void run() {
        while(open) {
            try {
                Socket sock = this.server.accept();
                Connection conn = new Connection(this.hive,sock);
                this.hive.accept(conn);
            } catch (SocketTimeoutException e) {
            } catch (SocketException e) {
                e.printStackTrace();
                this.server.close();
            } catch (Exception e) {
                e.printStackTrace();
                this.server.close();
            }
        }
    }

    public void close() {
        this.open = false;
    }
}
