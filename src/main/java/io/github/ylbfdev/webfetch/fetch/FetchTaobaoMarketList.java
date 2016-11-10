package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 淘宝首页行业市场主题市场分类抓取
 * Created by ylbf_ on 2016/11/9.
 */
public class FetchTaobaoMarketList extends BaseFetch {
    private static final Logger Log = Logger.getLogger(FetchTaobaoMarketList.class.getName());

    @Override
    public void startFetch(WebDriver driver) {
        driver.get("https://www.taobao.com/markets/tbhome/market-list?spm=a21bo.7723596.8176.2.AA0jaI");
        Log.info(driver.getTitle());
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getHotRecommend(driver);
        getCategoryList(driver);
    }

    /**
     * 淘宝首页行业市场分类
     *
     * @param driver
     */
    private void getCategoryList(WebDriver driver) {
        List<WebElement> driverElements = driver.findElements(By.cssSelector("div.home-category-list.J_Module > div"));
        for (WebElement webElement : driverElements) {
            String category_name = webElement.findElement(By.cssSelector("a")).getText();
            //一级分类
            Log.info(category_name + ",,");
            List<WebElement> list_item = webElement.findElements(By.className("category-list-item"));
            for (WebElement list_itemElement : list_item) {
                String category_name2 = list_itemElement.findElement(By.cssSelector("a.category-name")).getText();
                //二级分类
                Log.info(category_name + "," + category_name2 + ",");
                List<WebElement> list_item2 = list_itemElement.findElements(By.cssSelector("div > a.category-name"));
                for (WebElement list_itemElement2 : list_item2) {
                    //三级分类
                    Log.info(category_name + "," + category_name2 + "," + list_itemElement2.getText());
                }
            }
        }
    }

    /**
     * 抓取热门推荐
     *
     * @param driver
     */
    private void getHotRecommend(WebDriver driver) {
        WebElement element = driver.findElement(By.cssSelector("div.home-hot-recommend.J_Module > div"));
        String module_title = element.findElement(By.className("module-title")).getText();
        Log.info(module_title);
        List<WebElement> keywordElements = element.findElements(By.className("keyword"));
        for (WebElement webElement : keywordElements) {
            String down_url = webElement.getAttribute("href");
            String file_name = webElement.getText();
            Log.info(file_name + "\t,\t" + down_url);
        }
    }
}
