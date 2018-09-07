package com.sampleapplication.debanka.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//Test Case to Add a Computer
public class AddComputer {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); // Initialize Chrome driver to run the application in Google Chrome
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://computer-database.herokuapp.com/computers"); // Open the Sample Application
		driver.manage().window().maximize();
		Thread.sleep(1000);

		String pageTitle = driver.getTitle();
		String actualTitle = "Computers database";

		if (pageTitle.equalsIgnoreCase(actualTitle)) {   	// Check if the correct page is loaded
			driver.findElement(By.id("add")).click();   	// Click Add a Computer
			List<WebElement> pageHeader = driver.findElements(By.xpath("//h1[contains(text(), 'Add a computer')]"));
			if (pageHeader.size() > 0) {
				Thread.sleep(1000);
				driver.findElement(By.id("name")).sendKeys("DR Super Computer"); 			//Enter Computer Name
				driver.findElement(By.id("introduced")).sendKeys("2018-09-05"); 			//Enter Introduced Date
				driver.findElement(By.id("discontinued")).sendKeys("2018-09-10"); 			//Enter Discontinued Date
				driver.findElement(By.xpath("//*[@id='company']//*[@value='1']")).click();  // Select the Company
				driver.findElement(By.className("btn")).click();						    //Click Add a Computer button
				String successMessage = driver.findElement(By.xpath("//*[@class='alert-message warning']")).getText();
				String actualMessage = "Done! Computer DR Super Computer has been created";
				Thread.sleep(1000);

				if (successMessage.equalsIgnoreCase(actualMessage)) {		//Check if the Computer was added successfully
					System.out.println("Computer Added Successfully!");
					driver.findElement(By.id("searchbox")).sendKeys("DR Super Computer"); 
					driver.findElement(By.id("searchsubmit")).click(); 
					List<WebElement> count = driver.findElements(By.xpath("//*[@class='pagination']//*[@class='current']//*"));
					Thread.sleep(1000);
					if (count.size() > 0) {
						System.out.println("The Added Computer is found in the list!");
					}
					else {
						System.out.println("The Added Computer is not found in the list! Test Case Failed!");
					}
				}
				else {
					System.out.println("Computer Not Added! Test Case Failed!");
				}
			}
			else {
				System.out.println("Unable to reach Add a computer page! Test Case Failed!");
			}
		}

		else {
			System.out.println("Unable to Launch the application! Test Case Failed!");
		}
		driver.close();	//Close the window
	}
}