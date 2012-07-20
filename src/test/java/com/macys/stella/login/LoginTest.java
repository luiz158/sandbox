package com.macys.stella.login;

import static com.macys.stella.util.ContextConstants.PASSWORD;
import static com.macys.stella.util.ContextConstants.SUPER_USER;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.selenium.base.AbstractTest;

import com.macys.stella.HomePageDriver;
import com.macys.stella.common.LogoutDriver;

public final class LoginTest extends AbstractTest{
	
	@After
	public void after(){
		new LogoutDriver( this.getWebDriver() ).logout();
	}
	
	// tests
	
	@Test
	public final void givenNotLoggedIn_whenLoggingIn_thenNoExceptions(){
		new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( SUPER_USER ).password( PASSWORD ).login();
	}
	
	@Test
	public final void givenNotLoggedIn_whenLoggingIn_thenOnHomepage(){
		// When
		final HomePageDriver homePageDriver = new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( SUPER_USER ).password( PASSWORD ).login();
		
		// Then
		assertTrue( homePageDriver.isHere() );
	}
	
	@Test
	public final void givenLoggedIn_whenLoggingOut_thenNoExceptions(){
		// Given
		final HomePageDriver homePageDriver = new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( SUPER_USER ).password( PASSWORD ).login();
		
		// When
		homePageDriver.logout();
	}
	
	@Test
	public final void givenLoggedIn_whenLoggingOut_thenOnLoggedOutPage(){
		// Given
		final HomePageDriver homePageDriver = new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( SUPER_USER ).password( PASSWORD ).login();
		
		// When
		final LoggedOutPageDriver loggedOutPageDriver = homePageDriver.logout();
		
		// Then
		assertTrue( loggedOutPageDriver.isHere() );
	}
	
	@Test
	public final void givenNotLoggedIn_whenLoggingInWithIncorrectCredentials_thenLoginPage(){
		// When
		final LoginPageDriver loginPageDriver = new LoginPageDriver( this.getWebDriver() );
		loginPageDriver.openLoginPage().username( SUPER_USER ).password( PASSWORD + "a" ).login();
		
		// Then
		assertTrue( loginPageDriver.isHere() );
	}
	
}
