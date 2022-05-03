package Jandy.Krystian.controller.thread_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

import lombok.extern.java.Log;

@Log
public class IncomingSocketConnectionHandler implements Runnable {

    private  ServerSocket serverSocket;


    public IncomingSocketConnectionHandler(int portNumber){

        try {
            serverSocket = new ServerSocket(portNumber);
            serverSocket.setSoTimeout(1000);
        } catch(IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void run() {
        log.info("Starting HTTP Listener");
        while(!Thread.interrupted()) {
            try {
                Socket socket = serverSocket.accept();
                new Thread(new SocketConnectionHandler(socket)).start();  // Obsługa połączenia
            } catch(SocketTimeoutException ex) {
                log.log(Level.FINEST, ex.getMessage(),ex);
            } catch(IOException ex) {
                log.log(Level.WARNING,ex.getMessage(),ex);
            }
        }
        log.info("Stopping HTTP Listener");
    }
}
