package priv.muxue.connector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author yp
 */
public class SocketConnector implements Connector {
    private static final ExecutorService EXECUTOR_SERVICE = Connector.getConnectorThreadPool(5,10);
    @Override
    public void run() {
        try {
            while (Thread.currentThread().isInterrupted()) {
                ServerSocket server=new ServerSocket();
                Socket socket = server.accept();
            }
        }catch (IOException e){
            close();
        }
    }

    private void close() {
    }
}
