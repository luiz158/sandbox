package com.macys.stella.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.selenium.base.AbstractTest;

import com.macys.stella.HomePageDriver;
import com.macys.stella.login.LoginPageDriver;
import com.macys.stella.util.ContextConstants;

public final class SecurityTest extends AbstractTest{
	
	@After
	public final void after(){
		new LoginPageDriver( this.getWebDriver() ).logoutIfLoggedIn();
	}
	
	// tests
	
	@Test
	public final void givenUserWithoutCreateContentRoleIsLoggedIn_thenNoCreationActionsAreAvailable(){
		// Given
		final HomePageDriver homePageDriver = this.login( "Admin", ContextConstants.PASSWORD );
		
		// Then
		assertFalse( homePageDriver.areContentCreationOptionsAvailable() );
	}
	@Test
	public final void givenUserWithCreateContentRoleIsLoggedIn_thenNoCreationActionsAreAvailable(){
		// Given
		final HomePageDriver homePageDriver = this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		
		// Then
		assertTrue( homePageDriver.areContentCreationOptionsAvailable() );
	}
	
	//
	
	final HomePageDriver login( final String user, final String pass ){
		return new LoginPageDriver( this.getWebDriver() ).openLoginPage().name( user ).password( pass ).login();
	}
	
	final HomePageDriver home(){
		return new HomePageDriver( this.getWebDriver() ).wait( 1 );
	}
	
}
