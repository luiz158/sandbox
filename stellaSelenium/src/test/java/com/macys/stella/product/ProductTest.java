package com.macys.stella.product;

import static com.macys.stella.product.ProductConstants.brandName;
import static com.macys.stella.product.ProductConstants.departament;
import static com.macys.stella.product.ProductConstants.division;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import com.macys.stella.common.BaseLoggedInSeleniumTest;

public final class ProductTest extends BaseLoggedInSeleniumTest{
	
	// tests
	
	// navigations
	
	@Test
	public final void givenOnHomepage_whenNavigatingToProductsPage_thenNoExceptions(){
		// Given
		
		// When
		this.home().openProductsPage();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void givenOnProductPage_whenNavigatingToRegistry_thenNoExceptions(){
		// Given
		final ProductByIdDriver productByIdDriver = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() );
		
		// When
		productByIdDriver.openRegistryTab();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	//
	
	@Test
	public final void givenOnHomepage_whenCreateProductIsTriggered_thenNoExceptions(){
		// Given
		
		// When
		this.home().activateLeftMenuDriver().createProduct();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void givenCreatingProduct_whenNoDivision_thenError(){
		// Given
		final CreateProductDriver createProductDriver = this.home().activateLeftMenuDriver().createProduct();
		
		// When
		createProductDriver.department( departament ).brandName( brandName ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		
		// Then
		Assert.assertTrue( this.home().isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenCreatingProduct_whenNoDepartment_thenError(){
		// Given
		final CreateProductDriver createProductDriver = this.home().activateLeftMenuDriver().createProduct();
		
		// When
		createProductDriver.division( division ).brandName( brandName ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		
		// Then
		Assert.assertTrue( this.home().isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenCreatingProduct_whenProductCreationIsFinished_thenNoExceptions(){
		// Given
		final CreateProductDriver createProductDriver = this.home().activateLeftMenuDriver().createProduct();
		
		// When
		final ProductByIdDriver productByIdDriver = createProductDriver.division( division ).department( departament ).brandName( brandName ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create().wait( 1 );
		
		// Then
		assertTrue( productByIdDriver.isHere() );
	}
	
	@Test
	public final void givenCreatingProduct_whenProductIsCreated_thenNavigationGoesToProductByIdPage(){
		// Given
		final CreateProductDriver createProductDriver = this.home().activateLeftMenuDriver().createProduct();
		
		// When
		final ProductByIdDriver productByIdDriver = createProductDriver.division( division ).department( departament ).brandName( brandName ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		productByIdDriver.wait( 3 );
		
		// Then
		Assert.assertTrue( productByIdDriver.isHere() );
	}
	@Test
	public final void givenCreatingProduct_whenIncompleteProductIsCreated_thenNavigationDoesNotAdvance(){
		// Given
		final CreateProductDriver createProductDriver = this.home().activateLeftMenuDriver().createProduct();
		
		// When
		final ProductByIdDriver productByIdDriver = createProductDriver.department( departament ).brandName( brandName ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		productByIdDriver.tryWait( 1 );
		
		// Then
		Assert.assertFalse( productByIdDriver.isHere() );
	}
}
