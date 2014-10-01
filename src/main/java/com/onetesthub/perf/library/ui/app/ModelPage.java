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

public class ModelPage extends BaseUIPerfTest {
	
	//generate random asset name
	String uuid;

	
	
	By Lnkpart = By.xpath("//a[contains(@class,'qa-linkToPartNumber')]");
	
	By Lnkclassification = By.xpath("//a[contains(@class,'qa-linkToClassification')]");
	
	By Lnkassetstr = By.xpath("//a[contains(@class,'qa-linkToAST')]");
	
	By btnNewClassification = By.xpath("//button[contains(@class,'qa-btnNewRoot')]");
	
	By btnNewAST = By.xpath("//button[contains(@data-title,'Add a new structure node')]");
	
	By assertDataTable = By.xpath("//div[contains(@class,'ngCellText ng-scope col1 colt1')]/span");
	


public PartPage OpenPartPage(){
	
	PageName = "Part Page";
	
	driver.findElement(Lnkpart).click();
	
	driverWait = new WebDriverWait(driver,webDriverTimeout);
	driverWait.until(ExpectedConditions.elementToBeClickable(assertDataTable));
	
	return new PartPage();
	
}

public ClassificationPage OpenClassificationPage(){
	
	PageName = "Classification Page";
	
	driver.findElement(Lnkclassification).click();
	
	driverWait = new WebDriverWait(driver,webDriverTimeout);
	driverWait.until(ExpectedConditions.elementToBeClickable(btnNewClassification));
	
	return new ClassificationPage();
	
}

public ASTPage OpenASTPage(){
	
	PageName = "Asset Structure Template Page";
	
	driver.findElement(Lnkassetstr).click();
	
	driverWait = new WebDriverWait(driver,webDriverTimeout);
	driverWait.until(ExpectedConditions.elementToBeClickable(btnNewAST));
	
	return new ASTPage();
	
}

}

