package com.macys.stella.common;

import org.junit.After;
import org.junit.Before;
import org.selenium.base.AbstractTest;

import com.macys.stella.login.LoginPageDriver;

public abstract class BaseLoggedInSeleniumTest extends AbstractTest{
	
	//
	
	protected final void cancelModalWindowsBeforeLoggingOutIfNeeded(){
		// TODO
	}
	
	// fixtures
	
	@Before
	public void before(){
		currentTest = this;
		new LoginPageDriver( this.getWebDriver() ).openLoginPage().name( "SuperBAUser" ).password( "haht" ).login();
	}
	@After
	public void after(){
		new LoginPageDriver( this.getWebDriver() ).logoutIfLoggedIn();
	}
	
}
