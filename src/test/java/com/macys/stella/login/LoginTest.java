package com.macys.stella.login;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.selenium.base.AbstractTest;

import com.macys.stella.HomePageDriver;
import com.macys.stella.common.LogoutDriver;
import com.macys.stella.util.ContextConstants;

public final class LoginTest extends AbstractTest{
	
	@After
	public void after(){
		new LogoutDriver( this.getWebDriver() ).logout();
	}
	
	// tests
	
	@Test
	public final void givenNotLoggedIn_whenLoggingIn_thenNoExceptions(){
		new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( ContextConstants.SUPER_USER ).password( ContextConstants.PASSWORD ).login();
	}
	
	@Test
	public final void givenNotLoggedIn_whenLoggingIn_thenOnHomepage(){
		// When
		final HomePageDriver homePageDriver = new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( ContextConstants.SUPER_USER ).password( ContextConstants.PASSWORD ).login();
		
		// Then
		assertTrue( homePageDriver.isHere() );
	}
	
	@Test
	public final void givenLoggedIn_whenLoggingOut_thenNoExceptions(){
		// Given
		final HomePageDriver homePageDriver = new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( ContextConstants.SUPER_USER ).password( ContextConstants.PASSWORD ).login();
		
		// When
		homePageDriver.logout();
	}
	
}
