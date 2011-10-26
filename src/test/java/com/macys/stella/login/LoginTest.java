package com.macys.stella.login;

import org.junit.Test;
import org.selenium.base.AbstractTest;

public final class LoginTest extends AbstractTest{
	
	// tests
	
	@Test
	public final void givenNotLoggedIn_whenLoggingIn_thenNoExceptions(){
		// Given
		
		// When
		new LoginPageDriver( this.getWebDriver() ).openLoginPage().name( "SuperBAUser" ).password( "haht" ).login();
		
		// Then
	}
	
}
