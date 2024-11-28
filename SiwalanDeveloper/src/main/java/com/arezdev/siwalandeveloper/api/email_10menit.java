/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import com.arezdev.siwalandeveloper.CookiesFB;
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
                    try (FileWriter writeMail = new FileWriter("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + devices +".txt"); 
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
    private static void Nextconfirm(String mail_10menit, String devices, String codeFB){
        System.out.println(mail_10menit + " => get Code FB sukses : " + codeFB);
        try {
                //wait
                    Thread.sleep(4000);
                //allow kontak
                    adb.klik(devices, "540 734");
                //wait
                    Thread.sleep(5000);
                    
                //klikbox code
                    adb.klik(devices, "343 443");
                //wait
                    Thread.sleep(2000);
                    
                //klikbox code
                    adb.klik(devices, "343 443");

                
                    adb.create("adb -s " + devices + " shell input keyevent 62");
                //wait
                    Thread.sleep(2000);
                //clear
                    adb.create("adb -s " + devices + " shell input keyevent 67");
                    Thread.sleep(25);
                    adb.create("adb -s " + devices + " shell input keyevent 67");
                    Thread.sleep(25);

                //fill code
                    adb.nulis(devices, codeFB);
                //wait
                    Thread.sleep(4000);
                    
                //OK code SignUp !!!
                    adb.klik(devices, "362 538");
                //wait
                    Thread.sleep(9000);
                    
                //get email
                BufferedReader bfr;
                bfr = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + devices + ".txt"));
                String email = bfr.readLine();
                //send FB
                    CookiesFB.getCookies(devices, email);
                //wait
                    Thread.sleep(15000);
                    
            //windscribe        
//                //OFF VPN
//                    adb.openFB("adb -s " + devices + " shell am start -n com.windscribe.vpn/com.windscribe.mobile.windscribe.WindscribeActivity");
//                //wait
//                    Thread.sleep(7000);
//                //klik off vpn
//                    adb.klik(devices, "590 184");

//            //cloudflare           
//                    //OFF VPN
//                            adb.openFB("adb -s " + devices + " shell am start -n com.cloudflare.onedotonedotonedotone/com.cloudflare.app.presentation.main.MainActivity");
//                        //wait
//                            Thread.sleep(7000);
//                        //klik off vpn
//                            adb.klik(devices, "325 580");
//                        //wait
//                            Thread.sleep(4000);
//                        //klik off vpn
//                            adb.klik(devices, "115 800");
                    
                //wait
                    //Thread.sleep(12000);
            
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println(e);
        }
    }
    
    public static void main(String[] args) {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://10minutemail.net/address.api.php?sessionid="+gens(9));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        result.append(line);
                    }
                    JSONObject js = new JSONObject(result.toString());
                    System.out.println(js.getString("mail_get_mail"));
//                    JSONObject js = new JSONObject(result.toString());
//                    JSONArray codeMail = new JSONArray(js.getJSONArray("mail_list"));
//                    for (int i = 0; i < codeMail.length(); i++) {
//                        JSONObject jsk = new JSONObject(codeMail.get(i).toString());
//                        if(jsk.getString("from").contains("registration")){
//                            System.out.println("ono");
//                            System.out.println(jsk.getString("subject").split(" ")[0]);
//                        } else {
//                            System.out.println("gaono kodene");
//                            System.out.println(jsk);
//                        }
//                    }
            }
        } catch (IOException | JSONException e) {
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
