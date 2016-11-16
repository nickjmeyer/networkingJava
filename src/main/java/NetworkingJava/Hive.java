package NetworkingJava;

import java.net.*;
import java.io.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public abstract class Hive {
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
    protected abstract void on_accept(Connection conn);


    /**
     * Connect to remote
     * @param conn: the new connection
     */
    public final void connect(Connection conn) {
        conn_set.add(conn);
        this.on_accept(conn);
    }


    /**
     * Callback for connect().  Override this method if additonal
     * actions are needed upon creating a new connection.
     * @param conn: the new connection
     */
    protected abstract void on_connect(Connection conn);


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
    protected abstract void on_recv(Connection conn, byte[] buf);


    /**
     * Send data to the connection
     * @param conn: a connection to a remote host
     * @param buf: a byte array of outgoing data
     */
    public final void send(Connection conn, byte[] buf) throws IOException {
        conn.write(buf);
        this.on_send(conn,buf);
    }


    /**
     * Callback for send().  Override this method if additional
     * actions are needed upon sending data.
     * @param conn: a connection to a remote host
     * @param buf: a byte array of outgoing data
     */
    protected abstract void on_send(Connection conn, byte[] buf);


    /**
     * Close down all connections.
     */
    public void close() {
        Iterator<Connection> it = this.conn_set.iterator();
        while (it.hasNext()) {
            it.next().close();
            it.remove();
        }

    }
}
