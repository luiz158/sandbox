package com.macys.stella;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.macys.stella.common.BaseLoggedInSeleniumTest;
import com.macys.stella.savedSet.CreateSavedSetOfProductsDriver;
import com.macys.stella.savedSet.SavedSetByIdDriver;

public final class SandboxTest extends BaseLoggedInSeleniumTest{
	
	// fixtures
	
	@Override
	@Before
	public void before(){
		super.before();
		this.<HomePageDriver> getDriver().wait( 1 );
	}
	
	// tests
	
	@Test
	public final void givenOnHomepage_whenCreateSavedSetIsTriggered_thenNoExceptions(){
		// Given
		
		// When
		this.<HomePageDriver> getDriver().activateLeftMenuDriver().createSavedSetOfProducts();
		
		// Then
	}
	
	// no name
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenNoNameSavedSetIsCreated_thenError(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		this.home().navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().productIds( idProduct ).create();
		
		// Then
		Assert.assertTrue( this.getDriver().isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenNoNameSavedSetIsCreated_thenNavigationDoesNotAdvance(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = this.home().navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().productIds( idProduct ).create();
		
		// Then
		Assert.assertFalse( savedSetByIdDriver.isHere() );
	}
	
	// any error
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenInvalidSavedSetIsCreated_thenError(){
		// Given
		
		// When
		this.<HomePageDriver> getDriver().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( "123" ).create();
		
		// Then
		Assert.assertTrue( this.getDriver().isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenInvalidSavedSetIsCreated_thenNavigationDoesNotAdvance(){
		// Given
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( "123" ).create();
		
		// Then
		Assert.assertFalse( savedSetByIdDriver.isHere() );
	}
	// - note: this sometimes fails - replicate and fix
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenInvalidSavedSetIsCreated_thenTheSavedSetIsNotCreated(){
		// Given
		final String nameOfSavedSet = randomAlphabetic( 6 );
		
		// When
		final CreateSavedSetOfProductsDriver createSavedSetOfProductsDriver = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createSavedSetOfProducts().name( nameOfSavedSet ).productIds( "123" );
		createSavedSetOfProductsDriver.create();
		
		// Then
		final int noSavedSetsByName = createSavedSetOfProductsDriver.cancel().selectFilterByName().filterBy( nameOfSavedSet ).filter( nameOfSavedSet ).countSavedSetsInTable();
		System.out.println( noSavedSetsByName );
		Assert.assertTrue( noSavedSetsByName == 0 );
	}
	
}
