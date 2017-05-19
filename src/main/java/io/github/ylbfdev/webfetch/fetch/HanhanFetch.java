package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Time;
import java.util.*;

/**
 * Created by ylbf_ on 2017/2/13.
 */
public class HanhanFetch extends BaseFetch {
    private static final Logger Log = Logger.getLogger(HanhanFetch.class.getName());
    private List<HashMap<String, String>> filmUrls = new ArrayList<>();

    @Override
    public void startFetch(WebDriver driver) throws InterruptedException {
        driver.get("https://www.hanhanfilm.com/film/detail?fid=c416d01f1f2247ccb0f7108cc3692544"); //孤单又灿烂的神：鬼怪
        Log.info(driver.getTitle());
        List<WebElement> driverElements = driver
                .findElements(By.cssSelector("div.wid-content > div > div:nth-child(2) > div > ul > li > a"));
        Log.info(driverElements.size());

        for (WebElement webElement : driverElements) {
            HashMap<String, String> film = new HashMap<>();
            String down_url = webElement.getAttribute("href");
            String file_name = webElement.findElement(By.tagName("span")).getText().replaceAll("【点击下载】", "").replaceAll("도깨비.E", "");
            film.put("file_name", file_name);
            film.put("down_url", down_url);
            filmUrls.add(film);
            // Log.info(file_name + "\t,\t" + down_url);
        }

        for (HashMap<String, String> filmUrl : filmUrls) {
            Thread.sleep(10*1000);
            driver.get(filmUrl.get("down_url"));
            Log.info(driver.getTitle());
            String magnetUrl = driver.findElement(By.cssSelector("#main-content > div > div.movie > div > div:nth-child(2) > div > center > a")).getAttribute("href");
            Log.info(filmUrl.get("file_name") + "\t,\t" + magnetUrl);
        }
    }
}
