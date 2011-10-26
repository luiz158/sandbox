package com.macys.stella.savedSet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.macys.stella.common.StellaBaseDriver;

public final class SavedSetByIdDriver extends StellaBaseDriver{
	
	public SavedSetByIdDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	@Override
	public final boolean isHere(){
		return Selenium2Utils.isElementPresentById( this.driver, "saved-set" );
	}
	
	@Override
	public final SavedSetByIdDriver wait( final int seconds ){
		Selenium2Utils.Wait.tryWaitForElementFoundById( this.driver, "saved-set", seconds );
		// Selenium2Utils.Wait.tryWaitForElementFoundById( this.driver, "saved-set-content", 1 );
		return this;
	}
	
	public final SavedSetsPageDriver openSavedSetsPage(){
		this.getWebDriver().findElement( By.linkText( "View saved sets" ) ).click();
		return new SavedSetsPageDriver( this.driver );
	}
	
	// API - retrieve
	
	public final String retrieveId(){
		final WebElement element = this.getWebDriver().findElement( By.xpath( "//li[@id=\"itemId\"]" ) );
		return element.getText();
	}
	
	public List< String > getProductIds(){
		final List< WebElement > productIdNodes = this.driver.findElements( By.xpath( "//*[@id=\"product-table\"]/table/tbody[2]/tr/td[3]/div/a" ) );
		final List< String > ids = Lists.transform( productIdNodes, new Function< WebElement, String >(){
			@Override
			public final String apply( final WebElement webEl ){
				return webEl.getText();
			}
		} );
		
		return ids;
	}
	
}
