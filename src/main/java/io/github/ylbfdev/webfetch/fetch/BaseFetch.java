package io.github.ylbfdev.webfetch.fetch;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.github.ylbfdev.webfetch.utils.PageUtils;

public abstract class BaseFetch {
    private static final Logger Log = Logger.getLogger(BaseFetch.class.getName());

    /**
     * 初始化
     */
    public void init() {
        WebDriver driver = PageUtils.getWebDriver();
        if (driver == null) {
            Log.error("初始化WebDirver失败");
            return;
        }
        // 开始抓取
        try {
            startFetch(driver);
        } catch (Exception e) {
            Log.info(driver.getPageSource());
            e.printStackTrace();
        } finally {
            driver.close();
        }
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
            driver.quit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsatisfiedLinkError ignored) {
        } finally {
            PageUtils.serviceStop();
        }
    }

    /**
     * 开始抓取
     *
     * @param driver
     */
    public abstract void startFetch(WebDriver driver) throws InterruptedException;
}
