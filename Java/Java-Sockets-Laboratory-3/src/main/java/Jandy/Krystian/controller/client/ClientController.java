package Jandy.Krystian.controller.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.Random;

import Jandy.Krystian.protocol.Message;
import static Jandy.Krystian.controller.ConnectionManagement.*;

import lombok.extern.java.Log;
import lombok.Getter;
import lombok.Setter;

@Log
@Getter
@Setter
public class ClientController {

    private Scanner scanner = new Scanner(System.in);
    private Integer msgNumber;
    private Integer[] numberRandom = new Integer[10];
    Random random = new Random();
    public void sendMsg(String message, BufferedWriter output) {
        try {
            output.write(message);
            output.newLine();
            output.flush();
        } catch (IOException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    private boolean serverReadiness(String msg, ObjectOutputStream objectOutput) {
        if (msg.equals("ready")) {
            log.info("Enter the number");
            msgNumber = Integer.valueOf(scanner.next());
            for (int i=0; i<10;i++){
                numberRandom[i] = random.nextInt(msgNumber);
            }
            sendObjectInteger(msgNumber, objectOutput);
            sendObjectIntegerArr(numberRandom,objectOutput);
            return true;
        } else {
            log.warning("Error: Server is not ready");
            return false;
        }
    }

    private boolean sendMessages(ObjectOutputStream objectOutput, ObjectInputStream objectInput) throws IOException {
        String msg = readObjectString(objectInput);
        if (msg.equals("ready for messages")) {
            log.info("Server: " + msg);
            Message tmpMessage;
            for (int i = 0; i < this.msgNumber; i++) {
                tmpMessage = new Message();
                System.out.println("Enter value: ");
                tmpMessage.setNumber(Integer.parseInt(scanner.next()));
                System.out.println("Enter content: ");
                tmpMessage.setContent(scanner.next());
                objectOutput.writeObject(tmpMessage);

            }
            objectOutput.flush();
            return true;
        }
        else {
            log.warning("Error");
            return false;
        }
    }



    private boolean serverEnding(ObjectInputStream objectInput) {
        String msg = readObjectString(objectInput);
        if (msg.equals("finished")) {
            log.info("Connection is finished");
            return true;
        }
        else {
            log.warning("Error: Server not finish connection");
            return false;
        }
    }

    public int serviceClient(Socket client,ObjectOutputStream objectOutput,ObjectInputStream objectInput) throws IOException {
        objectOutput.flush();
        if (!serverReadiness(readObjectString(objectInput),objectOutput)) return -1;
        if (!sendMessages(objectOutput,objectInput)) return -1;
        if (!serverEnding(objectInput)) return -1;
        return 1;
    }

    public ClientController(int socketNumber, String server) {

        try (Socket client = new Socket(server,socketNumber)){

            try ( ObjectOutputStream objectOutput = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
                  ObjectInputStream objectInput = new ObjectInputStream(client.getInputStream());
                  BufferedWriter output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                  BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()))
            )
            {
               if(serviceClient(client,objectOutput,objectInput) > 0) {
                   log.info("Message(s) has sent.");
               }
               else {
                   log.warning("Error");
               }
                log.info("If you want close the server enter the 0, else enter other value to end your connection!");
                sendObjectInteger(Integer.valueOf(scanner.nextInt()),objectOutput);
            }

        } catch (IOException ex) {
            log.log(Level.WARNING,ex.getMessage(),ex);
        }
    }

}
