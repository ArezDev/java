/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import static com.arezdev.siwalandeveloper.Settings.aku;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author AREZDEV
 */
public class tempmailio {
    
    public static void start(String device) {
        String em = "";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://api.internal.temp-mail.io/api/v3/email/new");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            String datajson = "{\"min_name_length\":10,\"max_name_length\":10}";
                try(OutputStream os = http.getOutputStream()) {
                    byte[] input = datajson.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(http.getInputStream()))) {
                        for (String line; (line = reader.readLine()) != null; ) {
                            result.append(line);
                        }
                }
                System.out.println(result);
                JSONObject emails = new JSONObject(result.toString());
                em = emails.getString("email");
                try (FileWriter writeMail = new FileWriter( aku + "\\data\\email\\email-" + device.split(":")[1] + ".txt"); 
                    BufferedWriter typeMail = new BufferedWriter(writeMail)) {
                    typeMail.write(em);
                } 
                Thread.sleep(2000);
                confirm_next_mumuplayer.changeMail(device, em);
                getCode(em, device);
            } catch (IOException | InterruptedException | JSONException e) {
                System.err.println(e);
        }
    }
    
    public static void getCode(String mail, String device) {
        
        System.out.println("start get code device => " + device);
        
        String codeFB = "";
        
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://api.internal.temp-mail.io/api/v3/email/" + mail + "/messages");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        result.append(line);
                    }
            }
            System.out.println(result + "\n");
            Pattern findCode = Pattern.compile("subject\":\"\\d+");
            Matcher code = findCode.matcher(result);
            if(code.find()){
                codeFB = code.group(0).split("subject\":\"")[1];
                System.out.println(device + " code sukses => " + codeFB + "\n");
                confirm_next_mumuplayer.verify(device, codeFB);
            }
            if (codeFB.isEmpty()) {
            System.out.println(device + " code gagal !!!" + "\n");    
                //wait
                    Thread.sleep(4000); 
                    
                    adb.closeApp(device,"com.windscribe.vpn");    
                //wait
                    Thread.sleep(5000); 
                    
                    adb.clearAPP(device,"com.facebook.lite");
                //wait
                    Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println(e);
        }
        
    }
    
}
