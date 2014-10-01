package com.onetesthub.perf.library.ui.app;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.onetesthub.perf.adapter.ui.BaseUIPerfTest;
import com.onetesthub.perf.api.ui.PerfWebDriver;

public class DashboardPage extends BaseUIPerfTest {
	
	//generate random asset name
	String uuid;

	By btnNewAsset = By.xpath("//button[contains(@class,'new-asset-button')]");
	By assetId = By.name("assetId");
	By assetDesc = By.name("assetDesc");
	By assetSerialNumber = By.className("qa-assetSerialNumber");

	By Addattr = By.xpath("//div[contains(@class,'qa-addnewAttribute')]");
	By attrName = By.xpath("//input[contains(@class,'attr-name')]");
	By attrType = By.xpath("//select[contains(@class,' attr-type')]");
	By attrUnit = By.xpath("//select[contains(@class,' attr-unit')]");
	By attrUnitOption = By.xpath("//select[contains(@class,' attr-unit')]/option");
	
	By attrValue = By.xpath("//input[contains(@class,'qa-attrDefault')]");
	By btnSave = By.xpath("//button[contains(@class,'qa-btnSave')]");
	
	
	By listAllAssets = By.xpath("//a[contains(@class, 'qa-assets')]");
	
	By viewSingleAsset = By.xpath("//div[contains(@class,'ngCellText ng-scope col1 colt1')]/span");
	
	By model = By.xpath("//div[@class='primary-navbar nav-collapse']//span[text()='Model']");
	
	By part = By.xpath("//a[contains(@class,'qa-linkToPartNumber')]");

	By btnCreateClass = By.xpath("//button[contains(@class,'btn qa-btnNewRoot')]");
	
	By assertViewAssets = By.xpath("//div[contains(@class,'ngCellText ng-scope col1 colt1')]/span");
	By assertAssetDetail = By.xpath("//section[contains(@class,'asset-details-wrapper-section')]");
	
	

	public void createAsset(String uuid) {
		
		PageName = "Create Asset";

		try {

		
			System.out.println("Next is to click create asset button");
			
			driver.findElement(btnNewAsset).click();
			
			System.out.println("Create asset button click DONE");
			
			driverWait = new WebDriverWait(driver,webDriverTimeout);
			driverWait.until(ExpectedConditions.elementToBeClickable(btnSave));
			
			driver.findElement(assetId).sendKeys(uuid);
			driver.findElement(assetDesc).sendKeys(uuid);
			driver.findElement(assetSerialNumber).sendKeys(uuid);
			driver.findElement(Addattr).click();
			driver.findElement(attrName).sendKeys(uuid);
			
			System.out.println("yyyy: attrvalue");
			driver.findElement(attrValue).click();
			
			driver.findElement(attrValue).sendKeys(uuid);
			
			System.out.println("yyyy: attrUnit");
			
			driver.findElement(attrUnit).click();
			
			List<WebElement>webElms = driver.findElements(attrUnitOption);
			for(int i=0; i<webElms.size(); i++)
			{
				if(webElms.get(i).getText().equalsIgnoreCase("CM")){
					
//					System.out.println("XXXXXXXXyyyy: attrUnitoption::::--"+ webElms.get(i).getText());
					webElms.get(i).click();
					break;
//					Thread.sleep(5000);
				}
			
			}
			//driver.findElement(attrType).se
			
			System.out.println("yyyy: save");
			
			driver.findElement(btnSave).click();
			
			driverWait = new WebDriverWait(driver,webDriverTimeout);
			
			By wl = By.xpath("//div[contains(@class, 'toast-message')]");
			
			driverWait.until(ExpectedConditions.textToBePresentInElement(wl, "Save Successful"));
			

		} catch (Exception e) {

			System.out.println(e);
			e.printStackTrace();
		}
		
		assertTrue(driver.getPageSource().contains("Save Successful"));
		
		

	}
	
public void viewAllAssets() {
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("window.scrollBy(0,0)", "");
		
		PageName = "View All Assets";

		try {

			System.out.println("View Asset page");	
			driver.findElement(listAllAssets).click();
			
		} catch (Exception e) {

			System.out.println(e);
		}
		
		driverWait = new WebDriverWait(driver,webDriverTimeout);
		
		//By we = viewAsset;
		
		System.out.println("XXXXXXXXXXXXXXXXXXXX: view all assets");
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(assertViewAssets));

	}

	public void viewSingleAsset() {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,0)", "");
		
		PageName = "View Single Asset";

		try {

			System.out.println("View Asset single");
			List<WebElement> assetIDlist = driver.findElements(viewSingleAsset);
			assetIDlist.get(1).click();
			
		} catch (Exception e) {

			System.out.println(e);//
		}
		
		driverWait = new WebDriverWait(driver,webDriverTimeout);
		
		//By we = By.xpath("//button[contains(@class,'qa-AssetHistory')]");
		
		//System.out.println("XXXXXXXXXXXXXXXXXXXX: "+we.getClass().getName());
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(assertAssetDetail));
		
		//driverWait.until(ExpectedConditions.visibilityOf(wl));

	}
	
public ModelPage OpenModelPage(){
		
		PageName = "Model Page";
		
		driver.findElement(model).click();
		
		driverWait = new WebDriverWait(driver,webDriverTimeout);
		driverWait.until(ExpectedConditions.elementToBeClickable(btnCreateClass));
		
		return new ModelPage();
		
	}


}

