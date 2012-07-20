package com.macys.stella.login;

import org.junit.Test;
import org.selenium.base.AbstractTest;

import com.macys.stella.util.ContextConstants;

public final class LoginTest extends AbstractTest{
	
	// tests
	
	@Test
	public final void givenNotLoggedIn_whenLoggingIn_thenNoExceptions(){
		// Given
		
		// When
		new LoginPageDriver( this.getWebDriver() ).openLoginPage().username( ContextConstants.SUPER_USER ).password( ContextConstants.PASSWORD ).login();
		
		// Then
	}
	
}
