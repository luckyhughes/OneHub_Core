package com.onetesthub.perf.testcases.app;

import java.util.UUID;

import org.testng.annotations.Test;

import com.onetesthub.perf.adapter.ui.BaseUIPerfTest;
import com.onetesthub.perf.library.ui.app.ClassificationPage;
import com.onetesthub.perf.library.ui.app.DashboardPage;
import com.onetesthub.perf.library.ui.app.HomePage;
import com.onetesthub.perf.library.ui.app.LoginPage;
import com.onetesthub.perf.library.ui.app.ModelPage;
import com.onetesthub.perf.library.ui.app.PartPage;

public class TC1 extends BaseUIPerfTest{
	
	LoginPage loginpage;
	HomePage homepage;
	DashboardPage dpage;
	ModelPage mpage;
	PartPage ppage;
	ClassificationPage cpage;
	String uuid= UUID.randomUUID().toString();;
	
		
	@Test ()
	public void loginPage(){
		
		System.out.println("In Login");
		
		LoginPage loginpage = new LoginPage();
		//jmx.jmxReplaceUrlPath("sandylogin");
		homepage = loginpage.login();
	
	}
	
	@Test ()
	public void homePage(){
		
		System.out.println("In homepage");

		//jmx.jmxReplaceUrlPath("loginUrl");
	    dpage = homepage.Authenticate("integration", "integration");
		
		//dpage = homepage.Authenticate("integration", "integration");

	}
	
	
	
	@Test ()
	public void createAsset(){
		
		System.out.println("Create Asset");	
		
		jmx.jmxReplaceParam(uuid, "asset_\\${randomNumber}",
				"value");	
		dpage.createAsset(uuid);

	}
	
	@Test ()
	public void viewAllAssets(){
		
		System.out.println("View all Assets");		
		dpage.viewAllAssets();
		
	}
	
	@Test ()
	public void viewSingleAsset(){
		
		System.out.println("View single Asset");		
		dpage.viewSingleAsset();
		
	}
	
	@Test ()
	public void OpenModelPage(){
		
		System.out.println("Open Model Page");		
		mpage = dpage.OpenModelPage();

	}
	
	@Test ()
	public void ClassificationPage(){
		
		System.out.println("Open Classification Page");		
		cpage = mpage.OpenClassificationPage();

	}
	
	@Test ()
	public void createClassification(){
		
		jmx.jmxReplaceParam(uuid, "classification_\\${randomNumber}",
				"value");
		
		jmx.jmxReplaceParam("/classification/(.*?)\",", "classification_\\${randomNumber}",
				"regex");
		
		
		//jmx.jmxReplaceParam("uri", "/classification/\\${randomNumber}","param");
		
		
		System.out.println("Create Classification");		
		cpage.createClassification(uuid);

	}
	
	@Test ()
	public void PartPage(){
		
		System.out.println("Open Part Page");		
		ppage = mpage.OpenPartPage();

	}
	
	@Test ()
	public void createPart(){
		
		System.out.println("Create Part");	
		
		jmx.jmxReplaceParam(uuid, "part_\\${randomNumber}",
				"value");
		
		jmx.jmxSaveResponseParam("parturl", "/part/(.*?)\",","1");
		ppage.createPart(uuid);

	}
	
	@Test ()
	public void viewAllParts(){
		
		System.out.println("View all Parts");		
		ppage.viewAllParts();
		
	}
	
	@Test ()
	public void viewSinglePart(){
		
		System.out.println("View single Part");		
		ppage.viewSinglePart();
		
	}
	
	
//	@Test (priority=2)
//	public void login(){
//		
//		System.out.println("In Step2");
//		
//		//jmx.jmxReplaceParam("q","${searchterm}");
//		jmx.jmxReplaceUrlPath("loginUrl");
//		//jmx.jmxReplaceParam("location","${searchterm1}");
//		MyHomePage searchresultspage = searchpage.submitSearch("lithium");
//
//	}
	

}
