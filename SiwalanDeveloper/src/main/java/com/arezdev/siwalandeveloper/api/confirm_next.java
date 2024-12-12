/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import com.arezdev.siwalandeveloper.CookiesFB;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author AREZDEV
 */
public class confirm_next {
    
    public static void changeMail(String devices, String Mail) {
        
        try {
            
            System.out.println("device " + devices + " email => " + Mail + "\n");
                    Thread.sleep(7000);
                        //ok !
                        //adb.klik(devices, "526 1126");
                        //not now
//                            adb.klik(devices, "66 1126");
//                            Thread.sleep(500);
//                        //not now
//                            adb.klik(devices, "66 1126");
//                            Thread.sleep(500);
                        //not now
                            adb.klik(devices, "66 1126");
                        //delay wait
                            Thread.sleep(5000);
                            
                        //deny dobel mail lawas    
                        //adb.klik(devices, "411 734");
                            
                        //change by email
                            adb.klik(devices, "64 680");
                            //adb.klik(devices, "42 734");
                        //wait
                             Thread.sleep(5000);
                             
                        //klik box
                         adb.klik(devices, "59 267");
                         
                        //klik box Add valid email
                            //adb.klik(devices, "66 495");
                        //wait
                             Thread.sleep(4000);
                            
                        //fill email
                            adb.create("adb -s " + devices + " shell input text '" + Mail + "'" + "\n");
                        //wait
                            Thread.sleep(4000);
                            
                        //Add valid email
                            //adb.klik(devices, "66 587");
                        //wait
                            //Thread.sleep(4000);
                            
                        //next
                            adb.klik(devices, "59 360");
                        //wait
                            Thread.sleep(7000);
                            
                        //////////===resend code===//////////    

//                        // allow kontak
//                            adb.klik(devices, "540 734");
//                        //wait
//                            Thread.sleep(5000);
//                           
//                        //resend code!
//                            adb.klik(devices, "188 666"); 
//                        //wait
//                            Thread.sleep(6000);  
//                            
//                        //ok
//                            adb.klik(devices, "108 675"); 
//                        //wait
//                            Thread.sleep(3000);
                            
                        //////////===resend code===//////////    
                            
                        //wait get code
                            //Thread.sleep(5000); 
                            
                        //get code    
                            //getCode(session_10menit, devices);
                            //verify(devices);
                
                            
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }
        
    }
    
    public static void verify(String devices, String code) throws InterruptedException, IOException, URISyntaxException {
                //wait
                    Thread.sleep(4000);
                // allow kontak
                    adb.klik(devices, "540 734");
                //wait
                    Thread.sleep(5000);
                    
                //klikbox code
                    adb.klik(devices, "343 443");
                //wait
                    Thread.sleep(2000);
                //klikbox code
                    adb.klik(devices, "343 443");
//                //wait
//                    Thread.sleep(2000);

                    adb.create("adb -s " + devices + " shell input keyevent 62");
//                //wait
                    Thread.sleep(2000);
//                //clear
                    adb.create("adb -s " + devices + " shell input keyevent 67");
                    Thread.sleep(25);
                    adb.create("adb -s " + devices + " shell input keyevent 67");
                    Thread.sleep(25);

                //fill code
                    adb.nulis(devices, code);
                //wait
                    Thread.sleep(4000);
                    
                //OK code SignUp !!!
                    adb.klik(devices, "362 538");
                //wait
                    Thread.sleep(10000);
                    
                //get email
                BufferedReader bfr;
                bfr = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + devices + ".txt"));
                String email = bfr.readLine();
                //send FB
                    CookiesFB.getCookies(devices, email);
                //wait
                    Thread.sleep(2000);
                    
//                        //OFF vpn
//                            adb.openFB("adb -s " + devices + " shell am start -n com.windscribe.vpn/com.windscribe.mobile.windscribe.WindscribeActivity");
//                        //wait
//                            Thread.sleep(4000);
//                        //klik vpn
//                            adb.klik(devices, "590 184");
//                    adb.closeApp(devices,"com.windscribe.vpn");
//                //wait
//                    Thread.sleep(5000);
//                    
//                    adb.closeApp(devices,"com.facebook.lite");
                        //wait
                            //Thread.sleep(2000);
                            
    }
    
}
