package Jandy.Krystian.controller.thread_server;

import java.io.*;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import Jandy.Krystian.controller.ConnectionManagement;
import Jandy.Krystian.protocol.Message;
import lombok.extern.java.Log;

import static Jandy.Krystian.controller.ConnectionManagement.*;


@Log
public class SocketConnectionHandler implements Runnable {

    private final Socket socket;
    private ArrayList<Message> messages;
    private Integer[] numberRandom;

    public SocketConnectionHandler(Socket socket){
        this.socket = socket;

        this.messages = new ArrayList<>();
    }

    private void printMessage() {
        int msgNum = 1;
        for(Message msg : messages) {
            System.out.println("======================= " + String.valueOf(msgNum) + " =======================" );
            System.out.println(msg);
            System.out.println("=================================================");
            System.out.println();
            msgNum += 1;
        }
    }

    private boolean messageService(Socket client,ObjectOutputStream objectOutput,ObjectInputStream objectInput) throws IOException, ClassNotFoundException {
        Integer n = (Integer)objectInput.readObject();
        numberRandom = (Integer[])objectInput.readObject();
        if (n != null) {
            sendObjectString("ready for messages", objectOutput);
        }
        Message tmp;

        // Get messages
        for(int i=0; i < n; i++){
          if( (tmp = readObjectMessage(objectInput)) != null){
            messages.add(tmp);
          }
          else return false;
        }
        return true;
    }
    void printNumber(){
        for(int i=0; i<10;i++){
            System.out.println(numberRandom[i]);
        }
    }
    @Override
    public void run() {
        try (
             ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        )
        {
            log.info("Server start connection");
            sendObjectString("ready",objectOutput);    //  Wysłanie obiektu String o stałej wartości ready
            if (messageService(socket,objectOutput,objectInput)) {
                printMessage();
                sendObjectString("finished", objectOutput);
            }
            printNumber();
            if (readObjectInteger(objectInput) == 0) {
                endConnection();    //  Zakończenie głównego wątku nasłuchującego na połączenie
            }


            //endConnection();    //  Zakończenie głównego wątku nasłuchującego na połączenie

        } catch (IOException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
