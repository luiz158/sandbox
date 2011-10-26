package com.macys.stella.product;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import org.junit.Assert;
import org.junit.Test;

import com.macys.stella.HomePageDriver;
import com.macys.stella.common.BaseLoggedInSeleniumTest;

public final class ProductTest extends BaseLoggedInSeleniumTest{
	
	// tests
	
	@Test
	public final void givenOnHomepage_whenCreateProductIsTriggered_thenNoExceptions(){
		// Given
		
		// When
		new HomePageDriver( this.getWebDriver() ).activateLeftMenuDriver().createProduct();
		
		// Then
	}
	
	@Test
	public final void givenOnCreateProductPage_whenProductIsCreatedWithoutDivision_thenError(){
		// Given
		final CreateProductDriver createProductDriver = new HomePageDriver( this.getWebDriver() ).activateLeftMenuDriver().createProduct();
		
		// When
		createProductDriver.department( "188" ).brandName( "A New York" ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		
		// Then
		Assert.assertTrue( new HomePageDriver( this.getWebDriver() ).isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenOnCreateProductPage_whenProductIsCreatedWithoutDepartment_thenError(){
		// Given
		final CreateProductDriver createProductDriver = new HomePageDriver( this.getWebDriver() ).activateLeftMenuDriver().createProduct();
		
		// When
		createProductDriver.division( "1" ).brandName( "A New York" ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		
		// Then
		Assert.assertTrue( new HomePageDriver( this.getWebDriver() ).isErrorEmbeddedPresent() );
	}
	
	@Test
	public final void givenOnCreateProductPage_whenProductIsCreated_thenNoExceptions(){
		// Given
		final CreateProductDriver createProductDriver = new HomePageDriver( this.getWebDriver() ).activateLeftMenuDriver().createProduct();
		
		// When
		createProductDriver.division( "1" ).department( "188" ).brandName( "A New York" ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		
		// Then
	}
	
	@Test
	public final void givenOnCreateProductPage_whenProductIsCreated_thenNavigationGoesToProductByIdPage(){
		// Given
		final CreateProductDriver createProductDriver = new HomePageDriver( this.getWebDriver() ).activateLeftMenuDriver().createProduct();
		
		// When
		final ProductByIdDriver productByIdDriver = createProductDriver.division( "1" ).department( "188" ).brandName( "A New York" ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		productByIdDriver.wait( 3 );
		
		// Then
		Assert.assertTrue( productByIdDriver.isHere() );
	}
	@Test
	public final void givenOnCreateProductPage_whenIncompleteProductIsCreated_thenNavigationDoesNotAdvance(){
		// Given
		final CreateProductDriver createProductDriver = new HomePageDriver( this.getWebDriver() ).activateLeftMenuDriver().createProduct();
		
		// When
		final ProductByIdDriver productByIdDriver = createProductDriver.department( "188" ).brandName( "A New York" ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
		productByIdDriver.wait( 1 );
		
		// Then
		Assert.assertFalse( productByIdDriver.isHere() );
	}
	
}
