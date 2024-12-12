/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AREZDEV
 */
public class ArezDev_DATA {
    
    public static void writeMail(String device, String data) {
        
        try (FileWriter writeMail = new FileWriter("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + device +".txt"); 
                BufferedWriter typeMail = new BufferedWriter(writeMail)) {
                    typeMail.write(data);
                } catch (IOException ex) {
            Logger.getLogger(ArezDev_DATA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static String readMail(String device) throws FileNotFoundException, IOException {
        
        BufferedReader bfr;
        bfr = new BufferedReader(new FileReader("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\Email\\email-" + device + ".txt"));
        StringBuilder v = new StringBuilder();
        v.append(bfr.readLine());
        return v.toString();
        
    }
    
}
