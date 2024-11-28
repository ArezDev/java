/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AREZDEV
 */
public class mailnesia {
    
    public static void main(String[] args) {
        start("emulator-5580");
    }
    
    public static void start(String device) {
        
        try {
            //create mail
            ArezDev_DATA.writeMail(device, g(3, 5)+"@mailnesia.com");
            Thread.sleep(4000);
            
            //save email
            BufferedReader GetEmail;
            GetEmail = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + device + ".txt"));
            String email = GetEmail.readLine();
            confirm_next.changeMail(device, email);
            Thread.sleep(6000);
            
            //get email
            readMail(email, device);
            Thread.sleep(4000);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println(e);
        }
        
    }
    
    public static void changeEmail(String device, String email) {
        
            confirm_next.changeMail(device, email);
        
    }
    
    public static void readMail(String email, String device) throws URISyntaxException, InterruptedException {
        
        String code_fb = "";
        
        try {
            StringBuilder data = new StringBuilder();
            URL urlx = new URL("https://mailnesia.com/mailbox/" + email.split("@mailnesia.com")[0]);
                HttpURLConnection httpx = (HttpURLConnection) urlx.openConnection();
                httpx.setRequestMethod("GET");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpx.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        data.append(line);
                   }
                    Pattern findCode = Pattern.compile("title=\"Open email\">\\d+");
                    Matcher code = findCode.matcher(data);
                    if(code.find()){
                        code_fb = code.group(0).split("title=\"Open email\">")[1];
                        System.out.println(device + "code found! => " + code_fb);
                        Thread.sleep(2000);
                        confirm_next.verify(device, code_fb);
                    }
                    if(code_fb.isEmpty()){ 
                        System.out.println(device + "code not found !");
                        Thread.sleep(2000);
                        adb.clearAPP(device, "com.facebook.lite");
                        Thread.sleep(5000);
                        adb.closeApp(device, "com.windscribe.vpn");
                        Thread.sleep(5000);
                    }
                }
                //System.out.println(data);
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }
    
    private static String g(int length, int num){
            String textRandom = "abcdefghijklmnopqrstuvwxyz";
            String numberRandom = "0987654321";
            StringBuilder sb = new StringBuilder ();
            StringBuilder nb = new StringBuilder ();
            Random random = new Random ();
            for (int i = 0; i < length; i ++) {
                sb.append (textRandom.charAt (random.nextInt (textRandom.length())));
            }
            for (int i = 0; i < num; i ++) {
                nb.append (numberRandom.charAt (random.nextInt (numberRandom.length())));
            }
            return sb.toString()+nb.toString();
    }
    
}
