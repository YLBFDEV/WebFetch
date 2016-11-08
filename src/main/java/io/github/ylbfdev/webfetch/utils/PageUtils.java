package io.github.ylbfdev.webfetch.utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
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
		if (Constants.WEB_DRIVER.equals("htmlunit")) {
			driver = new HtmlUnitDriver();
			return driver;
		} else if (Constants.WEB_DRIVER.equals("chrome")) {
			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File(Constants.DRIVER_PATH)).usingAnyFreePort().build();
			try {
				service.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 设置 chrome 的路径
			// 下载镜像地址http://npm.taobao.org/mirrors/chromedriver
			System.setProperty(Constants.CHROME_DRIVER_KEY, Constants.DRIVER_PATH);
			driver = new ChromeDriver();
			return driver;
		} else if (Constants.WEB_DRIVER.equals("firefox")) {
			System.setProperty(Constants.FIREFOX_DRIVER_KEY, Constants.DRIVER_PATH);
			// 创建一个 FireFox 的浏览器实例
			driver = new FirefoxDriver();
			return driver;
		} else if (Constants.WEB_DRIVER.equals("ie")) {
			driver = new InternetExplorerDriver();
			return driver;
		} else {
			Log.error("Unknown web driver type: " + Constants.WEB_DRIVER + ".\n Exit...");
			return null;
		}
		// driver = new EventFiringWebDriver(new ChromeDriver()).register(new
		// LogEventListener());
	}

}
