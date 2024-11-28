/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper;

import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 *
 * @author AREZDEV
 */
public class Websoket_ku {
    
    public static String user = "";
    public static String pass = "";
    public static String data_info;
    
    public static class saveFB extends WebSocketClient {

    public saveFB(URI serverUri) {
       super(serverUri);        
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
       System.out.println("Connected");
       // do whatever        
    }

    @Override
    public void onMessage(String message) {
       System.out.println("got: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
       System.out.println("Disconnected");
    }

    @Override
    public void onError(Exception ex) {
    }
   }
    
    public static class getFB extends WebSocketClient {

    public getFB(URI serverUri) {
       super(serverUri);        
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
       System.out.println("Connected");
       // do whatever        
    }

    @Override
    public void onMessage(String message) {
       String dataFB = message;
       data_info = message;
        user = dataFB.split("\\|")[0];
        pass = dataFB.split("\\|")[1];
       System.out.println(user);
       System.out.println(pass);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
       System.out.println("Disconnected");
    }

    @Override
    public void onError(Exception ex) {
    }
   }
    
}
