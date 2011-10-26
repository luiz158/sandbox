package com.macys.stella.savedSet;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.macys.stella.HomePageDriver;
import com.macys.stella.common.BaseLoggedInSeleniumTest;

public final class SavedSetTest extends BaseLoggedInSeleniumTest{
	
	// fixtures
	
	@Override
	@Before
	public void before(){
		super.before();
		this.<HomePageDriver> getDriver().navigateToCurrent();
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
		new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().productIds( idProduct ).create();
		
		// Then
		Assert.assertTrue( this.getDriver().isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenNoNameSavedSetIsCreated_thenNavigationDoesNotAdvance(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().productIds( idProduct ).create();
		
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
	
	// non-number
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithNonNumberValueIsCreated_thenError(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct, "\nwrong" ).create();
		
		// Then
		Assert.assertTrue( this.getDriver().isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithNonNumberValueIsCreated_thenNavigationDoesNotAdvance(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct, "\nwrong" ).create();
		
		// Then
		Assert.assertFalse( savedSetByIdDriver.isHere() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithNonNumberValueIsCreated_thenTheSavedSetIsStillCreated(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		final String nameOfSavedSet = randomAlphabetic( 6 );
		
		// When
		final CreateSavedSetOfProductsDriver createSavedSetOfProductsDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( nameOfSavedSet ).productIds( idProduct, "\nwrong" );
		createSavedSetOfProductsDriver.create();
		
		// Then
		Assert.assertTrue( createSavedSetOfProductsDriver.cancel().selectFilterByName().filterBy( nameOfSavedSet ).filter( nameOfSavedSet ).countSavedSetsInTable() >= 1 );
	}
	
	// empty values
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithEmptyValueIsCreated_thenNoErrors(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct, "\n  " ).create();
		
		// Then''
		Assert.assertFalse( this.getDriver().isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithEmptyValueIsCreated_thenNavigationDoesAdvance(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct, "\n  " ).create();
		
		// Then
		Assert.assertTrue( savedSetByIdDriver.isHere() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithEmptyValueIsCreated_thenTheSavedSetIsStillCreated(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		final String nameOfSavedSet = randomAlphabetic( 6 );
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( nameOfSavedSet ).productIds( idProduct, "\n  " ).create();
		
		// Then
		Assert.assertTrue( savedSetByIdDriver.openSavedSetsPage().selectFilterByName().filterBy( nameOfSavedSet ).filter( nameOfSavedSet ).countSavedSetsInTable() >= 1 );
	}
	
	// multiple valid id, same line
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithValidIdsOnTheSameLine_thenNoErrors(){
		// Given
		final String idProduct1 = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		final String idProduct2 = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct1 + ", " + idProduct2 ).create();
		
		// Then
		Assert.assertFalse( this.getDriver().anyProblem() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithValidIdsOnTheSameLine_thenTheSavedSetIsStillCreated(){
		// Given
		final String idProduct1 = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		final String idProduct2 = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		final String nameOfSavedSet = randomAlphabetic( 6 );
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( nameOfSavedSet ).productIds( idProduct1 + ", " + idProduct2 ).create();
		
		// Then
		Assert.assertTrue( savedSetByIdDriver.openSavedSetsPage().selectFilterByName().filterBy( nameOfSavedSet ).filter( nameOfSavedSet ).countSavedSetsInTable() >= 1 );
	}
	
	// numeric but invalid id
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithNumericButInvalidValueIsCreated_thenError(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct, "\n123" ).create();
		
		// Then
		Assert.assertTrue( this.getDriver().isErrorEmbeddedPresent() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithNumericButInvalidValueIsCreated_thenNavigationDoesNotAdvance(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct, "\n123" ).create();
		
		// Then
		Assert.assertFalse( savedSetByIdDriver.isHere() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithNumericButInvalidValueIsCreated_thenTheSavedSetIsStillCreated(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		final String nameOfSavedSet = randomAlphabetic( 6 );
		
		// When
		final CreateSavedSetOfProductsDriver createSavedSetOfProductsDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( nameOfSavedSet ).productIds( idProduct, "\n123" );
		createSavedSetOfProductsDriver.create();
		
		// Then
		Assert.assertTrue( createSavedSetOfProductsDriver.cancel().selectFilterByName().filterBy( nameOfSavedSet ).filter( nameOfSavedSet ).countSavedSetsInTable() >= 1 );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithNumericButInvalidValueIsCreated_thenTheProductIsPartOfTheSavedSet(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		final String nameOfSavedSet = randomAlphabetic( 6 );
		
		// When
		final CreateSavedSetOfProductsDriver createSavedSetOfProductsDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( nameOfSavedSet ).productIds( idProduct, "\n123" );
		createSavedSetOfProductsDriver.create();
		final List< String > productIds = createSavedSetOfProductsDriver.cancel().selectFilterByName().filterBy( nameOfSavedSet ).filter( nameOfSavedSet ).goInto( 1 ).getProductIds();
		
		// Then
		assertThat( productIds, hasItem( idProduct ) );
	}
	
	// many problems
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetWithNonNumberEmptyAndInvalidValuesIsCreated_thenTheSavedSetIsStillCreated(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		final String nameOfSavedSet = randomAlphabetic( 6 );
		
		// When
		final CreateSavedSetOfProductsDriver createSavedSetOfProductsDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( nameOfSavedSet ).productIds( idProduct, "\nwrong", "\n   ", "\n123" );
		createSavedSetOfProductsDriver.create();
		
		// Then
		Assert.assertTrue( createSavedSetOfProductsDriver.cancel().selectFilterByName().filterBy( nameOfSavedSet ).filter( nameOfSavedSet ).countSavedSetsInTable() >= 1 );
	}
	
	// no error
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetIsCreatedWithValidProductWithExtraSpaces_thenNoExceptions(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct + " " ).create();
		
		// Then
		Assert.assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetIsCreated_thenNoExceptions(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct ).create();
		
		// Then
		Assert.assertFalse( this.getDriver().anyProblem() );
	}
	@Test
	public final void givenOnCreateSavedSetFromProductPage_whenSavedSetIsCreated_thenNavigationDoesAdvance(){
		// Given
		final String idProduct = this.<HomePageDriver> getDriver().activateLeftMenuDriver().createProduct().fillRandom().create().retrieveId();
		
		// When
		final SavedSetByIdDriver savedSetByIdDriver = new HomePageDriver( this.getWebDriver() ).navigateToCurrent().activateLeftMenuDriver().createSavedSetOfProducts().name( randomAlphabetic( 6 ) ).productIds( idProduct ).create();
		
		// Then
		Assert.assertTrue( savedSetByIdDriver.isHere() );
	}
	
}
