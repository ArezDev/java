/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import com.arezdev.siwalandeveloper.CookiesFB;
import com.arezdev.siwalandeveloper.startEmulator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AREZDEV
 */
public class adb {
    
    public static void verifycode() {
        try {
//              ProcessBuilder cmd = new ProcessBuilder(
//                "cmd.exe", 
//                "/c", "adb devices");
//                cmd.redirectErrorStream(true);
//                Process p = cmd.start();
//                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			String inputLine = null;
//			StringBuilder response = new StringBuilder();
//                        while ((inputLine = r.readLine()) != null) {
//                                response.append(inputLine + "\n");
//                        }
//                        r.close();
//                        String result = response.toString();                          
//                        String[] devicesList = result.split("List of devices attached");
//                        sendText(devicesList[1]);
                        BufferedReader njupokFile;
                        njupokFile = new BufferedReader(new FileReader("deviceku.txt"));
                        String isiFile = njupokFile.readLine();
                        while((isiFile = njupokFile.readLine()) != null) {   
                        //ngitung devices
                              Pattern modelsID = Pattern.compile("(.*?)	device");
                              Matcher findID = modelsID.matcher(isiFile);
                              if(findID.find()){
                                  String device = findID.group(0).replace("	device", "");
                                    Thread verifycode = new Thread() {
                                      @Override
                                      public void run() {
                                          try {
                                            BufferedReader bfr;
                                            bfr = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + device + ".txt"));
                                            String email = bfr.readLine();
                                              System.out.println(device + " => " + email);
                                              Thread.sleep(4000);
                                             //start devices
                                              tempmailio.getCode(email, device);
                                          } catch (IOException | InterruptedException ex) {
                                              Logger.getLogger(adb.class.getName()).log(Level.SEVERE, null, ex);
                                          }
                                        }};
                                        verifycode.start();
                                        Thread.sleep(5000);
                                        System.out.println("start device: " + device);
                                        System.out.println("start devices : " + device + " => " + isiFile);
                                }
                              
                            //System.out.println(isiFile);
                        }
        
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }
    }
    
    public static void connect_emulator(String device) throws IOException {
        
            ProcessBuilder cmd = new ProcessBuilder("cmd.exe", "/c", device);
                cmd.redirectErrorStream(true);
                Process p = cmd.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String inputLine = null;
			StringBuilder response = new StringBuilder();
                        while ((inputLine = r.readLine()) != null) {
                                response.append(inputLine + "\n");
                        }
                        r.close();
                        String result = response.toString(); 
                        System.out.println(result);
                        
    }
    
    public static void startDevices() {
        try {
            BufferedReader emulaator_k;
               emulaator_k = new BufferedReader(new FileReader("connect_device.txt"));
//                String emulatorku = emulaator_k.readLine();
//                while((emulatorku = emulaator_k.readLine()) != null) {
//                        connect_emulator(emulatorku);
//                }
                        for (String line; (line = emulaator_k.readLine()) != null; ) {
                            //result.append(line);
                            //System.out.println(line);
                            connect_emulator(line);
                        }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static void startConfirm() {
        try {
            startDevices();
            ProcessBuilder cmd = new ProcessBuilder("cmd.exe", "/c", "adb devices");
                cmd.redirectErrorStream(true);
                Process p = cmd.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String inputLine = null;
			StringBuilder response = new StringBuilder();
                        while ((inputLine = r.readLine()) != null) {
                                response.append(inputLine + "\n");
                        }
                        r.close();
                        String result = response.toString();                          
                        String[] devicesList = result.split("List of devices attached");
                        sendText(devicesList[1]);
                BufferedReader njupokFile;
                        njupokFile = new BufferedReader(new FileReader("hello.txt"));
                        String isiFile = njupokFile.readLine();
                        while((isiFile = njupokFile.readLine()) != null) {   
                        //ngitung devices
                              Pattern modelsID = Pattern.compile("(.*?)	device");
                              Matcher findID = modelsID.matcher(isiFile);
                                if(findID.find()){
                                  String device = findID.group(0).replace("	device", "");
                                    Thread startEmu = new Thread() {
                                      @Override
                                      public void run() {
                                          try {
                                        //start devices
                                              Confirm.getData(device);
                                              Confirm.start(device);
                                          } catch (InterruptedException | URISyntaxException e) {
                                              System.err.println(e);
                                          } catch (IOException ex) {
                                              Logger.getLogger(adb.class.getName()).log(Level.SEVERE, null, ex);
                                          }
                                        }};
                                        startEmu.start();
                                        Thread.sleep(7000);
                                        System.out.println("start device: " + device);
                                        System.out.println("start devices : " + device + " => " + isiFile + "\n");
                                }
                        }
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }
    }
    public static void getAllcookiefb() {
        try {
            startDevices();
            ProcessBuilder cmd = new ProcessBuilder(
                "cmd.exe", 
                "/c", "adb devices");
                cmd.redirectErrorStream(true);
                Process p = cmd.start();
                StringBuilder response;
                    try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                        String inputLine = null;
                        response = new StringBuilder();
                        while ((inputLine = r.readLine()) != null) {
                            response.append(inputLine).append("\n");
                        }
                    }
                        String result = response.toString();                          
                        String[] devicesList = result.split("List of devices attached");
                        sendText(devicesList[1]);
                        BufferedReader njupokFile;
                        njupokFile = new BufferedReader(new FileReader("hello.txt"));
                        String isiFile = njupokFile.readLine();
                        while((isiFile = njupokFile.readLine()) != null) {   
                        //ngitung devices
                              Pattern modelsID = Pattern.compile("(.*?)	device");
                              Matcher findID = modelsID.matcher(isiFile);
                              if(findID.find()){
                                  String device = findID.group(0).replace("	device", "");
                                    Thread startEmu = new Thread() {
                                      @Override
                                      public void run() {
                                          try {
                                              BufferedReader bfr;
                                              bfr = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\akun-" + device + ".txt"));
                                              String email = bfr.readLine();
                                              CookiesFB.getCookies(device, email);
                                            } catch (IOException | InterruptedException | URISyntaxException e) {
                                                System.err.println(e);
                                          }
                                        }};
                                        startEmu.start();
                                        Thread.sleep(2000);
                                        System.out.println("get cookie device: " + device);
                                        System.out.println("get cookie devices : " + device + " => " + isiFile);
                            }
                            System.out.println(isiFile);
                        }
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }
    }
    private static void sendText(String Line) throws FileNotFoundException, UnsupportedEncodingException, IOException{
            FileWriter fr = new FileWriter("hello.txt");
                    BufferedWriter br = new BufferedWriter(fr);
                    br.write(Line);
                    br.close();
                    fr.close();
    }
    
    public static void closeApp(String emulator, String App) throws IOException, InterruptedException {
        
    ProcessBuilder openFB = new ProcessBuilder("cmd.exe", "/c", "adb -s " + emulator + " shell am force-stop " + App);
    openFB.redirectErrorStream(true);
    Process p = openFB.start();
    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(emulator + " => " + line + "\n"); 
        }  
    }
    
    public static void openFB(String cmdString) throws IOException, InterruptedException {
        
    ProcessBuilder openFB = new ProcessBuilder("cmd.exe", "/c", cmdString);
    openFB.redirectErrorStream(true);
    Process p = openFB.start();
    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            //System.out.println(line); 
        }  
    }
    
    public static void create(String createString) {
        
    ProcessBuilder createFB = new ProcessBuilder("cmd.exe", "/c", createString);
        try {
            createFB.start();   
        } catch (IOException ex) {
            Logger.getLogger(adb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void klik(String device, String tap) throws IOException {
        ProcessBuilder nulis = new ProcessBuilder("cmd.exe", "/c", "adb -s "+device+" shell input tap "+tap);
        nulis.redirectErrorStream(true);
        Process p = nulis.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            //System.out.println(line); 
        }  
    }
    
    public static void nulis(String device, String konten) throws IOException {
        ProcessBuilder nulis = new ProcessBuilder("cmd.exe", "/c", "adb -s "+device+" shell input text '"+konten+"'");
        nulis.redirectErrorStream(true);
        Process p = nulis.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            //System.out.println(line); 
        }  
    }
    
    public static void clearAPP(String devices, String App) {

        try {        
            ProcessBuilder clearData = new ProcessBuilder("cmd.exe", "/c", "adb -s " + devices + " shell pm clear " + App);
            clearData.redirectErrorStream(true);
            Process p = clearData.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }

                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(adb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
