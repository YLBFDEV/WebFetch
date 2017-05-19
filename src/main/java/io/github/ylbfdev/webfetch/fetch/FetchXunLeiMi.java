package io.github.ylbfdev.webfetch.fetch;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FetchXunLeiMi extends BaseFetch {
	private static final Logger Log = Logger.getLogger(FetchXunLeiMi.class.getName());

	@Override
	public void startFetch(WebDriver driver) {
		//driver.get("http://www.xunleimi.com/huayudianshiju/27806/");//大都市小爱情
		driver.get("http://www.xunleimi.com/huayudianshiju/19494/");//神医喜来乐
		Log.info(driver.getTitle());
		List<WebElement> driverElements = driver.findElements(By.cssSelector("#xun1 > table > tbody > tr > td > a"));
		for (WebElement webElement : driverElements) {
			String down_url = webElement.getAttribute("href");
			String file_name = webElement.getText();
			Log.info(file_name + "\t,\t" + down_url);
		}
	}
}
