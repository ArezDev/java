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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AREZDEV
 */
public class esekr {
    
    public static void main(String deviceGmail) throws IOException {
        String mail_esekr = "";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("http://ese.kr/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    Pattern findMail = Pattern.compile("name=\"mailbox\" value=\"(.*?)\" style=\"float:right;\">");
                    Matcher email = findMail.matcher(line);
                    if(email.find()){
                        mail_esekr = email.group().replace("name=\"mailbox\" value=\"", "").replace("\" style=\"float:right;\">", "");
                        result.append(mail_esekr);
                    }
                }
            }
            System.out.println("get email sukses: "+result.toString());
            //isi email
            adb.create("adb -s " + deviceGmail + " shell input text "+result.toString());
            //Thread.sleep(Integer.parseInt(Main.delay_next.getText()) * 1000);
            //next 1
            adb.create("adb -s " + deviceGmail + " shell input tap 962 661");
            //Thread.sleep(Integer.parseInt(Main.delay_next.getText()) * 1000);
            //next 2
            adb.create("adb -s " + deviceGmail + " shell input tap 535 555");
            //Thread.sleep(Integer.parseInt(Main.delay_next.getText()) * 1000);
            //wait kode !
            //Thread.sleep(Integer.parseInt(Main.delay_waitkode.getText()) * 1000);
            getCode(mail_esekr,deviceGmail);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println(e);
        }
    }
    public static void getMail(String devices) {
        String mail_esekr = "";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("http://ese.kr/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    Pattern findMail = Pattern.compile("name=\"mailbox\" value=\"(.*?)\" style=\"float:right;\">");
                    Matcher email = findMail.matcher(line);
                    if(email.find()){
                        mail_esekr = email.group().replace("name=\"mailbox\" value=\"", "").replace("\" style=\"float:right;\">", "");
                        result.append(mail_esekr);
                    }
                }
                Thread.sleep(2000);
                System.out.println("\n devices  : " + devices + " => " + mail_esekr);
                try (FileWriter writeMail = new FileWriter("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + devices +".txt"); 
                    BufferedWriter typeMail = new BufferedWriter(writeMail)) {
                    typeMail.write(mail_esekr);
                }
                        //wait
                            Thread.sleep(2000);
                        //ok !
                        //adb.klik(devices, "526 1126");
                        //not now
                            adb.klik(devices, "66 1126");
                        //wait
                            Thread.sleep(4000);
                            
                        //change by email
                            adb.klik(devices, "64 680");
                        //wait
                             Thread.sleep(2000);
                             
                        //klik box
                         adb.klik(devices, "59 267");
                            //adb.klik(devices, "66 495");
                        //wait
                             Thread.sleep(2000);
                        //fill email
                            adb.create("adb -s " + devices + " shell input text '" + mail_esekr + "'");
                        //wait
                            Thread.sleep(2000);
                            
                        //Add valid email
                            //adb.klik(devices, "66 587");
                        //wait
                            //Thread.sleep(4000);
                            
                        //next
                            adb.klik(devices, "59 360");
                        //wait
                            Thread.sleep(9000);
                            
                        // allow kontak
                            adb.klik(devices, "540 734");
                        //wait
                            Thread.sleep(5000);
                           
                        //resend code!
                            adb.klik(devices, "188 666"); 
                        //wait
                            Thread.sleep(6000);  
                            
                        //ok
                            adb.klik(devices, "108 675"); 
                        //wait
                            Thread.sleep(5000);

                        //wait get code
                            Thread.sleep(15000);  
                        //get code    
                            getCode(mail_esekr, devices);     
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println(e);
        }
    }
    public static void getCode(String mail, String devices) throws MalformedURLException, IOException, InterruptedException, URISyntaxException {
        String code_konfirm = "";
        URL obj = new URL("http://ese.kr/");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty ("Content-Type", "application/x-www-form-urlencoded");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                String datajson = "mail_id=&mail_mode=&lang=en&mailbox="+mail;
                try(OutputStream os = con.getOutputStream()) {
                        byte[] input = datajson.getBytes("utf-8");
                        os.write(input, 0, input.length);			
                         }
		            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                                  StringBuilder response = new StringBuilder();
                                  for (String line; (line = br.readLine()) != null; ) {
                                        Pattern findCode = Pattern.compile("registration@facebookmail.com</td><td style=\"font-weight:bold;\"><a href=\"#\">\\d+");
                                        Matcher code = findCode.matcher(line);
                                        if(code.find()){
                                            code_konfirm=code.group(0).replace("registration@facebookmail.com</td><td style=\"font-weight:bold;\"><a href=\"#\">","");
                                            response.append(code_konfirm);
                                        }
                                  }
                                  if(code_konfirm.isEmpty()){
                                      System.out.println("\n" + mail + " => " + devices + " get Code FB : gagal"); 
                                      //reset
                                        adb.clearAPP(devices, "com.facebook.lite");
                                         //wait
                                             Thread.sleep(5000);
                                        adb.closeApp(devices, "com.windscribe.vpn");
                                         //wait
                                             Thread.sleep(5000);
                                  } else {
                                  System.out.println("\n devices :" + devices + " mail => " + mail + " Code : "+code_konfirm); 
                                    //allow kontak
                                        //adb.klik(devices, "540 734");
                                    //wait
                                        Thread.sleep(2000);
                                    //klikbox code
                                        //adb.klik(devices, "343 443");
                                        adb.klik(devices, "352 455");
                    //                //wait
                    //                    Thread.sleep(2000);

                    //                //fill code
                    //                    //adb.nulis(devices, codeFB);
                                        adb.create("adb -s " + devices + " shell input keyevent 62");
                    //                //wait
                                        Thread.sleep(2000);
                    //                //clear
                                        adb.create("adb -s " + devices + " shell input keyevent 67");
                                        Thread.sleep(25);
                                        adb.create("adb -s " + devices + " shell input keyevent 67");
                                        Thread.sleep(25);
                    //                    adb.create("adb -s " + devices + " shell input keyevent 67");
                    //                    Thread.sleep(200);
                    //                    adb.create("adb -s " + devices + " shell input keyevent 67");
                    //                    Thread.sleep(200);
                    //                    adb.create("adb -s " + devices + " shell input keyevent 67");
                    //                    Thread.sleep(200);
                    //                //wait
                    //                     Thread.sleep(100);

                                    //fill code
                                        adb.nulis(devices, code_konfirm);
                                    //wait
                                        Thread.sleep(1000);
                                        
                                    //OK code SignUp !!!
                                        //adb.klik(devices, "362 538");
                                        adb.klik(devices, "355 551");
                                    //wait
                                        Thread.sleep(6000);
                                        
                                    //get email
                                    BufferedReader bfr;
                                    bfr = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + devices + ".txt"));
                                    String email = bfr.readLine();
                                    //send FB
                                        CookiesFB.getCookies(devices, email);
                                    //wait
                                        Thread.sleep(9000);
                                        
                                   //reset
                                   adb.clearAPP(devices, "com.facebook.lite");
                                    //wait
                                        Thread.sleep(5000);
                                   adb.closeApp(devices, "com.windscribe.vpn");
                                    //wait
                                        Thread.sleep(5000);
                                    
                                  }                        
                            }
    }
}
