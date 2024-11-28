/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arezdev.siwalandeveloper.api;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author AREZDEV
 */
public class appium_reg {
    
    public static AndroidDriver driver;
    
public static String[] jeneng = {"Katie","Giuliana","Aylin","Annika","Amiyah","Meghan","Izabella","Julianne","Livia","Zariah","Lucy","Casey","Mikaela","Naomi","Jordin","Lainey","Annabelle","Bailey","Isabelle","Sage","Ally","Alaina","Thalia","Amaya","Clara","Summer","Azul","Kathryn","Joyce","Jaylynn","Litzy","Lea","Kiersten","Yuliana","Catalina","Brynn","Matilda","Hannah","Sydnee","Angelique","Keely","Kamari","Breanna","Aaliyah","Ava","Macey","Athena","Juliet","Amani","Alisha","Mya","Jamya","Giana","Eileen","Hana","Kali","Tara","Marlene","Kelsie","Ariel","Vanessa","Mercedes","Lana","Averi","Savanna","Jaidyn","Zoe","Elliana","Daniella","Belen","Eve","Mariela","Jazmyn","Allison","Margaret","Mira","Cecelia","Imani","Leia","Elisabeth","Jaylen","Saniya","Francesca","Lindsey","Carly","Genevieve","Adelaide","Sarah","Myah","Aubrey","Brylee","Aniyah","Cara","Kaitlynn","Alice","Kaitlin","Alison","Maritza","Alanna","Adriana","Brenna","Aileen","Julissa","Emery","Nylah","Gina","Hillary","Luciana","Joy","Evelyn","Deborah","Emelia","Marin","Joselyn","Yadira","Laylah","Mattie","Ashtyn","Elsie","Maddison","Abbie","Sasha","Nyla","Jaylee","Hadassah","Rachael","Eva","Kasey","Johanna","Moriah","Kayden","Nora","Presley","Mina","Maya","Leila","Isabela","Selena","Emmalee","Charlie","Akira","Natalia","Jaylah","Karla","London","Sandra","Paris","Kaitlyn","Laurel","Genesis","Mckinley","Isabell","Marilyn","Shayla","Viviana","Shaylee","Jaiden","Natalie","Kamora","Kamryn","Caitlyn","Lillie","Jada","Makena","Briley","Emely","Mikayla","Anne","Lexi","Harper","Anastasia","Joslyn","Cloe","Crystal","Donna","Anika","Gianna","Katelyn","Lily","Skyler","Iliana","Saniyah","Hazel","Taylor","Belinda","Ciara","Kelsey","Madeleine","Lia","Alina","Angel","Kailyn","Brooklynn","Kiley","Lara","Scarlett","Mylie","Regina","Lila","Greta","Leslie","Zoey","Karen","Ashlynn","Autumn","Alejandra","Paula","Sara","Aurora","Gracelyn","Paola","Mckenna","Paityn","Gracie","Monica","Tiara","Aspen","Nayeli","Estrella","Lauryn","Selah","Alisson","Rowan","Yoselin","Camilla","Aimee","Jazlynn","Maribel","Alyvia","Shyann","Giovanna","Lola","Liliana","Arianna","Cierra","Laila","Alicia","Lorelei","Sherlyn","Eliana","Elaina","Amaris","Bria","Melina","Aiyana","Carla","Carley","Yamilet","Halle","Cameron","Larissa","Kimberly","Marina","Haylie","Madilynn","Sierra","Sadie","Cassidy","Kaliyah","Natasha","Amy","Elyse","Perla","Lorena","Roselyn","Evie","Julie","Amelie","Rose","Amelia","Jadyn","Quinn","Amirah","Campbell","Essence","Ashley","Sarai","Dahlia","Rosa","Amanda","Mireya","Tabitha","Journey","Abigail","Celeste","Gloria","Hayden","Mariah","Aleena","Melany","Adelyn","Katrina","Skyla","Katherine","Amya","Adeline","Abril","Jocelyn","Tori","Nathalia"};
      
    public static void startCreate(String devices) throws MalformedURLException, InterruptedException, IOException, URISyntaxException {
        
        int a = (int) Math.floor(Math.random() * jeneng.length);
        int b = (int) Math.floor(Math.random() * jeneng.length);
        String Firstname = jeneng[a];
        String Lastname = jeneng[b];
        
             //driver.installApp("C:\\Users\\AREZDEV\\Documents\\DATAKODINGJAVA\\APK\\lite.apk");
            
            Thread.sleep(15000);
            
            //create new acc
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Buat akun baru\")")).click();
            driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Buat akun baru\"]")).click();

            Thread.sleep(9000);
            //mulai
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Mulai\")")).click();
            Thread.sleep(3000);
            //jeneng ngarep
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).click();
            Thread.sleep(3000);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).sendKeys(Firstname);
            Thread.sleep(3000);
            //jeneng nguri
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")).click();
            Thread.sleep(3000);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")).sendKeys(Lastname);
            
            //next
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Berikutnya\")")).click();
            Thread.sleep(3000);
            
            //tgl lahir
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2023\")")).click();
            Thread.sleep(200);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2022\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2021\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2020\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2019\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2018\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2017\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2016\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2015\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2014\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2013\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2012\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2011\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2010\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2009\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2008\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2007\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2006\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2005\")")).click();
            Thread.sleep(100);
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2004\")")).click();
            //set tgl lahir
            driver.findElement(AppiumBy.id("android:id/button1")).click();
            
            //next
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Berikutnya\")")).click();
            Thread.sleep(3000);
            
            //jenis kelamin
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Perempuan\")")).click();
            
            //next
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Berikutnya\")")).click();
            Thread.sleep(4000);
            
            //act permission
            driver.findElement(AppiumBy.id("com.android.packageinstaller:id/permission_deny_button")).click();
            Thread.sleep(6000);
            
            //daftar dengan email
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Daftar dengan email\")")).click();
            Thread.sleep(3000);
            
            //isi email
            driver.findElement(AppiumBy.className("android.widget.EditText")).click();
            Thread.sleep(3000);
            //getMail server api
            //esekr.getMail(devices,false);
            //driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(esekr.emailesekr);
            Thread.sleep(3000);
            
            //next
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Berikutnya\")")).click();
            Thread.sleep(5000);
            
            //isi password
            driver.findElement(AppiumBy.className("android.widget.EditText")).click();
            Thread.sleep(3000);
            driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("TEORINGELID");
            
            //next
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Berikutnya\")")).click();
            Thread.sleep(5000);
            
            //save device
            driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Simpan\"]")).click();
            //driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(5)")).click();
            //driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Simpan\")")).click();
            Thread.sleep(5000);
            
            //aggre
            driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Saya setuju\"]")).click();
            Thread.sleep(Reg.delaySignup * 1000);
            
            //act permission
            driver.findElement(AppiumBy.id("com.android.packageinstaller:id/permission_deny_button")).click();
            Thread.sleep(6000);
            
            //klik box code
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\")")).click();
            Thread.sleep(5000);
            
            //fill code
            //esekr.getMail(devices, true);
            Thread.sleep(Reg.delayWaitkode * 1000);
            //driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\")")).sendKeys(esekr.kodeesekr);
            Thread.sleep(5000);
            
            //OK signup !
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(3)")).click();    
    }

}
