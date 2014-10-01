package com.onetesthub.perf.library.ui.app;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

import java.util.UUID;

import com.onetesthub.perf.adapter.ui.BaseUIPerfTest;
import com.onetesthub.perf.api.ui.PerfWebDriver;

public class PartPage extends BaseUIPerfTest {
	
	//generate random asset name
	String uuid;

	By btnNewPart = By.xpath("//button[contains(@class,'qa-btnCreatePartNum')]");
	
	By btnSavePart = By.xpath("//button[contains(@class,'qa-savePart')]");  
	
	By btnDeletePart = By.xpath("//button[contains(@class,'qa-deletePart')]");
	
	By partName = By.xpath("//input[contains(@class,'qa-partName')]");
	By partDesc = By.xpath("//input[contains(@class,'qa-partDesc')]");
	
	By partTable = By.xpath("//div[contains(@id,'partnumberTable')]");
	
	By wl = By.xpath("//div[contains(@class, 'toast-message')]");
	
	By Lnkpart = By.xpath("//a[contains(@class,'qa-linkToPartNumber')]");
	
	By viewSinglePart = By.xpath("//div[contains(@class,'ngCellText ng-scope col1 colt1')]/span");
	

	public void createPart(String uuid) {
		
		PageName = "Create Part";

		try {

			System.out.println("Create part to begin");
			
			driver.findElement(btnNewPart).click();
			
			System.out.println("Create part button click DONE");
			
			driverWait = new WebDriverWait(driver,webDriverTimeout);
			driverWait.until(ExpectedConditions.elementToBeClickable(btnSavePart));
			
			driver.findElement(partName).sendKeys("part-"+uuid);
			driver.findElement(partDesc).sendKeys("desc-"+uuid);
			
			driver.findElement(btnSavePart).click();
			
			driverWait = new WebDriverWait(driver,webDriverTimeout);
			
			
			
			driverWait.until(ExpectedConditions.textToBePresentInElement(wl, "Save Successful"));
			

		} catch (Exception e) {

			System.out.println(e);
		}
		
		assertTrue(driver.getPageSource().contains("Save Successful"));

	}
	
public void viewAllParts() {
		
		PageName = "View All Parts";

		try {

			System.out.println("View Parts page");	
			driver.findElement(Lnkpart).click();
			
		} catch (Exception e) {

			System.out.println(e);
		}
		
		driverWait = new WebDriverWait(driver,webDriverTimeout);
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(partTable));

	}

	public void viewSinglePart() {
		
		PageName = "View Single Part";

		try {

			System.out.println("View Part single");
			driver.findElement(viewSinglePart).click();
			
		} catch (Exception e) {

			System.out.println(e);
		}
		
		driverWait = new WebDriverWait(driver,webDriverTimeout);
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(btnDeletePart));
		
		//driverWait.until(ExpectedConditions.visibilityOf(wl));

	}

}
