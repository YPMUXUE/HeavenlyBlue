package priv.muxue;

import priv.muxue.connector.Connector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Bootstrap {
    public static void main(String[] args) throws Exception {
        Connector connector=Connector.getInstance();
        new Thread(connector).start();
    }
}
