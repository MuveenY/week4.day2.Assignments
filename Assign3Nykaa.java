package week4.day2.Assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assign3Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		//driver.get("https://www.nykaa.com/brands/loreal-paris/c/595?eq=desktop");
		//driver.get("https://www.nykaa.com/l-oreal-paris-color-protect-shampoo/p/6282?categoryId=595&pps=14&productId=6282&ptype=product&skuId=3102");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Mouse hover on brands
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));

		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		// Mouse hover on popular
		WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
		builder.moveToElement(popular).perform();
		// click L'oreal paris
		driver.findElement(By.xpath("(//li[@class='brand-logo menu-links'])[5]//img")).click();
		// go to new window
		Set<String> windows = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windows);
		driver.switchTo().window(windowsList.get(1));
		// click sort by and select customer top rated
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']/following-sibling::div")).click();
		Thread.sleep(2000);
		// click category and click shampoo
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//label[@for='chk_Shampoo_undefined']//div")).click();
		// check whether the filter is applied with shampoo
		String filters = driver.findElement(By.xpath("//span[text()='filters applied']/following::li")).getText();
		System.out.println(filters);
		if (filters.contains("Shampoo"))
			System.out.println("Filters Applied");
		else
			System.out.println("Filters Not Applied");
		// click on L'oreal paris colour protect shampoo
		WebElement oreal = driver
				.findElement(By.xpath("(//span[contains(text(), 'Oreal Paris Colour Protect Shampoo')])[1]"));
		String text = oreal.getText();

		oreal.click();
		// go to new window and select size as 175ml
		Set<String> windows1 = driver.getWindowHandles();
		List<String> windowsList1 = new ArrayList<String>(windows1);
		driver.switchTo().window(windowsList1.get(2));

		WebElement size = driver.findElement(By.xpath("//span[text()='175ml']"));
		if (size.isSelected())
			System.out.println("175 ML is default selected");
		else {
			size.click();
			System.out.println("175 ML is Selected Now");
		}
		// print the MRP of the product
		String price2 = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[1]")).getText();
		System.out.println(" MRP Price : " + price2);
		// click on add to bag
		driver.findElement(By.xpath("//div[@class='pull-left']//button")).click();
		// go to shopping bag
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		Thread.sleep(2000);
		// print grand total amount
		String grandTotal = driver.findElement(By.xpath("//div[@class='first-col']/div")).getText();
		System.out.println("Final Price : " + grandTotal);
		String total = grandTotal.replaceAll("\\D", "");
		// close the server pop-up
		driver.findElement(By.xpath("//button[text()='Close']")).click();
		// click on proceed
		driver.findElement(By.xpath("//div[@class='second-col']/button")).click();

		// click on continue as guest

		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		// check the grand total is same as before grand total
		String grandTotal2 = driver.findElement(By.xpath("(//div[text()='Grand Total']/following::span)[1]")).getText();
		System.out.println("Grand Total : " + grandTotal2);
		String finalPrice = grandTotal2.replaceAll("\\D", "");

		if (total.equals(finalPrice))
			System.out.println("Both Prices are Same");
		else
			System.out.println("Both prices are not Same");
		// close all the windows
		Set<String> allwindows = driver.getWindowHandles();
		for (String eachwindow : allwindows) {
			driver.switchTo().window(eachwindow).close();
		}

	}

}
