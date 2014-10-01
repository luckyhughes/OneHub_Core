package com.onetesthub.perf.library.ui.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.onetesthub.perf.adapter.ui.BaseUIPerfTest;
import com.onetesthub.perf.api.ui.PerfWebDriver;



public class HomePage extends BaseUIPerfTest{
	
	
	By username = By.id("username-input");
	By password = By.id("password-input");
	By btnLogin = By.id("login-button");
	
	//private WebElement q;
	//private WebElement btnK;
	
	public DashboardPage Authenticate(String username_text, String password_text) {
		
		PageName = "Home Page";
		
		try{
		System.out.println("Authenticating");
		driver.findElement(username).sendKeys(username_text);
		driver.findElement(password).sendKeys(password_text);
		driver.findElement(btnLogin).click();
		
		driverWait = new WebDriverWait(driver,webDriverTimeout);
		
		//WebElement wl = driver.findElement(By.xpath("//table[contains(@id,'tab1')]/tbody/tr[2]/td[1]"));
		
		By we = By.xpath("//div[contains(@class,'ngCellText ng-scope col1 colt1')]/span");
		
		System.out.println("XXXXXXXXXXXXXXXXXXXX: "+we.getClass().getName());
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(we));
		
		System.out.println("webelement text for asset id wl is ::" + we.getClass());
		
		//System.out.println("webelement text for asset id we is ::" + we.getClass().getName());
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		
		//wait.until(ExpectedConditions.textToBePresentInElement(wl, "Asset ID"));
		
		return new DashboardPage();
		//return PageFactory.initElements(driver, SearchResultsPage.class);
	}

}




