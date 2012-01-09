package com.macys.stella.product;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.macys.stella.common.BaseLoggedInSeleniumTest;

public final class ProductCopyTest extends BaseLoggedInSeleniumTest{
	
	// tests
	
	// navigation
	
	@Test
	public final void givenOnProductPage_whenNavigatingToTheCopyTab_thenNoProblems(){
		// Given
		final ProductByIdDriver productByIdDriver = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() );
		
		// When
		productByIdDriver.openCopyTab();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	//
	
	@Test
	public final void givenOnProductCopyPage_whenEditing_thenNoProblems(){
		// Given
		final ProductCopyDriver copyTabDriver = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() ).openCopyTab();
		
		// When
		final EditProductCopyDriver editProductCopyDriver = copyTabDriver.startEdit();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
		editProductCopyDriver.cancel();
	}
	
}
