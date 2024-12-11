/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper;

import static com.arezdev.siwalandeveloper.Settings.aku;
import com.arezdev.siwalandeveloper.Websoket_ku.saveFB;
import com.arezdev.siwalandeveloper.api.adb;
import com.arezdev.siwalandeveloper.api.email_10menit;
import com.arezdev.siwalandeveloper.api.esekr;
import com.arezdev.siwalandeveloper.api.mailnesia;
import com.arezdev.siwalandeveloper.api.tempmailio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class CookiesFB {
    
    public static void check_fb(String uid) throws MalformedURLException, IOException {
        StringBuilder result = new StringBuilder();
            URL url = new URL("https://graph.facebook.com/" + uid + "/picture?type=normal");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(true);
            try (BufferedReader reader = new BufferedReader( new InputStreamReader(http.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
            //System.out.println(result);
            System.out.println(http.getURL());
            String fb_normal = http.getURL().toString();
            if(fb_normal.contains("C5yt7Cqf3zU")){
                System.out.println("FBne cp!");
            } else {
                System.out.println("FBne urip!");
            }
    }
    
public static void getCookies(String emulatorKu, String email) throws InterruptedException, URISyntaxException {
    
    String uid = "";
    String c_user = "";
    String xs = "";
    String fr = "";
    String datr = "";
    String accestoken = "";
    String profiles = "";
    
    try {
        ProcessBuilder cmd = new ProcessBuilder(
                "cmd.exe", 
                "/c", "adb -s "+ emulatorKu +" root && adb -s "+ emulatorKu +" pull /data/data/com.facebook.lite/files/PropertiesStore_v02 " +  aku + "\\" + emulatorKu +".txt");
        cmd.redirectErrorStream(true);
        Process p = cmd.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            //System.out.println(line);
        }
    BufferedReader njupokFile;
        njupokFile = new BufferedReader(new FileReader( aku + "\\" +emulatorKu+".txt"));
        String isiFile = njupokFile.readLine();
                while((isiFile = njupokFile.readLine()) != null) {   
                //njupok cokis
                String rawTextFromFile = isiFile.replaceAll("[^\\u0020-\\u007E]", "|");
                String della = rawTextFromFile.replace("|", "\n");
                //njupok token
                      Pattern modelsAccessToken = Pattern.compile("profile(.*?})(.*?})");
                      Matcher akses_token = modelsAccessToken.matcher(della);
                      if(akses_token.find()){
                          String full_accesstoken = "{\""+akses_token.group(0);
                          JSONObject js = new JSONObject(full_accesstoken);
                          accestoken=js.getString("access_token");
                          JSONObject check_fb = (JSONObject) js.get("profile");
                          profiles=check_fb.getString("name");
                      }
                    //get full cookie              
                      Pattern modelsCookie = Pattern.compile("expires(.*?})(.*?})(.*?})(.*?})");
                      Matcher Cookie = modelsCookie.matcher(della);
                      if(Cookie.find()){
                          String full_cookie = "[{\""+Cookie.group()+"]";
                          JSONArray js = new JSONArray(full_cookie);
                          JSONObject cookie_c_user = (JSONObject) js.get(0);
                          JSONObject cookie_xs = (JSONObject) js.get(1);
                          JSONObject cookie_fr = (JSONObject) js.get(2);
                          JSONObject cookie_datr = (JSONObject) js.get(3);
                            //set Cookie
                            uid=cookie_c_user.getString("value");
                            c_user="c_user="+cookie_c_user.getString("value")+";";
                            xs="xs="+cookie_xs.getString("value")+";";
                            fr="fr="+cookie_fr.getString("value")+";";
                            datr="datr="+cookie_datr.getString("value")+";";
                      }
                 }
                if(profiles.isEmpty()){
                      //System.out.println("fb checkpoint!");
                } else {
                      //System.out.println("fb waras!");
                    System.out.println("get cookie device: " + emulatorKu + "\n");
                //delay cek uid//  
                Thread.sleep(5000);
                //delay cek uid//
                StringBuilder response = new StringBuilder();
                    URL url = new URL("https://graph.facebook.com/" + uid + "/picture?type=normal");
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("GET");
                    http.setDoOutput(true);
                    try (BufferedReader reader = new BufferedReader( new InputStreamReader(http.getInputStream()))) {
                        for (String lineP; (lineP = reader.readLine()) != null; ) {
                            response.append(lineP);
                        }
                    }
                    System.out.println(response);
                    System.out.println(http.getURL());
                    String fb_normal = http.getURL().toString();
                    if(fb_normal.contains("C5yt7Cqf3zU")){
                        System.out.println("FBne cp!");
                        //close vpn
                            Thread.sleep(5000);  
                        adb.closeApp(emulatorKu,"com.windscribe.vpn");
                        //wait
                            Thread.sleep(5000);  
                        //clear fb
                        adb.clearAPP(emulatorKu,"com.facebook.lite");
                        //wait
                            Thread.sleep(5000);
                    } else {
                        System.out.println("FBne urip!");
                        send_akun(uid,datr,c_user,xs,fr,accestoken,email);
                    }
                          
                }
                Thread.sleep(5000);
        try (FileWriter data_cookis = new FileWriter( aku + "\\" + emulatorKu + ".txt"); BufferedWriter br = new BufferedWriter(data_cookis)) {
            br.write("done get cookies !");
        }
    } catch (IOException ignored) {
        //Logger.getLogger(CookiesFB.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

    public static void checkLogin(String emulatorKu) throws URISyntaxException {
        String cek_profile = "";
        try {
            ProcessBuilder cmd = new ProcessBuilder(
                "cmd.exe", 
                "/c", "adb -s "+ emulatorKu +" root && adb -s "+ emulatorKu +" pull /data/data/com.facebook.lite/files/PropertiesStore_v02 " + aku + "\\checkLogin-" + emulatorKu +".txt");
        cmd.redirectErrorStream(true);
        Process p = cmd.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println("devices: " + emulatorKu + " => " + line);
        }
        BufferedReader njupokFile;
            njupokFile = new BufferedReader(new FileReader( aku + "\\checkLogin-" + emulatorKu+".txt"));
            String isiFile = njupokFile.readLine();
                    while((isiFile = njupokFile.readLine()) != null) {   
                    //njupok cokis
                    String rawTextFromFile = isiFile.replaceAll("[^\\u0020-\\u007E]", "|");
                    String della = rawTextFromFile.replace("|", "\n");
                    //getcookie
                          Pattern modelsAccessToken = Pattern.compile("profile(.*?})(.*?})");
                          Matcher akses_token = modelsAccessToken.matcher(della);
                          if(akses_token.find()){
                              String full_accesstoken = "{\""+akses_token.group(0);
                              JSONObject js = new JSONObject(full_accesstoken);
                              JSONObject check_fb = (JSONObject) js.get("profile");
                              cek_profile=check_fb.getString("name");
                          }
                     }
                    System.out.println(cek_profile);
    //random email
            Integer[] mail_services = {1};
            int ee = (int) Math.floor(Math.random() * mail_services.length);
                if(cek_profile.isEmpty()){
                    String userku = "";
                    String passku = "";
                    System.out.println("\n devices: " + emulatorKu + " Login lawas!");
                BufferedReader bfr;
                bfr = new BufferedReader(new FileReader( aku + "\\akun-" + emulatorKu + ".txt"));
                String akun = bfr.readLine();
                userku=akun.split("\\|")[0];
                passku=akun.split("\\|")[1];
                    //wait
//                        Thread.sleep(4000);
//                        adb.klik(emulatorKu, "534 272");
//                    //wait
//                        Thread.sleep(2000);
//                        adb.klik(emulatorKu, "66 1209");
//                    //wait
//                        Thread.sleep(2000);
                        
                     //allow 
                        //adb.klik(emulatorKu, "536 734");
                     //deny 
                        //adb.klik(emulatorKu, "411 734");
                        
                    //wait
                        //Thread.sleep(5000);
                        
//                //user//
//                        //adb.klik(emulatorKu, "54 242");
//                        adb.klik(emulatorKu, "47 198");
//                    //wait
//                        //Thread.sleep(2000);
//                    ////    
//                        adb.nulis(emulatorKu, userku);
//                    //wait
//                        Thread.sleep(3000);
//                        //Thread.sleep(2000);
//
//                //pass//
//                        //adb.klik(emulatorKu, "47 384");
//                        adb.klik(emulatorKu, "47 335");
//                    //wait
//                        //Thread.sleep(2000);
//                    ////    
//                        adb.nulis(emulatorKu, passku);
//                    //wait
//                        Thread.sleep(3000);
//                        //Thread.sleep(2000);
//
//                    //Login !
//                        //adb.klik(emulatorKu, "49 472");
//                        adb.klik(emulatorKu, "44 426");
//normal Login!
            //user
                adb.klik(emulatorKu, "299 536");
                //adb.klik(devices, "345 622");
            //wait
                Thread.sleep(2000);
            ////    
                adb.nulis(emulatorKu, userku);
            //wait
                Thread.sleep(200);
            //pass
                adb.klik(emulatorKu, "326 668");
                //adb.klik(devices, "330 754");
            //wait
                Thread.sleep(2000);
            ////    
                adb.nulis(emulatorKu, passku);
            //wait
                Thread.sleep(200);
            //Login !
                adb.klik(emulatorKu, "355 786");
                //adb.klik(devices, "360 881");                        
                        
            //wait
                Thread.sleep(15000);   
                        
                    ///mari Login !
        
/////windscribe
                if(Settings.vpn){
                    
                    Settings.vpn_start(emulatorKu);
                            
                }
                        Settings.email_confirm(emulatorKu, mail_services[ee]);
                            
                        //wait
                        //Thread.sleep(15000);
                } else {           
                        //mari Login !
                        //wait
                            //Thread.sleep(5000);
                if(Settings.vpn){
                    
                    Settings.vpn_start(emulatorKu);
                        
                }
                        Settings.email_confirm(emulatorKu, mail_services[ee]);
                            
                        //wait
                        //Thread.sleep(15000);
            }
            //done!!!
            try (FileWriter data_cookis = new FileWriter( aku + "\\checkLogin-" + emulatorKu + ".txt"); BufferedWriter br = new BufferedWriter(data_cookis)) {
                 br.write("done cek Login!");
            } 
        
    } catch (IOException ignored) {
        //Logger.getLogger(CookiesFB.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InterruptedException ex) { 
          Logger.getLogger(CookiesFB.class.getName()).log(Level.SEVERE, null, ex);
    } 
        
    }

public static void getCookiesfb(String emulatorKu, String email) throws InterruptedException, URISyntaxException {
    
    String uid = "";
    String c_user = "";
    String xs = "";
    String fr = "";
    String datr = "";
    String accestoken = "";
    String profiles = "";
    
    try {
        ProcessBuilder cmd = new ProcessBuilder(
                "cmd.exe", 
                "/c", "adb -s "+ emulatorKu +" root && adb -s "+ emulatorKu +" pull /data/data/com.facebook.katana/app_light_prefs/com.facebook.katana/authentication C:/Users/AREZDEV/Documents/DATAKODINGJAVA/"+ emulatorKu +".txt");
    cmd.redirectErrorStream(true);
    Process p = cmd.start();
    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line;
    while (true) {
        line = r.readLine();
        if (line == null) {
            break;
        }
        
        System.out.println(line);
        
    }
    BufferedReader njupokFile;
        njupokFile = new BufferedReader(new FileReader("C:/Users/AREZDEV/Documents/DATAKODINGJAVA/"+emulatorKu+".txt"));
        String isiFile = njupokFile.readLine();
        while((isiFile = njupokFile.readLine()) != null) {   
            
        //njupok cokis
        String rawTextFromFile = isiFile.replaceAll("[^\\u0020-\\u007E]", "|");
        String della = rawTextFromFile.replace("|", "\n");
            System.out.println(della);

        //njupok token
              Pattern modelsAccessToken = Pattern.compile("EAA(.*?)\\|");
              Matcher akses_token = modelsAccessToken.matcher(rawTextFromFile);
              if(akses_token.find()){
                  String full_accesstoken = akses_token.group(0);
                  System.out.println(full_accesstoken.replace("|", ""));
                  accestoken=full_accesstoken.replace("|", "");
              }
            
            //get full cookie              
              Pattern modelsCookie = Pattern.compile("name(.*?})(.*?})(.*?})(.*?})");
              Matcher Cookie = modelsCookie.matcher(della);
              if(Cookie.find()){
                  String full_cookie = "[{\""+Cookie.group()+"]";
                  System.out.println(full_cookie);
                  JSONArray js = new JSONArray(full_cookie);
                  JSONObject cookie_c_user = (JSONObject) js.get(0);
                  JSONObject cookie_xs = (JSONObject) js.get(1);
                  JSONObject cookie_fr = (JSONObject) js.get(2);
                  JSONObject cookie_datr = (JSONObject) js.get(3);
                    //set Cookie
                    uid=cookie_c_user.getString("value");
                    c_user="c_user="+cookie_c_user.getString("value")+";";
                    xs="xs="+cookie_xs.getString("value")+";";
                    fr="fr="+cookie_fr.getString("value")+";";
                    datr="datr="+cookie_datr.getString("value")+";";
                    System.out.println(c_user);
                    System.out.println(xs);
                    System.out.println(datr);
                    System.out.println(fr);
              }
         }
        send_akun(uid,datr,c_user,xs,fr,accestoken,email);
    } catch (IOException ignored) {
        //Logger.getLogger(CookiesFB.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

    public static void send_akun(String uid, String datr, String c_user, String xs, String fr, String accestoken, String email) throws URISyntaxException, InterruptedException, MalformedURLException, IOException {
        //save soket
        //saveFB client = new saveFB(new URI("ws://localhost:8181/"));
        //client.connectBlocking();
        //client.send(uid+"|"+"TEORINGELID"+"|"+ datr + " "+ c_user + " "+ xs + " "+ fr + "|"+ accestoken);
        //client.send(uid);
        
    //save server
//        String[] id_user = {"Nasyith","TesAkun"};
//        int random_user = (int) Math.floor(Math.random() * id_user.length);
//        String fix_user = id_user[random_user];
            String fix_user = "Nasyith";
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://balanesohib.team/server_akun/api/data.php");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            //bodyform
            String a = "send_akun=live&akune=ArezDev&cokis="+uid+"|"+"TEORINGELID"+"|"+ datr + " "+ c_user + " "+ xs + " "+ fr + "|"+ accestoken+ "|" + email;
            String b = "send_akun=live&akune=Nasyith&cokis="+uid;
            String c = "send_akun=live&akune=" + fix_user + "&cokis="+uid;
            //random
            //String[] random_send_akuns ={body0,body1,body2};
            //int kirimAkun = (int) Math.floor(Math.random() * random_send_akuns.length);
            
                try(OutputStream os = http.getOutputStream()) {
                    //byte[] input = random_send_akuns[kirimAkun].getBytes("utf-8");
                    byte[] input = a.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
                        for (String line; (line = reader.readLine()) != null; ) {
                            result.append(line);
                        }
                }
                //System.out.println("!send akune : " + fix_user + " => " + result);
                System.out.println(result);
    }
    
}
