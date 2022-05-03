package Jandy.Krystian.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

import Jandy.Krystian.protocol.Message;
import lombok.extern.java.Log;
import lombok.Setter;
import lombok.Getter;

@Log
@Getter
@Setter
public class ConnectionManagement {

    @Setter
    private static Thread serverThread;

    public static void writeMessage(String msg, BufferedWriter output) {
        try {
            output.write(msg);
            output.newLine();
            output.flush();
        }
        catch (IOException ex) {
            log.log(Level.WARNING,ex.getMessage(),ex);
        }
    }

    public static void sendObjectString(String objectString, ObjectOutputStream output) {
        try {
            output.writeObject(objectString);
            output.flush();
        }
        catch (IOException ex) {
            log.log(Level.WARNING,ex.getMessage(),ex);
        }
    }

     public static String readObjectString(ObjectInputStream inputStream) {
         try {
             return (String) inputStream.readObject();
         } catch (IOException ex) {
             log.log(Level.WARNING, ex.getMessage(), ex);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         return null;
     }

    public static void sendObjectMessage(Message objectMessage, ObjectOutputStream output) {
        try {
            output.writeObject(objectMessage);
            output.flush();
        }
        catch (IOException ex) {
            log.log(Level.WARNING,ex.getMessage(),ex);
        }
    }

    public static Message readObjectMessage(ObjectInputStream inputStream) {
        try {
            return (Message) inputStream.readObject();
        } catch (IOException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendObjectInteger(Integer objectInteger, ObjectOutputStream output) {
        try {
            output.writeObject(objectInteger);
            output.flush();
        }
        catch (IOException ex) {
            log.log(Level.WARNING,ex.getMessage(),ex);
        }
    }
    public static void sendObjectIntegerArr(Integer[] objectInteger, ObjectOutputStream output) {
        try {
            output.writeObject(objectInteger);
            output.flush();
        }
        catch (IOException ex) {
            log.log(Level.WARNING,ex.getMessage(),ex);
        }
    }
    public static Integer readObjectInteger(ObjectInputStream inputStream) {
        try {
            return (Integer) inputStream.readObject();
        } catch (IOException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void endConnection(){
        serverThread.interrupt();
    }

}
