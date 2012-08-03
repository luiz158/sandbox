package com.macys.stella.savedSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Preconditions;
import com.macys.stella.common.StellaBaseDriver;
import com.macys.stella.util.Ids;

public final class CreateSavedSetOfProductsDriver extends StellaBaseDriver{
	
	public CreateSavedSetOfProductsDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final CreateSavedSetOfProductsDriver name( final String nameToSet ){
		Preconditions.checkNotNull( nameToSet );
		
		this.driver.findElement( By.id( "createSavedSetCommand.savedSetName" ) ).sendKeys( nameToSet );
		return this;
	}
	public final CreateSavedSetOfProductsDriver productIds( final String... productIdsToSet ){
		Preconditions.checkNotNull( productIdsToSet );
		
		this.driver.findElement( By.xpath( ".//div[@id='create-savedset']/form/div[3]/textarea" ) ).sendKeys( productIdsToSet );
		return this;
	}
	
	// API - actions
	
	public final SavedSetByIdDriver create(){
		this.driver.findElement( By.id( "create" ) ).click();
		
		this.waitForLoadingMask();
		
		return new SavedSetByIdDriver( this.driver ).wait( 1 );
	}
	public final SavedSetsPageDriver cancel(){
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, Ids.BUTTON_CANCEL1, 1 );
		
		this.driver.findElement( By.id( Ids.BUTTON_CANCEL1 ) ).click();
		
		return new SavedSetsPageDriver( this.driver );
	}
	
}
