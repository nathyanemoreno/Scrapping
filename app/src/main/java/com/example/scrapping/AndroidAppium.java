package com.example.scrapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidAppium {

    WebDriver webDriver;

//    @Before
    public void setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "Nexus 5");
        cap.setCapability("platformName", "Android");
        cap.setCapability("appPackage", "com.scores365");
        cap.setCapability("appActivity", "com.scores365.ui.Splash");

        webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723"), cap);
    }
}