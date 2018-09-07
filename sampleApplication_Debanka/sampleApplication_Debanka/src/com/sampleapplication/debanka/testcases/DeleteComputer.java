package com.sampleapplication.debanka.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//Test Case to Delete a Computer
public class DeleteComputer {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); // Initialize Chrome driver to run the application in Google Chrome
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://computer-database.herokuapp.com/computers"); // Open the Sample Application
		driver.manage().window().maximize();
		Thread.sleep(1000);

		String pageTitle = driver.getTitle();
		String actualTitle = "Computers database";

		if (pageTitle.equalsIgnoreCase(actualTitle)) {   	// Check if the correct page is loaded
			driver.findElement(By.id("searchbox")).sendKeys("DR Super Computer"); 
			driver.findElement(By.id("searchsubmit")).click(); 
			List<WebElement> count = driver.findElements(By.xpath("//*[@class='pagination']//*[@class='current']//*"));
			if (count.size() > 0) {
				driver.findElement(By.xpath("//*[@class='computers zebra-striped']//tbody//tr[1]//a[@href]")).click();
				List<WebElement> pageHeader = driver.findElements(By.xpath("//h1[contains(text(), 'Edit computer')]"));
				if(pageHeader.size() > 0){
					driver.findElement(By.xpath("//*[@value='Delete this computer']")).click();
					String successMessage = driver.findElement(By.xpath("//*[@class='alert-message warning']")).getText();
					String actualMessage = "Done! Computer has been deleted";
					Thread.sleep(1000);

					if (successMessage.equalsIgnoreCase(actualMessage)) {		//Check if the Computer was successfully deleted
						System.out.println("Computer Deleted Successfully!");
					}
					else {
						System.out.println("Computer Not Deleted! Test Case Failed!");
					}
				}
				else {
					System.out.println("Unable to reach Edit Computer page! Test Case Failed!");
				}
			}
			else {
				System.out.println("No matching computers found!");
		}}
		else {
			System.out.println("Unable to Launch the application! Test Case Failed!");
		}
		driver.close();	//Close the window
	}
}