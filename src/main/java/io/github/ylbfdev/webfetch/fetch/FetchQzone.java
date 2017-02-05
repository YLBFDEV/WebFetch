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
        //driver.get("https://h5.qzone.qq.com/mqzone/profile?hostuin=2577344878");
        //driver.get("http://2577344878.qzone.qq.com/311");
        driver.get("http://user.qzone.qq.com/2577344878/main");
        Log.info(driver.getTitle());
        getQM_Feeds_Iframe(driver, "QM_Feeds_Iframe");
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


    /**
     * 切换到Iframe内部
     *
     * @param dr        driver
     * @param frameName Iframe ID名称
     */
    private void getQM_Feeds_Iframe(WebDriver dr, String frameName) {
        //视频地址
        String tencentVideoPlayerUrl = "http://imgcache.qq.com/tencentvideo_v1/player/TPQzone.swf" +
                "?vid=%s" + // vid参数
                "&skin=http://imgcache.qq.com/minivideo_v1/vd/res/skins/QzoneMiniSkin.swf" + // 皮肤
                "&auto=1" + // 自动播放
                "&mute=1" + // 有声没声
                "&list=2" +
                "&share=0" +
                "&showend=0" +
                "&showcfg=0" +
                "&shownext=0";
        // QM_Feeds_Iframe
        WebDriver driver = dr.switchTo().frame(frameName);
        Log.info(driver.getPageSource());
        // f-s-i
        List<WebElement> driverElements = driver.findElements(By.className("f-s-i"));
        for (WebElement webElement : driverElements) {
            String vid = webElement.findElement(By.cssSelector("div > div.f-ct > div > div.img-box.f-video-wrap > a")).getAttribute("href");
            String down_url = String.format(tencentVideoPlayerUrl, vid.substring(vid.lastIndexOf("/") + 1));
            String file_name = webElement.findElement(By.className("f-info")).getText();
            Log.info(file_name + "\t,\t" + down_url);
        }
    }
}
