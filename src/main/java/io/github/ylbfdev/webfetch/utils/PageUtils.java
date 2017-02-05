package io.github.ylbfdev.webfetch.utils;

import java.io.File;
import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.ylbfdev.webfetch.constants.Constants;

public class PageUtils {
    private static final Logger Log = Logger.getLogger(PageUtils.class.getName());
    private static ChromeDriverService service;

    public static void serviceStop() {
        if (service != null && service.isRunning())
            service.stop();
    }

    public static WebDriver getWebDriver() {
        WebDriver driver;
        switch (Constants.WEB_DRIVER) {
            case "htmlunit":
                driver = new HtmlUnitDriver();
                //driver = new HtmlUnitDriver(BrowserVersion.CHROME);
                break;
            case "chrome":
                service = startChromeDriverService();
                // 设置 chrome 的路径
                // 下载镜像地址http://npm.taobao.org/mirrors/chromedriver
                System.setProperty(Constants.CHROME_DRIVER_KEY, Constants.DRIVER_PATH);
                driver = getWebChromeDriver(true);
                break;
            case "firefox":
                System.setProperty(Constants.FIREFOX_DRIVER_KEY, Constants.DRIVER_PATH);
                // 创建一个 FireFox 的浏览器实例
                driver = new FirefoxDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            default:
                Log.error("Unknown web driver type: " + Constants.WEB_DRIVER + ".\n Exit...");
                return null;
        }
        return driver;
    }

    /**
     * 启动ChromeDriverService
     */
    private static ChromeDriverService startChromeDriverService() {
        try {
            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(Constants.DRIVER_PATH)).usingAnyFreePort().build();
            service.start();
            return service;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param isPhone user agent 是否是手机
     * @return WebDriver
     */
    private static WebDriver getWebChromeDriver(boolean isPhone) {
        if (isPhone) {
            return new ChromeDriver();
        } else {
            //声明chromeoptions,主要是给chrome设置参数
            ChromeOptions options = new ChromeOptions();
            //设置user agent
            options.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
            //设置用户文件夹
            //options.addArguments("--user-data-dir=C:\\Users\\ylbf_\\AppData\\Local\\Google\\Chrome\\User Data");
            return new ChromeDriver(options);
        }
    }

}
