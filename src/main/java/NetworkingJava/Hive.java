package NetworkingJava;

import java.net.*;
import java.io.*;

import java.util.Set;
import java.util.HashSet;

public class Hive {
    Set<Connection> conn_set = new HashSet<Connection>();

    /**
     * Accept a new connection.
     * @param conn: the new connection
     */
    public final void accept(Connection conn) {
        conn_set.add(conn);
        this.on_accept(conn);
    }

    /**
     * Callback for accept().  Override this method if additonal
     * actions are needed upon accepting a new connection.
     * @param conn: the new connection
     */
    protected void on_accept(Connection conn) {
        System.out.println("Accepted connection with " + conn.remote());
    }

    /**
     * Receive data from the connection
     * @param conn: a connection to a remote host
     * @param buf: a byte array of incoming data
     */
    public final void recv(Connection conn, byte[] buf) {
        this.on_recv(conn,buf);
    }

    /**
     * Callback for recv().  Override this method if additional
     * actions are needed upon sending data.
     * @param conn: a connection to a remote host
     * @param buf: a byte array of incoming data
     */
    protected void on_recv(Connection conn, byte[] buf) {
        System.out.println("Recevied bytes from " + conn.remote());
    }

    /**
     * Send data to the connection
     * @param conn: a connection to a remote host
     * @param buf: a byte array of outgoing data
     */
    public final void send(Connection conn, byte[] buf) {
        this.on_send(conn,buf);
    }

    /**
     * Callback for send().  Override this method if additional
     * actions are needed upon sending data.
     * @param conn: a connection to a remote host
     * @param buf: a byte array of outgoing data
     */
    protected void on_send(Connection conn, byte[] buf) {
        System.out.println("Sent bytes to " + conn.remote());
    }
}
