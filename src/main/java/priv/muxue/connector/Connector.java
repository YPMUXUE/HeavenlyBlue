package priv.muxue.connector;

import priv.muxue.Config;

import java.util.concurrent.*;

public interface Connector extends Runnable {
    String CONNECTOR = "defaultConnector";
    String DEFAULT_CONNECTOR = "priv.muxue.connector.SocketConnector";

    static Connector getInstance() throws Exception {
        String className = Config.SYSTEMCONFIG.getConfigMap().getOrDefault(CONNECTOR, DEFAULT_CONNECTOR);
        Class clazz = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
        Connector connector = (Connector) clazz.newInstance();
        return connector;
    }

    static ThreadPoolExecutor getConnectorThreadPool(int core, int max) {
        return new ThreadPoolExecutor(core, max, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10)
                , Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (!executor.isShutdown()) {
                    r.run();
                }
            }
        });
    }
}
