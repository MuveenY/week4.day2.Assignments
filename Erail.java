package week4.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Erail {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		String title = driver.getTitle();
		System.out.println(title);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement from = driver.findElement(By.xpath("//input[@id='txtStationFrom']"));
		from.clear();
		from.sendKeys("MS", Keys.ENTER);

		WebElement to = driver.findElement(By.xpath("//input[@id='txtStationTo']"));
		to.clear();
		to.sendKeys("MDU", Keys.ENTER);

		driver.findElement(By.xpath("//input[@id='chkSelectDateOnly']")).click();
		Thread.sleep(2000);

		WebElement trainTable = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']"));

		List<WebElement> trainList = trainTable.findElements(By.tagName("tr"));
		System.out.println("No. of Trains : " + trainList.size());

		List<String> nameList = new ArrayList<String>();

		for (int i = 0; i < trainList.size(); i++) {
			WebElement eachRow = trainList.get(i);
			List<WebElement> eachData = eachRow.findElements(By.tagName("td"));
			String trainNo = eachData.get(0).getText();
			String trainName = eachData.get(1).getText();
			System.out.println(i + " " + trainNo + " " + trainName);
			nameList.add(trainName);
		}
		Collections.sort(nameList);
		System.out.println("Sorted List");
		System.out.println(nameList);

	}

}
