/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author AREZDEV
 */
public class mailtm {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        start("emulator-5554");
//        String devices = "emulator-5554";
//        BufferedReader bfr;
//        bfr = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + devices + ".txt"));
//        String email = bfr.readLine();
//        System.out.println(email);
        
    }
    
    public static void start(String device) {
        try {
            
            StringBuilder em = new StringBuilder();
            URL urlx = new URL("https://api.mail.tm/domains");
                HttpURLConnection httpx = (HttpURLConnection) urlx.openConnection();
                httpx.setRequestMethod("GET");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpx.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        em.append(line);
                   }
                }
            JSONObject domainGet = new JSONObject(em.toString());
            JSONArray domain_ = new JSONArray(domainGet.getJSONArray("hydra:member"));
            JSONObject domain_iki = new JSONObject(domain_.get(0).toString());
            createMail(gens(12)+"@", domain_iki.get("domain").toString(), device);
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public static void getToken(String mail) {
        try {
            StringBuilder em = new StringBuilder();
            URL url = new URL("https://api.mail.tm/token");
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setRequestProperty ("Content-Type", "application/json");
                http.setRequestProperty("Accept", "application/json");
                http.setDoOutput(true);
                String datajson = "{\"address\":\""+mail+"\",\"password\":\"TEORINGELID\"}";
                    try(OutputStream os = http.getOutputStream()) {
                        byte[] input = datajson.getBytes("utf-8");
                        os.write(input, 0, input.length);
                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        em.append(line);
                   }
                }
                System.out.println(em);
                JSONObject tokenget = new JSONObject(em.toString());
                System.out.println(tokenget.getString("token"));
                //getMessage(tokenget.getString("token"));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public static void createMail(String user, String domain, String device) {
        try {
            StringBuilder em = new StringBuilder();
            URL urlx = new URL("https://api.mail.tm/accounts");
                HttpURLConnection httpx = (HttpURLConnection) urlx.openConnection();
                httpx.setRequestMethod("POST");
                httpx.setRequestProperty ("Content-Type", "application/json");
                httpx.setRequestProperty("Accept", "application/json");
                httpx.setDoOutput(true);
                String datajson = "{\"address\":\""+user+domain+"\",\"password\":\"TEORINGELID\"}";
                    try(OutputStream os = httpx.getOutputStream()) {
                        byte[] input = datajson.getBytes("utf-8");
                        os.write(input, 0, input.length);
                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpx.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        em.append(line);
                   }
                }
                System.out.println(em);
                JSONObject emailget = new JSONObject(em.toString());
                try (FileWriter writeMail = new FileWriter("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + device +".txt"); 
                BufferedWriter typeMail = new BufferedWriter(writeMail)) {
                    typeMail.write(emailget.getString("address"));
                }
                getToken(emailget.getString("address"));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public static void getMessage(String token) {
        try {
            StringBuilder em = new StringBuilder();
            URL urlx = new URL("https://api.mail.tm/messages");
                HttpURLConnection httpx = (HttpURLConnection) urlx.openConnection();
                httpx.setRequestMethod("GET");
                httpx.setRequestProperty("Authorization", "Bearer " + token);
                httpx.setDoOutput(true);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpx.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        em.append(line);
                   }
                }
                System.out.println(em);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public static String gens (int length) {
        String textRandom = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder sb = new StringBuilder ();
        Random random = new Random ();
        for (int i = 0; i < length; i ++) {
            sb.append (textRandom.charAt (random.nextInt (textRandom.length ())));
        }
        return sb.toString();
    }
    
}
