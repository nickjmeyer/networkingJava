package NetworkingJava;

import java.net.*;
import java.io.*;

public class Connection extends Thread {
    protected Hive hive;
    protected boolean open = true;
    protected Socket sock;

    public Connection(Hive hive, Socket sock) {
        this.hive = hive;
        this.sock = sock;

        try {
            this.sock.setSoTimeout(500);
        } catch (SocketException e) {
            if (!this.sock.isClosed()) {
                this.sock.close();
            }
            this.close();
        }
    }

    public void run() {
        while(open) {
            try {
                InputStream is = this.sock.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                byte[] buf = new byte[dis.available()];
                dis.readFully(buf);

                hive.recv(this, buf);

            } catch (SocketException e) {
                e.printStackTrace();
                this.close();
            } catch (SocketTimeoutException e) {
            } catch (IOException e) {
                e.printStackTrace();
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
                this.close();
            }
        }
    }

    public void close() {
        this.open = false;
        if (!this.sock.isClosed()) {
            try {
                this.sock.close();
            } catch(Exception e) {
                // closing socket so doesn't matter if there is an error
            }
        }
    }

    public SocketAddress remote() {
        return sock.getRemoteSocketAddress();
    }
}
