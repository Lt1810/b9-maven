package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePickerHomework {
	public static WebDriver driver;
	public static String url = "https://jqueryui.com/datepicker/";

	public static void main(String[] args) throws Exception {
		setup();
		chooseDate("january", "12", 2025);
		driver.quit();
	}

	public static void chooseDate(String toMonth, String toDate, int toYear) throws Exception {
		driver.get(url);
		driver.switchTo().frame(0);
		driver.findElement(By.id("datepicker")).click();// clicking on Date: bar

			// getting year and converting it to integer
		WebElement getYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']"));
		String textYear = getYear.getText();
		int currentyear = Integer.parseInt(textYear);

			// going to year
		if (currentyear > toYear) {
			while (currentyear > toYear) {
				driver.findElement(By.xpath("//span[text()='Prev']")).click();
				getYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']"));
				textYear = getYear.getText();
				currentyear = Integer.parseInt(textYear);
				Thread.sleep(500);
			}
		}
		if (currentyear < toYear) {
			while (currentyear < toYear) {
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				getYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']"));
				textYear = getYear.getText();
				currentyear = Integer.parseInt(textYear);
				Thread.sleep(500);
			}
		}
			// getting current month
		WebElement month = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"));
		String currentMonth = month.getText();
		int toMonthNum = month(toMonth);
		int currentMonthNum = month(currentMonth);
		System.out.println(currentMonthNum);
			//going to month
		if (currentMonthNum > toMonthNum) {
			while (!(currentMonth.toLowerCase().equals(toMonth.toLowerCase()))) {
				driver.findElement(By.xpath("//span[text()='Prev']")).click();
				month = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"));
				currentMonth = month.getText();
				System.out.println(currentMonth + " prev");
				Thread.sleep(500);
			}
		}
		else if (currentMonthNum < toMonthNum) {
			while (!(currentMonth.toLowerCase().equals(toMonth.toLowerCase()))) {
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				month = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"));
				currentMonth = month.getText();
				Thread.sleep(500);
			}
		}
			// converting toDate to integer
		int date = Integer.parseInt(toDate);
		driver.findElement(By.xpath("//a[@data-date='" + date + "']")).click();
		Thread.sleep(5000);
		System.out.println(currentMonth + "/" + date + "/" + currentyear);
	}
		//getting month index
	public static int month(String month) {
		int monthNum = 0;	//		0			1		2			3		4		5		6...
		String monthArray[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		for (int i = 0; i < monthArray.length; i++) {
			String monthName = monthArray[i];
			if (monthName.toLowerCase().equals(month.toLowerCase())) {// getting the possition number of toMonth
				i++;	//+1 to index 
				monthNum = i;
			}
		}
		return monthNum;
	}

	public static void setup() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}
}
