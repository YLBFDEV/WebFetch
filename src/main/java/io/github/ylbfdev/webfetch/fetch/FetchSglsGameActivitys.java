package io.github.ylbfdev.webfetch.fetch;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FetchSglsGameActivitys extends BaseFetch {
    private static final Logger Log = Logger.getLogger(FetchSglsGameActivitys.class.getName());

    @Override
    public void startFetch(WebDriver driver) {
        driver.get("http://bbs.open.qq.com/forum-3109-1.html");
        Log.info(driver.getTitle());
        String pageStr = driver.findElement(By.cssSelector("#fd_page_top > div > label > span")).getAttribute("title");
        Log.info(pageStr);
        int totalPage = Integer.parseInt(pageStr.replaceAll("[^0-9]", ""));
        totalPage = 1;
        for (int i = 0; i < totalPage; i++) {
            Log.info(driver.getCurrentUrl());
            List<WebElement> driverElements = driver
                    .findElements(By.cssSelector("#moderate > table >tbody > tr > th > a"));
            driverElements.stream()
                    .filter(webElement -> webElement.getText().contains("活动"))
                    .forEach(webElement -> Log.info(webElement.getText()));
            try {
                driver.findElement(By.className("nxt")).click();
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
