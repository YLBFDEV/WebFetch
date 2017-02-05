package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylbf_ on 2016/12/9.
 */
public class FetchTaobao extends BaseFetch {
    private static final Logger Log = Logger.getLogger(FetchTaobao.class.getName());

    @Override
    public void startFetch(WebDriver driver) {
        List<String> urls = new ArrayList<>();
        urls.add("https://s.click.taobao.com/t?e=m%3D2%26s%3DItGj%2BUnZUkUcQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMaCnOyjmkXWi8sviUM61dt0%2BeeqqMMUU9S2VxLG7ZT1AxRzM3f5FP1xVsrfJbTwluzzcbSAxHOhCbbyzn7yFDfyXNZRZuc2g6k5SszSTuFvWEJq13Y%2FopHOrr4sBBkALz8YOae24fhW0");
        urls.add("https://s.click.taobao.com/t?e=m%3D2%26s%3DWO1HmcUhKFMcQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMaCnOyjmkXWi8sviUM61dt0%2BeeqqMMUU9S2VxLG7ZT1AxRzM3f5FP1xVsrfJbTwluwyaL0wnyeUuDfYoLuReTf6XNZRZuc2g6k5SszSTuFvWWjGpBDj0XvGv%2BEI0gUwTNsYOae24fhW0");
        urls.add("https://s.click.taobao.com/t?e=m%3D2%26s%3D679SOTwpLJ0cQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMaCnOyjmkXWi8sviUM61dt0%2BeeqqMMUU9S2VxLG7ZT1AxRzM3f5FP1xVsrfJbTwluwKMdl2GJf12ygCbgbs1hZEK2pKIe%2BSnW4UTCT8olmq18wpmb%2BQqTeRuCF6WWep6TMYl7w3%2FA2kb");
        urls.add("https://s.click.taobao.com/t?e=m%3D2%26s%3Dk3dgkil1EAwcQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMaCnOyjmkXWi8sviUM61dt0%2BeeqqMMUU9S2VxLG7ZT1AxRzM3f5FP1xVsrfJbTwlu5ZlB9QvQTVXEpkjLslMH7iXNZRZuc2g6k5SszSTuFvWMNqKw0EUXRfOGK6dgmIeW8YOae24fhW0");
        urls.add("https://s.click.taobao.com/t?e=m%3D2%26s%3D6Z4VmnmwvJQcQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMaCnOyjmkXWi8sviUM61dt0%2BeeqqMMUU9S2VxLG7ZT1AxRzM3f5FP1xVsrfJbTwluz42znHt3E7osgXlZcPXPVMK2pKIe%2BSnW4UTCT8olmq13%2FV3KU9iKGJ2IQTs8Uskc8Yl7w3%2FA2kb");
        urls.add("https://s.click.taobao.com/t?e=m%3D2%26s%3D8XNBZcnV5KscQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMaCnOyjmkXWi8sviUM61dt0%2BeeqqMMUU9S2VxLG7ZT1AxRzM3f5FP1xVsrfJbTwlu8jJQqjSHwva0AdopUHhd%2FoAV4Err%2B0r%2B4L1FB5074mSIy47b9Hvx%2Bf3UPzq3pvrTyGFCzYOOqAQ");
        urls.add("https://s.click.taobao.com/t?e=m%3D2%26s%3DNOQF6dLWB90cQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMaCnOyjmkXWi8sviUM61dt0%2BeeqqMMUU9S2VxLG7ZT1AxRzM3f5FP1xVsrfJbTwluzzcbSAxHOhC3U6r3LhvayKXNZRZuc2g6k5SszSTuFvWpAJZwx6PMZRVcPuqpW6BN8YOae24fhW0");
        for (String url : urls) {
            driver.get(url);
            Log.info(driver.getTitle());
            WebElement webElement = driver.findElement(By.cssSelector("#J_UlThumb > li > div > a > img"));
            String imageUrl = webElement.getAttribute("data-src").replaceAll("_50x50.jpg", "_300x300.jpg");
            Log.info(String.format("http:%s", imageUrl));
        }
        driver.get("https://www.baidu.com");


//        List<WebElement> driverElements = driver
//                .findElements(By.cssSelector("#J_UlThumb > li.tb-selected > div > a > img"));
//        Log.info(driverElements.size());
//        for (WebElement webElement : driverElements) {
//            String down_url = webElement.findElement(By.className("down_url")).getAttribute("value");
//            String file_name = webElement.findElement(By.className("down_url")).getAttribute("file_name");
//            Log.info(file_name + "\t,\t" + down_url);
//        }
    }
}
