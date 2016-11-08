package io.github.ylbfdev.webfetch.fetch;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Fetch520HD extends BaseFetch {
	private static final Logger Log = Logger.getLogger(Fetch520HD.class.getName());

	@Override
	public void startFetch(WebDriver driver) {
		driver.get("http://www.520hd.cc/view/play61837.html");
		Log.info(driver.getTitle());
		List<WebElement> driverElements = driver
				.findElements(By.cssSelector("#detail-list > div > div > ul > li"));
		Log.info(driverElements.size());

		for (WebElement webElement : driverElements) {
			String down_url = webElement.findElement(By.className("down_url")).getAttribute("value");
			String file_name = webElement.findElement(By.className("down_url")).getAttribute("file_name");
			Log.info(file_name + "\t,\t" + down_url);
		}
	}
}
