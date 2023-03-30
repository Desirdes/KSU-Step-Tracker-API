package com.ksupwlt.stepcounttracker.libraries;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URL;


@Configuration
//@Profile("!remote")
public class WebDriverLibrary {
    @Value("${remote.url}")
    private URL remoteUrl;


    @Value("${browser}")
    private String browser;

    @Bean
    public WebDriver getChromeDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(chromeOptions);
    }
//    @Bean
//    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
//    public WebDriver getRemoteWebDriverForChrome(){
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setCapability("browserVersion", "111");
//        chromeOptions.setCapability("platformName", "Windows 11");
//        return new RemoteWebDriver(remoteUrl, chromeOptions);
//}

}
