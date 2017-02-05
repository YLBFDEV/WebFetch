package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ylbf_ on 2016/12/5.
 */
public class FetchBtkitty extends BaseFetch {
    private static final Logger Log = Logger.getLogger(FetchBtkitty.class.getName());

    @Override
    public void startFetch(WebDriver driver) {
        driver.get("http://btkitty.bid/torrent/BcGBEQAwBACxmShXxine_iM0ETpIooaLVSOni2eTSMmqt9kq_gE.html");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Log.info(driver.getTitle());
        try {
            driver.findElement(By.id("filelist_expand")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //执行JS脚本第一种方法
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('filelist_hidden').style.display='block';");

//        //执行JS脚本第二种方法
//        JavascriptExecutor  js = (JavascriptExecutor)driver;
//        WebElement element = driver.findElement(By.id("filelist_hidden"));
//        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
//        js.executeScript("arguments[0].style=arguments[1]",element,"display: block;");

        List<WebElement> driverElements = driver.findElement(By.className("filelist")).findElements(By.cssSelector("p"));
        Log.info(driverElements.size());

        for (WebElement webElement : driverElements) {
//            String down_url = webElement.findElement(By.className("down_url")).getAttribute("value");
            String file_name = webElement.findElement(By.className("filename")).getText();
            String file_size = webElement.findElement(By.className("size")).getText();
            Log.info(file_name + " ; " + file_size);
        }
    }
}
