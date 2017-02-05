package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Fetch2tuys extends BaseFetch {
    private static final Logger Log = Logger.getLogger(Fetch2tuys.class.getName());

    @Override
    public void startFetch(WebDriver driver) {
        driver.get("http://www.2tuys.com/view/26358.html");//锦绣未央
        Log.info(driver.getTitle());
        List<WebElement> driverElements = driver
                .findElements(By.cssSelector("#ul1 > li"));
        Log.info(driverElements.size());

        for (WebElement webElement : driverElements) {
            String down_url = webElement.findElement(By.cssSelector("a")).getAttribute("href");
            String file_name = webElement.findElement(By.cssSelector("a")).getAttribute("title");
            Log.info(file_name + "\t,\t" + down_url);
        }
    }
}
