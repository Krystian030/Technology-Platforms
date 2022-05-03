package Jandy.Krystian;

import Jandy.Krystian.controller.ConnectionManagement;
import Jandy.Krystian.controller.thread_server.IncomingSocketConnectionHandler;

public class ServerComponent {

    public static void main(String[] args){
        Thread serverThread = new Thread(new IncomingSocketConnectionHandler(8080));
        ConnectionManagement.setServerThread(serverThread);
        serverThread.start();
    }
}
