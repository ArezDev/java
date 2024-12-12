/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import com.arezdev.siwalandeveloper.CookiesFB;
import static com.arezdev.siwalandeveloper.Settings.aku;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author AREZDEV
 */
public class email_10menit {
    
    public static void getMail(String devices) throws URISyntaxException {
        
    String mail_10menit = "";
    String session_10menit = "";
    session_10menit = gens(15);
        
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://10minutemail.net/address.api.php?&sessionid="+session_10menit);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        result.append(line);
                    }
                    JSONObject js = new JSONObject(result.toString());
                    System.out.println(result.toString());
                    System.out.println("device " + devices + " email => " + js.getString("mail_get_mail"));
                    mail_10menit = js.getString("mail_get_mail");
                    Thread.sleep(2000);
                    try (FileWriter writeMail = new FileWriter( aku + "\\data\\email\\email-" + devices.split(":")[1] + ".txt"); 
                    BufferedWriter typeMail = new BufferedWriter(writeMail)) {
                    typeMail.write(mail_10menit);
                    }
                    Thread.sleep(2000);
                    confirm_next_mumuplayer.changeMail(devices, mail_10menit);                            
                    //wait get code
                    //Thread.sleep(15000);   
                    //get code
                    getCode(session_10menit, devices);
                }
        } catch (IOException | InterruptedException | JSONException e) {
            System.err.println(e);
        }
    }
    
    public static void getCode(String sessionId, String devices) throws URISyntaxException {
        String codeFB = "";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://10minutemail.net/address.api.php?sessionid="+sessionId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        result.append(line);
                    }
                    //JSONObject js = new JSONObject(result.toString());
                    //System.out.println(result.toString());
                    //System.out.println("get Code devices : " + devices);
                    //System.out.println(js.getString("session_id"));
                    //System.out.println(js.getString("mail_get_mail"));
//                    JSONArray codeMail = new JSONArray(js.getJSONArray("mail_list"));
//                    JSONObject mail_code = new JSONObject(codeMail.get(0).toString());
//                    codeFB = mail_code.getString("subject").split(" ")[0];

                    JSONObject js = new JSONObject(result.toString());
                    String mail_10menit = js.getString("mail_get_mail");
                    JSONArray codeMail = new JSONArray(js.getJSONArray("mail_list"));
                    for (int i = 0; i < 1; i++) {
                        JSONObject jsk = new JSONObject(codeMail.get(i).toString());
                        if(jsk.getString("from").contains("registration")){
                            codeFB = jsk.getString("subject").split(" ")[0];
                            System.out.println("\n get kode => " + devices + " : " + codeFB);  
                            System.out.println(jsk);
                            //System.out.println(jsk.getString("subject").split(" ")[0]);
                            //Nextconfirm(mail_10menit, devices, codeFB);
                            confirm_next_mumuplayer.verify(devices, codeFB);
                        } else if(codeFB.isEmpty()){  
                            System.out.println("\n get kode => " + devices + " : gagal !!!");   
                            //wait
                            Thread.sleep(2000);
                            adb.clearAPP(devices, "com.facebook.lite");
                            //wait
                            Thread.sleep(5000);
                            adb.closeApp(devices, "com.windscribe.vpn");
                            //wait
                            Thread.sleep(2000);
                        }
                    }         
                }
        } catch (IOException | InterruptedException | JSONException e) {
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
