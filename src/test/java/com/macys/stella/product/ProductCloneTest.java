package com.macys.stella.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.macys.stella.HomePageDriver;
import com.macys.stella.common.BaseLoggedInSeleniumTest;

public final class ProductCloneTest extends BaseLoggedInSeleniumTest{
	
	// tests
	
	@Test
	public final void givenOnHomepage_whenNavigatingToProductsPage_thenNoExceptions(){
		// Given
		
		// When
		new HomePageDriver( this.getWebDriver() ).openProductsPage();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void givenOnProductsPageAndAtLeastAProductExists_whenNavigatingToAProduct_thenNoExceptions(){
		// Given
		final ProductsPageDriver productsPageDriver = new HomePageDriver( this.getWebDriver() ).openProductsPage();
		
		// When
		productsPageDriver.goIntoProductByIndex( 1 );
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void whenNavigatingToAProduct_thenNavigationIsSuccessful(){
		// When
		final ProductByIdDriver productByIdDriver = ProductDriver.goIntoRandomProduct( new HomePageDriver( this.getWebDriver() ).openProductsPage() );
		
		// Then
		assertNotNull( productByIdDriver );
	}
	
	@Test
	public final void givenOnAProductPage_whenProductIsCloned_thenNoExceptions(){
		// Given
		final ProductByIdDriver productDriver = ProductDriver.goIntoRandomProduct( new HomePageDriver( this.getWebDriver() ).openProductsPage() );
		
		// When
		productDriver.triggerCloning().save();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void givenOnAProductPage_whenNavigateToCrossSels_thenNoExceptions(){
		// Given
		final ProductByIdDriver productDriver = ProductDriver.goIntoRandomProduct( new HomePageDriver( this.getWebDriver() ).openProductsPage() );
		
		// When
		productDriver.openCrossSellsTab();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void givenNewProduct_thenThereAreNoCrossSellsDefined(){
		// Given
		final ProductByIdDriver productDriver = ProductDriver.createAProduct( new HomePageDriver( this.getWebDriver() ).openProductsPage() );
		
		// Then
		assertFalse( productDriver.openCrossSellsTab().hasCrossSells() );
	}
	
	@Test
	public final void givenOnCrossSellsPageOfAProduct_whenCreatingACrossSell_thenNoExceptions(){
		// Given
		final ProductByIdDriver productDriver = ProductDriver.goIntoRandomProduct( new HomePageDriver( this.getWebDriver() ).openProductsPage() );
		
		// When
		final CrossSellCreationDriver crossSellCreationDriver = productDriver.openCrossSellsTab().startEditAndAddCrossSell();
		crossSellCreationDriver.byDescription().value( "A" ).find().wait( 2 ).select( 1 ).done( 1 ).save();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void givenCrossSellWasCreatedForProduct_thenProductHasCrossSells(){
		// Given
		final ProductByIdDriver productDriver = ProductDriver.goIntoRandomProduct( new HomePageDriver( this.getWebDriver() ).openProductsPage() );
		final ProductCrossSellsDriver productCrossSellsDriver = ProductDriver.createCrossSell( productDriver );
		
		// Then
		assertTrue( productCrossSellsDriver.hasCrossSells( 1 ) );
	}
	
	@Test
	public final void givenSourceProductHasCrossSells_whenProductIsCloned_thenClonedProductHasCrossSells(){
		// Given
		final ProductByIdDriver productDriver = ProductDriver.createAProduct( new HomePageDriver( this.getWebDriver() ).openProductsPage() );
		ProductDriver.createCrossSell( productDriver );
		
		// When
		final ProductCrossSellsDriver crossSellsDriver = productDriver.triggerCloning().save().openCrossSellsTab();
		
		// Then
		assertTrue( crossSellsDriver.hasCrossSells( 1 ) );
	}
	
	@Test
	public final void givenSourceProductHasCrossSellsOnPosition3_whenProductIsCloned_thenClonedProductHasCrossSellsOnPosition3(){
		// Given
		final ProductByIdDriver productDriver = ProductDriver.createAProduct( new HomePageDriver( this.getWebDriver() ).openProductsPage() );
		ProductDriver.createCrossSell( productDriver, 3 );
		
		// When
		final ProductCrossSellsDriver crossSellsDriver = productDriver.triggerCloning().save().openCrossSellsTab();
		
		// Then
		assertTrue( crossSellsDriver.hasCrossSells( 3 ) );
	}
	
}
