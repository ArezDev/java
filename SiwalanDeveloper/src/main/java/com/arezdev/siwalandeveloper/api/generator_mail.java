/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 *
 * @author AREZDEV
 */
public class generator_mail {
    
    public static void main(String[] args) {
        try {
//            String dataEmail = g(3,0) + g(0,5) + "@" + "tesel.xyz";
//            ArezDev_DATA.writeMail("emulator-5554", dataEmail);
//            StringBuilder data = new StringBuilder();
//            URL urlx = new URL("https://generator.email/");
//                HttpURLConnection httpx = (HttpURLConnection) urlx.openConnection();
//                httpx.setRequestMethod("GET");
//                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpx.getInputStream()))) {
//                    for (String line; (line = reader.readLine()) != null; ) {
//                        data.append(line);
//                   }
//                }
//                System.out.println(data);
            getCode("emulator-5554");
        } catch (Exception e) {
        }
    }
    
    public static void getCode(String device) {
        
        try {
            BufferedReader bfr;
            bfr = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + device + ".txt"));
                StringBuilder data = new StringBuilder();
                URL urlx = new URL("https://generator.email/"+bfr.readLine());
                    HttpURLConnection httpx = (HttpURLConnection) urlx.openConnection();
                    httpx.setRequestMethod("GET");
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpx.getInputStream()))) {
                        for (String line; (line = reader.readLine()) != null; ) {
                            data.append(line);
                       }
                    }
                    System.out.println(data);
        } catch (Exception e) {
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
