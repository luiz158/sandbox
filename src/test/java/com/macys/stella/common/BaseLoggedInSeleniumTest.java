package com.macys.stella.common;

import org.junit.After;
import org.junit.Before;
import org.selenium.base.AbstractTest;

import com.macys.stella.HomePageDriver;
import com.macys.stella.login.LoginPageDriver;
import com.macys.stella.util.ContextConstants;

public abstract class BaseLoggedInSeleniumTest extends AbstractTest{
	
	protected final void cancelModalWindowsBeforeLoggingOutIfNeeded(){
		// TODO
	}
	
	// fixtures
	
	@Override
	@Before
	public void before(){
		super.before();
		new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( ContextConstants.SUPER_USER ).password( ContextConstants.PASSWORD ).login();
	}
	@After
	public void after(){
		new LoginPageDriver( this.getWebDriver() ).logout();
	}
	
	protected final HomePageDriver home(){
		return new HomePageDriver( this.getWebDriver() ).wait( 1 );
	}
	
}
