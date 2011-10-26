package com.macys.stella.login;

import org.junit.Assert;
import org.junit.Test;

import com.macys.stella.HomePageDriver;
import com.macys.stella.common.BaseLoggedInSeleniumTest;

public final class AfterLoginTest extends BaseLoggedInSeleniumTest{
	
	// tests
	
	@Test
	public final void whenJustLoggedIn_thenNoErrorPopups(){
		// Given
		
		// When
		
		// Then
		Assert.assertFalse( new HomePageDriver( this.getWebDriver() ).isErrorPopupPresent() );
	}
	@Test
	public final void whenJustLoggedIn_thenNoAllerts(){
		// Given
		
		// When
		
		// Then
		Assert.assertFalse( new HomePageDriver( this.getWebDriver() ).isAlertPresent() );
	}
	@Test
	public final void whenJustLoggedIn_thenNoEmbeddedErrors(){
		// Given
		
		// When
		
		// Then
		Assert.assertFalse( new HomePageDriver( this.getWebDriver() ).isErrorEmbeddedPresent() );
	}
	
}
