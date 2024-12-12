/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper;

import com.arezdev.siwalandeveloper.api.Confirm;
import com.arezdev.siwalandeveloper.api.adb;
import java.io.BufferedReader;
import java.io.FileReader;
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
public class startEmulator {
    
    public static void main(String[] args) throws URISyntaxException, InterruptedException, IOException {
        System.out.println("start auto create facebook !");
        
        adb.startConfirm();
        
        //CookiesFB.getCookies("127.0.0.1:16416", "cri27198@kisoq.com");
        //adb.getAllcookiefb();
        //adb.startDevices();
        
//        Confirm.getData("127.0.0.1:16384");
//        Confirm.start("127.0.0.1:16384");

    }
    
    public static void tes(String emulatorKu) {
     String cek_profile = "";
        try {
            ProcessBuilder cmd = new ProcessBuilder(
                "cmd.exe", 
                "/c", "adb -s "+ emulatorKu +" root && adb -s "+ emulatorKu +" pull /data/data/com.facebook.lite/files/PropertiesStore_v02 C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\checkLogin-" + emulatorKu +".txt");
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
            njupokFile = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\checkLogin-" + emulatorKu+".txt"));
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
        } catch (IOException | JSONException e) {
            System.err.println(e);
        }
    }
    
}
