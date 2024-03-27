package com.example.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static  ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUserName;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // send
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // read
            this.clientUserName = bufferedReader.readLine();
            clientHandlers.add(this);

        } catch (Exception e) {

        }
    }

    @Override
    public void run() {

    }
}
