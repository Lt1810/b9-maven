package demo;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageTest {

	@Test
	public void loginPageTest() throws InterruptedException {
		String url = "https://ntkhr.noortecktraining.com/web/index.php/auth/login";
		String username = "ntk-admin";
		String password = "Ntk-orange!admin.123";
		String expPageTitle = "OrangeHRM";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(url);

		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();

		Thread.sleep(3000);

		String actPageTitle = driver.getTitle();

		if (expPageTitle.equals(actPageTitle)) {
			System.out.println("Page validation PASSED: [EXP] " + expPageTitle + " [ACT] " + actPageTitle);
		} else {
			System.out.println("Page validation FAILED: [EXP] " + expPageTitle + " [ACT] " + actPageTitle);

		}

		Thread.sleep(3000);
		driver.quit();
	}

}