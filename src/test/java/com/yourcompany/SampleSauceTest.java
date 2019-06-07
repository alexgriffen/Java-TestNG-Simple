package com.yourcompany;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.jna.platform.win32.Sspi.TimeStamp;

import static org.testng.Assert.assertEquals;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class SampleSauceTest {

	  public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	  public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	  public static long beforeSession;
	  public static long afterSession;
	  
	@Test
    public static void main() throws IOException {

		TimeStamp timeStamp = new TimeStamp();
		
        DesiredCapabilities caps = new DesiredCapabilities();
        	caps.setCapability("platform", "macOS 10.12");
        	caps.setCapability("version", "58.0");
        	caps.setCapability("browserName", "chrome");
        
//        	caps.setCapability("chromedriverVersion", "2.26");
//        	caps.setCapability("seleniumVersion", "3.3.0");
//        	
//        	caps.setCapability("appiumVersion", "1.6.4");
//        	caps.setCapability("deviceName","iPhone Simulator");
//        	caps.setCapability("deviceOrientation", "portrait");
//        	caps.setCapability("browserName", "Safari");
//        	caps.setCapability("platformVersion", "10.3");
//        	caps.setCapability("platformName","iOS");
//        	caps.setCapability("app", "sauce-storage:Meridian.zip");
//        	caps.setCapability("idleTimeout", "1000");
//        	caps.setCapability("appActivity", "com.docusign.ink.WelcomeActivity");
//        	caps.setCapability("appPackage", "com.docusign.ink");
//        	caps.setCapability("autoAcceptAlerts", true);
//        
//        	caps.setCapability("testobject_appium_version", "1.6.4");
//        	caps.setCapability("testobject_device", "iPhone_SE_10_2_POC111"); 
//        	caps.setCapability("testobject_cache_device", true);
//        	caps.setCapability("automationName", "XCUITest");
//        	caps.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY")); //specific project's API Key here

//--------------------------------------------------------------------------------------------------------------------------------------
    
  		  beforeSession = System.currentTimeMillis();
		  System.out.println("BEFORE SESSION " + beforeSession);
//		  AndroidDriver driver = new AndroidDriver<WebElement>(new URL("https://" + USERNAME +":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub"), caps);
//		  AndroidDriver driver = new AndroidDriver<WebElement>(new URL("https://us1.appium.testobject.com/wd/hub"), caps);
//		  IOSDriver driver = new IOSDriver<WebElement>(new URL("https://" + USERNAME +":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub"), caps);
//		  IOSDriver driver = new IOSDriver<WebElement>(new URL("https://us1.appium.testobject.com/wd/hub"), caps);
		  WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

     
//		  WebDriverWait wait = new WebDriverWait(driver, 30);
//		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  System.out.println("SESSION HAS STARTED - SESSION CREATION TIME: " + (System.currentTimeMillis() - beforeSession));
		  
//-----------------------------------------------------------------------------------------------------------------------------------

//Test Goes Here
	driver.get("https://saucelabs.com"); //simple example for web app
       
//Sleep mechanism to help visually confirm results in the video playback
       try{
       	   Thread.sleep(10000);
       	  }catch (InterruptedException ie1) {
       	    //ie1.printStackTrace();
       	  } 

        driver.quit();
      }

}
   
