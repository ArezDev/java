/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import com.arezdev.siwalandeveloper.Websoket_ku;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author AREZDEV
 */
public class tesh {
    public static void main(String[] args) {
        
//        try {
//            BufferedReader njupokFile;
//               njupokFile = new BufferedReader(new FileReader("connect_device.txt"));
//                String isiFile = njupokFile.readLine();
//                while((isiFile = njupokFile.readLine()) != null) {
//                            System.out.println(isiFile);
//                }
//        } catch (IOException e) {
//            System.err.println(e);
//        }

        //adb.startDevices();
        System.out.println(System.getProperty("user.dir"));
        
    }
    
    public static void api(String method, String baseURL, String body, StringBuilder respons ) {
        try {
            respons = new StringBuilder();
            URL url = new URL(baseURL);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);
            if(method.contains("GET")){
                System.out.println("GET http!");
            } else {
                System.out.println("POST http!");
            String datajson = body;
                try(OutputStream os = http.getOutputStream()) {
                    byte[] input = datajson.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    respons.append(line);
               }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public static class MyTask implements Runnable {
        PrintStream out;
        public MyTask(final PrintStream out) {
            this.out = out;
        }
        public void run() {
            out.println("MyTask executed in : " + Thread.currentThread().getName());
        }
    }

}