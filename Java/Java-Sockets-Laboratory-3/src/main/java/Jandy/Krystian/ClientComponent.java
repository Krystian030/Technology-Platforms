package Jandy.Krystian;

import Jandy.Krystian.controller.client.ClientController;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;

import lombok.extern.java.Log;

@Log
public class ClientComponent {
    public static void main(String[] args) throws IOException {
        ClientController clientController = new ClientController(8080, "localhost");

    }
}
