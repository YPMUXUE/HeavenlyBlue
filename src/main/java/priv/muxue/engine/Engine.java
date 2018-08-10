package priv.muxue.engine;

import java.net.Socket;

public interface Engine extends Runnable {
    void accept(Socket socket);
}
