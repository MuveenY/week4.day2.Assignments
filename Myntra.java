package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		String title = driver.getTitle();
		System.out.println(title);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//mouse hover on men
		WebElement men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();
//click jackets
		driver.findElement(By.xpath("//a[text()='Jackets']")).click();
//find the total count of items
		String totalItems = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		Thread.sleep(2000);
		String totalItems1 = totalItems.replaceAll("[^0-9]", "");
		int count = Integer.parseInt(totalItems1);
		System.out.println("Total count Jackets for Men Found : " + count);
//check normal jackets count
		String normalJackets = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String normalJackets1 = normalJackets.replaceAll("[^0-9]", "");
		int count1 = Integer.parseInt(normalJackets1);
		System.out.println("Total count of normal Jackets : " + count1);

//check rain jackets count		
		String rainJackets = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String rainJackets1 = rainJackets.replaceAll("[^0-9]", "");
		int count2 = Integer.parseInt(rainJackets1);
		System.out.println("Total count Jackets for Men Found : " + count2);
//verify the total count with normal and rain jackets
		int count3 = count1 + count2;
		if (count == count3)
			System.out.println("Count Matches");
		else
			System.out.println("Count Not Matches");
		Thread.sleep(2000);
//check jackets
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		Thread.sleep(2000);
//click +more option under brand
		driver.findElement(By.xpath("(//div[@class='brand-more'])[1]")).click();
//type duke 
		driver.findElement(By.xpath("(//input[@class='FilterDirectory-searchInput'])[1]")).sendKeys("Duke");
//click the checkbox
		driver.findElement(
				By.xpath("(//label[@class=' common-customCheckbox']/div[@class='common-checkboxIndicator'])[1]"))
				.click();
		// close the pop-up
		driver.findElement(By.xpath("(//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove'])"))
				.click();
//confirm all the coats are brand of duke
		List<WebElement> brandName = driver.findElements(By.xpath("//h3[text()='Duke']"));
		for (int i = 0; i < brandName.size(); i++) {
			String text = brandName.get(i).getText();
			if (text.equals("Duke"))
				System.out.println("Product brand Name is Duke");
			else
				System.out.println("Product brand Name is Not Duke");
		}
		// click on sort option
		WebElement sort = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(sort).perform();
		// click on better discount
		driver.findElement(By.xpath("(//label[@class='sort-label '])[3]")).click();
		Thread.sleep(2000);
		// find the price of first displayed item
		String firstProd = driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).getText();
		System.out.println("First Product Price : " + firstProd);
		// click on first product
		driver.findElement(By.xpath("(//div[@class='product-productMetaInfo'])[1]")).click();
		// go to the next window
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> windowList1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(windowList1.get(1));
		// take the screenshot
		File screen = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenShot/Myntra.png");
		FileUtils.copyFile(screen, dest);
		// click on wishlist
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		// close browser
		driver.close();

	}

}
