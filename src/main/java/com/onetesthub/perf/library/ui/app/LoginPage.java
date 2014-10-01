package com.onetesthub.perf.library.ui.app;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.onetesthub.perf.adapter.ui.BaseUIPerfTest;
import com.onetesthub.perf.api.ui.PerfWebDriver;



public class LoginPage extends BaseUIPerfTest{
	
	public HomePage login() {
				
		PageName = "Login Page";
		
		driver.get(baseUrl +"/login");
		driver.manage().window().maximize();

		return new HomePage();
		//return PageFactory.initElements(driver, SearchPage.class);
	}

	
}
