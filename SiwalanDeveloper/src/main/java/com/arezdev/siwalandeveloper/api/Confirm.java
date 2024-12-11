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

/**
 *
 * @author AREZDEV
 */
public class Confirm {
    
//       public static String login_name = "";
//       public static String login_pass = "";
    
    public static void getData(String emulator) throws URISyntaxException, InterruptedException, IOException{
        
            //get user data websoket
//            Websoket_ku.getFB user_fb = new Websoket_ku.getFB(new URI("ws://localhost:8282/"));
//                user_fb.connectBlocking();
//                user_fb.send("minta");
//            //wait
//                Thread.sleep(2000);

//get akun teko server!
//RelogCokis

            StringBuilder result = new StringBuilder();
            URL url = new URL("https://balanesohib.team/server_akun/api/data.php?get_akun=fb&id=RelogCokis&total=1");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
                try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(http.getInputStream()))) {
                        for (String line; (line = reader.readLine()) != null; ) {
                            result.append(line);
                        }
                }
                System.out.println(result);
            //write acc
            try (FileWriter data_cookis = new FileWriter( aku + "\\akun-" + emulator + ".txt"); BufferedWriter br = new BufferedWriter(data_cookis)) {
                //br.write(Websoket_ku.data_info);
                  br.write(result.toString());
            }
            //wait
                Thread.sleep(2000);  
    }
    
    public static void start(String devices) throws URISyntaxException, InterruptedException {
        
        try {
            //close vpn
            adb.closeApp(devices,"com.windscribe.vpn");
            //clear fb
            adb.clearAPP(devices,"com.facebook.lite");
            
            String userku = "";
            String passku = "";

            BufferedReader bfr;
            bfr = new BufferedReader(new FileReader( aku + "\\akun-" + devices + ".txt"));
                String akun = bfr.readLine();
                userku=akun.split("\\|")[0];
                passku=akun.split("\\|")[1];
                
            //wait
                Thread.sleep(5000);

            //mbuka FB LITE
                adb.openFB("adb -s " + devices + " shell am start -n com.facebook.lite/com.facebook.lite.MainActivity");
            //wait mbuka FB LITE
                Thread.sleep(15000);
                //Thread.sleep(25000);
                
        System.out.println("jeneng login: " + userku + " devices = > " + devices + " \n");
        
        //=================Normal Login====================//    
//            //change settings
//                adb.klik(devices, "475 485");
//            //wait
//                Thread.sleep(2000);
//            //don't share!
//                adb.klik(devices, "353 1199");
//            //wait
//                Thread.sleep(2000);

//            //change settings
//                adb.klik(devices, "44 480");
//            //wait
//                Thread.sleep(2000);
//            //change settings 2
//                adb.klik(devices, "595 458");
//            //wait
//                Thread.sleep(2000);
//            //don't share!
//                adb.klik(devices, "340 1211");
//            //wait
//                Thread.sleep(2000);

//normal Login!
//            //user
//                adb.klik(devices, "299 536");
//                //adb.klik(devices, "345 622");
//            //wait
//                Thread.sleep(2000);
//            ////    
//                adb.nulis(devices, userku);
//            //wait
//                Thread.sleep(200);
//            //pass
//                adb.klik(devices, "326 668");
//                //adb.klik(devices, "330 754");
//            //wait
//                Thread.sleep(2000);
//            ////    
//                adb.nulis(devices, passku);
//            //wait
//                Thread.sleep(200);
//            //Login !
//                adb.klik(devices, "355 786");
//                //adb.klik(devices, "360 881");


//Login lawas!
                //user//
                        //adb.klik(emulatorKu, "54 242");
                        adb.klik(devices, "47 198");
                    //wait
                        //Thread.sleep(2000);
                    ////    
                        adb.nulis(devices, userku);
                    //wait
                        Thread.sleep(3000);
                        //Thread.sleep(2000);

                //pass//
                        //adb.klik(emulatorKu, "47 384");
                        adb.klik(devices, "47 335");
                    //wait
                        //Thread.sleep(2000);
                    ////    
                        adb.nulis(devices, passku);
                    //wait
                        Thread.sleep(3000);
                        //Thread.sleep(2000);

                    //Login !
                        //adb.klik(emulatorKu, "49 472");
                        adb.klik(devices, "44 426");
                
            //delay mari Login
                Thread.sleep(15000);
                
        //=================Normal Login====================//
            
            //cek halaman Login gendeng
            CookiesFB.checkLogin(devices);
            //wait
                Thread.sleep(2000);
            
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }
    }
    
}
