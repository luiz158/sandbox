package com.macys.stella.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.macys.stella.common.BaseLoggedInSeleniumTest;

public final class ProductCloneTest extends BaseLoggedInSeleniumTest{
	
	// tests
	
	// navigation
	
	@Test
	@Ignore
	public final void givenOnHomepage_whenNavigatingToProductsPage_thenNoExceptions(){
		// Given
		
		// When
		this.home().openProductsPage();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	@Ignore
	public final void whenNavigatingToAProduct_thenNoExceptions(){
		// Given
		final ProductsPageDriver productsPageDriver = this.home().openProductsPage();
		
		// When
		productsPageDriver.goIntoEntityByIndex( 1 );
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	@Ignore
	public final void whenNavigatingToAProduct_thenNavigationIsSuccessful(){
		// When
		final ProductByIdDriver productByIdDriver = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() ).wait( 1 );
		
		// Then
		assertNotNull( productByIdDriver );
	}
	
	@Test
	@Ignore
	public final void givenOnAProductPage_whenNavigateToCrossSels_thenNoExceptions(){
		// Given
		final ProductByIdDriver productDriver = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() );
		
		// When
		productDriver.openCrossSellsTab();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	// other
	// TODO: when this test runs, the one after it fails - FIX THIS
	@Test
	public final void whenProductIsCloned_thenNoExceptions(){
		// Given
		final ProductByIdDriver productDriver = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() ).wait( 1 );
		
		// When
		productDriver.triggerCloning().save().wait( 1 );
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void givenNewProduct_thenThereAreNoCrossSellsDefined(){
		// Given
		final ProductByIdDriver productDriver = ProductShortcut.createAProduct( this.home().openProductsPage() );
		
		// Then
		assertFalse( productDriver.openCrossSellsTab().hasCrossSells() );
	}
	
	@Test
	@Ignore
	public final void givenOnCrossSellsPageOfAProduct_whenCreatingACrossSell_thenNoExceptions(){
		// Given
		final ProductByIdDriver productDriver = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() );
		
		// When
		final CrossSellCreationDriver crossSellCreationDriver = productDriver.openCrossSellsTab().startEditAndAddCrossSell();
		crossSellCreationDriver.byDescription().value( "A" ).find().wait( 2 ).select( 1 ).done( 1 ).save();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	@Ignore
	public final void givenCrossSellWasCreatedForProduct_thenProductHasCrossSells(){
		// Given
		final ProductByIdDriver productDriver = ProductShortcut.goIntoRandomProduct( this.home().openProductsPage() );
		final ProductCrossSellsDriver productCrossSellsDriver = ProductShortcut.createCrossSell( productDriver );
		
		// Then
		assertTrue( productCrossSellsDriver.hasCrossSells( 1 ) );
	}
	
	@Test
	@Ignore
	public final void givenSourceProductHasCrossSells_whenProductIsCloned_thenClonedProductHasCrossSells(){
		// Given
		final ProductByIdDriver productDriver = ProductShortcut.createAProduct( this.home().openProductsPage() );
		ProductShortcut.createCrossSell( productDriver );
		
		// When
		final ProductCrossSellsDriver crossSellsDriver = productDriver.triggerCloning().save().openCrossSellsTab();
		
		// Then
		assertTrue( crossSellsDriver.hasCrossSells( 1 ) );
	}
	
	@Test
	@Ignore
	public final void givenSourceProductHasCrossSellsOnPosition3_whenProductIsCloned_thenClonedProductHasCrossSellsOnPosition3(){
		// Given
		final ProductByIdDriver productDriver = ProductShortcut.createAProduct( this.home().openProductsPage() );
		ProductShortcut.createCrossSell( productDriver, 3 );
		
		// When
		final ProductCrossSellsDriver crossSellsDriver = productDriver.triggerCloning().save().openCrossSellsTab();
		
		// Then
		assertTrue( crossSellsDriver.hasCrossSells( 3 ) );
	}
	
}
