package com.macys.stella.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.selenium.base.AbstractTest;

import com.macys.stella.HomePageDriver;
import com.macys.stella.login.LoginPageDriver;
import com.macys.stella.product.ProductRegistryDriver;
import com.macys.stella.product.ProductShortcut;
import com.macys.stella.project.ProjectByIdDriver;
import com.macys.stella.project.ProjectShortcut;
import com.macys.stella.util.ContextConstants;

public final class EditabilityTest extends AbstractTest{
	
	@After
	public final void after(){
		new LoginPageDriver( this.getWebDriver() ).logout();
	}
	
	// tests
	
	@Test
	public final void givenNotLoggedIn_whenLoggingIn_thenNoExceptions(){
		// Given
		
		// When
		new LoginPageDriver( this.getWebDriver() ).openLoginPage().name( ContextConstants.SUPER_USER ).password( ContextConstants.PASSWORD ).login().wait( 1 );
		
		// Then
	}
	
	@Test
	public final void givenSuperuserIsLoggedIn_whenNavigatingToProductRegistryPage_thenEditingIsEnabled(){
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		
		// When
		final ProductRegistryDriver registryTab = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() ).openRegistryTab();
		
		// Then
		assertTrue( registryTab.isEditable() );
	}
	@Test
	public final void givenSuperuserIsLoggedIn_whenNavigatingToProjectPage_thenEditingIsEnabled(){
		// Given
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		
		// When
		final ProjectByIdDriver randomProjectDriver = ProjectShortcut.goIntoRandomProject( this.home().openProductsPage().openProjectsPage() );
		
		// Then
		assertTrue( randomProjectDriver.isEditable() );
	}
	
	@Test
	public final void givenLimitedUserIsLoggedIn_whenNavigatingToProductRegistryPage_thenEditingIsDisabled(){
		// Given
		this.login( ContextConstants.VENDOR_USER, ContextConstants.PASSWORD );
		
		// When
		final ProductRegistryDriver registryTab = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() ).openRegistryTab();
		
		// Then
		assertFalse( registryTab.isEditable() );
	}
	@Test
	public final void givenLimitedUserIsLoggedIn_whenNavigatingToProjectPage_thenEditingIsDisabled(){
		// Given
		this.login( ContextConstants.VENDOR_USER, ContextConstants.PASSWORD );
		
		// When
		final ProjectByIdDriver randomProjectDriver = ProjectShortcut.goIntoRandomProject( this.home().openProductsPage().openProjectsPage() ).wait( 1 );
		
		// Then
		assertFalse( randomProjectDriver.isEditable() );
	}
	
	// when user has ContentEdit role - it can edit stuff
	// when user can edit but does not have copywrite role - it cannot edit product copy attributes
	
	@Test
	public final void givenUserCanEditButDoesNotHaveCopywriteCredentials_whenEditingProductCopy_thenAttributesAreNotEditable(){
		// Given
		this.login( ContextConstants.VENDOR_USER, ContextConstants.PASSWORD );
		
		// When
		ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() ).openCopyTab();
		
		// Then
		
	}
	
	//
	
	final HomePageDriver login( final String user, final String pass ){
		return new LoginPageDriver( this.getWebDriver() ).openLoginPage().name( user ).password( pass ).login().wait( 1 );
	}
	
	final HomePageDriver home(){
		return new HomePageDriver( this.getWebDriver() ).wait( 1 );
	}
	
}
