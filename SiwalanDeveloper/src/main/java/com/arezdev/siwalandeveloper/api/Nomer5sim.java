/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author AREZDEV
 */
public class Nomer5sim {
    
    public static String id_order_5sim = null;
    public static String nomer_5sim = null;
    public static String kode_verify_5sim = null;
    
    public static void main(String [] c) {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://5sim.net/v1/user/buy/activation/netherlands/virtual51/facebook");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3Mzc4MDQ3MDksImlhdCI6MTcwNjI2ODcwOSwicmF5IjoiMDA5ZGFkYjcxN2Q3M2UyMzI3NWM2NzNlYWQxNjA5YzYiLCJzdWIiOjIxMzIwOTN9.3ar4R_wA84ywKLTR-6apJQO9qxfRxd67h0KiFXRaLdtyLyCTfXrRWCT6P2_CQPuS9BnUAgm6Z8VQrWzUAIbYSFpck8Wtuzbzmc2vbPH8ymX_LQRtYy2wd3SACsByzJxoi_rE0FhclTPRG2grNR7GURwVp1NNAra5vZxOwT4Mv1FjFge8eM9SEio3W-zu0J_hnVK0gZNHu8MhCIWiFKMPEQNLhOUe8NxZHwF_jpS5cCTCv8SJh4PURrCutn_KK_8SWYZGzdI6DrLdDaiBq9lkBsyvo_7L_E-Ef0FrPv1LFjRmHpD9eCnRQTg9e7vdQmZEY22lsBu-RktPwyAai_VPnQ");
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        result.append(line);
                    }
                }
                System.out.println(result.toString());
                JSONObject js = new JSONObject(result.toString());
                id_order_5sim = js.getString("id");
                nomer_5sim = js.getString("phone");
                System.out.println(id_order_5sim);
                System.out.println(nomer_5sim);
        } catch (Exception e) {
        }
    }
    public static void getCode(String id) {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://5sim.net/v1/user/check/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3Mzc4MDQ3MDksImlhdCI6MTcwNjI2ODcwOSwicmF5IjoiMDA5ZGFkYjcxN2Q3M2UyMzI3NWM2NzNlYWQxNjA5YzYiLCJzdWIiOjIxMzIwOTN9.3ar4R_wA84ywKLTR-6apJQO9qxfRxd67h0KiFXRaLdtyLyCTfXrRWCT6P2_CQPuS9BnUAgm6Z8VQrWzUAIbYSFpck8Wtuzbzmc2vbPH8ymX_LQRtYy2wd3SACsByzJxoi_rE0FhclTPRG2grNR7GURwVp1NNAra5vZxOwT4Mv1FjFge8eM9SEio3W-zu0J_hnVK0gZNHu8MhCIWiFKMPEQNLhOUe8NxZHwF_jpS5cCTCv8SJh4PURrCutn_KK_8SWYZGzdI6DrLdDaiBq9lkBsyvo_7L_E-Ef0FrPv1LFjRmHpD9eCnRQTg9e7vdQmZEY22lsBu-RktPwyAai_VPnQ");
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        result.append(line);
                    }
                }
            System.out.println(result.toString());
            JSONObject js = new JSONObject(result.toString());
            JSONArray jsArray = new JSONArray(js.getJSONArray("sms"));
            JSONObject kode = (JSONObject) jsArray.get(0);
            System.out.println(kode.getString("code"));
            kode_verify_5sim = kode.getString("code");
        } catch (Exception e) {
        }
    }
}
