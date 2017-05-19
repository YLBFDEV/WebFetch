package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class C8Fetch extends BaseFetch {
    private static final Logger Log = Logger.getLogger(C8Fetch.class.getName());

    @Override
    public void startFetch(WebDriver driver) throws InterruptedException {
        driver.get("http://www.caom8.com/tv/61607.html");//越狱第五季
        Log.info(driver.getTitle());
//        Log.info(driver.getPageSource());

//        List<WebElement> driverElements = driver
//                .findElements(By.cssSelector("#ul1 > li"));
//        Log.info(driverElements.size());
//
//        for (WebElement webElement : driverElements) {
//            String down_url = webElement.findElement(By.cssSelector("a")).getAttribute("href");
//            String file_name = webElement.findElement(By.cssSelector("a")).getAttribute("title");
//            Log.info(file_name + "\t,\t" + down_url);
//        }
    }
}
