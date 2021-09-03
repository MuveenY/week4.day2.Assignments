package week4.day2.Assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assign4Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//mouse hover on men's fashion
		WebElement mensfashion = driver.findElement(By.xpath("(//span[contains(text(),'Men')])[2]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(mensfashion).perform();
		//go to sports shoes
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
		Thread.sleep(2000);
		//get the count of sports shoes
		String count = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		System.out.println("Total Number of Sports shoes :" + count);
		//click on training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(3000);
		//Sort by low to high
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		//driver.findElement(By.xpath("//div[@class='sort-drop clearfix']/i")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();
		Thread.sleep(3000);
		//check if the items displayed are sorted correctly 
		WebElement end = driver.findElement(By.xpath("//span[@class='btn-yes js-yesFeedback']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", end);
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -700)");
		
		Thread.sleep(5000);
		List<WebElement> pricesList = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		System.out.println("Total List : " + pricesList.size());
		Thread.sleep(2000);
		List<Integer> prices = new ArrayList<Integer>();
		
		for (WebElement eachname : pricesList) {
			String text = eachname.getText();
			String values = text.replaceAll("[^0-9]", "");
			int values1 = Integer.parseInt(values);
			prices.add(values1);
		}
		
		System.out.println("Prices List : "+ prices);
		Integer max = Collections.max(prices);
		System.out.println("Maximum value in the list : " + max);
		Integer lastValue = prices.get(prices.size()-1);
		System.out.println("last value of list : " + lastValue);
		if(max == lastValue)
			System.out.println("Prices are Sorted");
		else
			System.out.println("prices are not sorted");
		//mouse hover on jagroon gray shoe
		WebElement grayShoe = driver.findElement(By.xpath("//p[@title='JAGROON GYM MASTER Gray Training Shoes']"));
		builder.moveToElement(grayShoe).perform();
		Thread.sleep(2000);
		//click quick view button
		driver.findElement(By.xpath("(//div[@class='clearfix row-disc']/div)[3]")).click();
		//print the cost and discount percentage
		String finalprice = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println( "Final price of Shoe : " + finalprice);
		String discount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Discount on Shoe : " + discount);
		//take the snapshot
		File screen = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenShot/Snapdeal.png");
		FileUtils.copyFile(screen, dest);
		//close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
		//close the main window
		driver.quit();
		
	}

}
