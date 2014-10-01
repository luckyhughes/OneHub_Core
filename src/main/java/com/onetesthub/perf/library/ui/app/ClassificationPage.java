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

public class ClassificationPage extends BaseUIPerfTest {
	
	//generate random asset name
	String uuid;

	By btnNewClassification = By.xpath("//button[contains(@class,'qa-btnNewRoot')]");
	
	By className = By.xpath("//input[contains(@class,'inheritance-label-input')]");
	By classDesc = By.xpath("//input[contains(@ng-model,'classificationsMap[selectedUri].description')]");
	By btnSaveClass = By.xpath("//button[contains(@class,'qa-btnSave')]");
	By attrTitle = By.xpath("//div[contains(@class,'qa-ClassificationTitle')]/h2");
	By addAttr = By.xpath("//div[contains(@class,'new-attribute-command')]");
	By attrName = By.xpath("//input[contains(@class,'qa-attrName')]");
	By attrValue= By.xpath("//input[contains(@class,'qa-attrDefault')]");
	
	By partBody = By.xpath("//div[contains(@class,'module-body qa-module-body')]");   
	
	
	

	public void createClassification(String uuid) {
		
		PageName = "Create Classification";

		try {

			
			driver.findElement(btnNewClassification).click();
			
			System.out.println("Create Classification button click DONE");
			
			driver.findElement(partBody).click();
			
			//driverWait = new WebDriverWait(driver,webDriverTimeout);
			//driverWait.until(ExpectedConditions.elementToBeClickable(classDesc));
			
			driver.findElement(className).sendKeys(uuid);
			driver.findElement(partBody).click();
			driver.findElement(attrTitle).click();
			driver.findElement(classDesc).sendKeys("desc-"+uuid);
			
			driver.findElement(addAttr).click();
			driver.findElement(attrName).sendKeys("attrname1");
			//driver.findElement(attrValue).sendKeys("attrvalue");
			

			driver.findElement(btnSaveClass).click();
			
			driverWait = new WebDriverWait(driver,webDriverTimeout);
			
			By wl = By.xpath("//div[contains(@class, 'toast-message')]");
			
			driverWait.until(ExpectedConditions.textToBePresentInElement(wl, "Save Successful"));
			

		} catch (Exception e) {

			System.out.println(e);
		}
		
		assertTrue(driver.getPageSource().contains("Save Successful"));

	}
	
//public void viewAllAssets() {
//		
//		PageName = "View All Assets";
//
//		try {
//
//			System.out.println("View Asset page");	
//			driver.findElement(listAssets).click();
//			
//		} catch (Exception e) {
//
//			System.out.println(e);
//		}
//		
//		driverWait = new WebDriverWait(driver,webDriverTimeout);
//		
//		By we = By.xpath("//table[contains(@id,'tab1')]/tbody/tr[2]/td[1]");
//		
//		System.out.println("XXXXXXXXXXXXXXXXXXXX: "+we.getClass().getName());
//		
//		driverWait.until(ExpectedConditions.presenceOfElementLocated(we));
//
//	}
//
//	public void viewSingleAsset() {
//		
//		PageName = "View Single Asset";
//
//		try {
//
//			System.out.println("View Asset single");
//			driver.findElement(viewAsset).click();
//			
//		} catch (Exception e) {
//
//			System.out.println(e);
//		}
//		
//		driverWait = new WebDriverWait(driver,webDriverTimeout);
//		
//		By we = By.xpath("//i[contains(@class,'qa-btnAddChildAsset')]");
//		
//		System.out.println("XXXXXXXXXXXXXXXXXXXX: "+we.getClass().getName());
//		
//		driverWait.until(ExpectedConditions.presenceOfElementLocated(we));
//		
//		//driverWait.until(ExpectedConditions.visibilityOf(wl));
//
//	}

}
