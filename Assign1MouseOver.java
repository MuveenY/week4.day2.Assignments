package week4.day2.Assignments;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assign1MouseOver {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement courses = driver.findElement(By.xpath("//a[text()='TestLeaf Courses']"));
		
		
		Actions builder = new Actions(driver);
		
		builder.moveToElement(courses).perform();
		
		List<WebElement> findElements = driver.findElements(By.xpath("//a[@class='listener']"));
		Set<String> names = new LinkedHashSet<String>();
		for (WebElement eachname : findElements) {
			String text = eachname.getText();
			names.add(text);
		}
		System.out.println("List of Courses : " + names);
		
		driver.findElement(By.xpath("(//a[@class='listener'])[1]")).click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();

		
		

	}

}
