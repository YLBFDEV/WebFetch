package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FetchQzone extends BaseFetch {
    private static final Logger Log = Logger.getLogger(FetchQzone.class.getName());

    @Override
    public void startFetch(WebDriver driver) {
        driver.get("http://2577344878.qzone.qq.com/311");
        Log.info(driver.getTitle());
        // div.f-info
        List<WebElement> driverElements = driver.findElements(By.className("bgr3"));
        for (WebElement webElement : driverElements) {
            String down_url = webElement.findElement(By.cssSelector("a.c_tx.c_tx3.goDetail")).getAttribute("href");
            String file_name = webElement.findElement(By.className("content")).getText();
            Log.info(file_name + "\t,\t" + down_url);
        }
    }

    public static void setScroll(WebDriver driver, int height) {
        try {
            String setscroll = "document.documentElement.scrollBottom=" + height;
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript(setscroll);
        } catch (Exception e) {
            System.out.println("Fail to set the scroll.");
        }
    }


    private void getic2(WebDriver driver, String url) {
        //driver.get("http://ic2.s21.qzone.qq.com/cgi-bin/feeds/feeds_html_module?i_uin=2577344878&i_login_uin=748034584&mode=4&previewV8=1&style=8&version=8&needDelOpr=true&transparence=true&hideExtend=false&showcount=10&MORE_FEEDS_CGI=http%3A%2F%2Fic2.s21.qzone.qq.com%2Fcgi-bin%2Ffeeds%2Ffeeds_html_act_all&refer=2&paramstring=os-winxp|100");
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Log.info(driver.getTitle());
        //Log.info(driver.getPageSource());
        // div.f-info
        List<WebElement> driverElements = driver.findElements(By.className("f-item"));
        for (WebElement webElement : driverElements) {
            String down_url = webElement.findElement(By.className("f-video-wrap")).getAttribute("url3");
            String file_name = webElement.findElement(By.className("f-info")).getText();
            Log.info(file_name + "\t,\t" + down_url);
        }
    }
}
