package NetworkingJava;

import java.io.UnsupportedEncodingException;

class EchoHive extends Hive {
    protected void on_accept(Connection conn) {
        System.out.println("Accepted from remote " + conn.remote());
    }

    protected void on_recv(Connection conn, byte[] buf) {
        try {
            System.out.println("Received message: " + new String(buf,"UTF-8"));
        } catch(UnsupportedEncodingException e) {
            System.out.println("Received message with unsupported encoding");
        }
    }

    protected void on_send(Connection conn, byte[] buf) {
        try {
            System.out.println("Sent message: " + new String(buf,"UTF-8"));
        } catch(UnsupportedEncodingException e) {
            System.out.println("Sent message with unsupported encoding");
        }
    }

    protected void on_connect(Connection conn) {
        System.out.println("Connected to remote " + conn.remote());
    }
}
