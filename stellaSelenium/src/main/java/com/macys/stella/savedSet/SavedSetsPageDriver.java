package com.macys.stella.savedSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Preconditions;
import com.macys.stella.common.StellaBaseDriver;

public final class SavedSetsPageDriver extends StellaBaseDriver{
	
	public SavedSetsPageDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	@Override
	public final SavedSetsPageDriver wait( final int seconds ){
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, "yui-dt-data", seconds );
		
		return this;
	}
	
	public final SavedSetsPageDriver selectFilterById(){
		this.driver.findElement( By.xpath( ".//select[@id='filter-type']/option[2]" ) ).click();
		return this;
	}
	public final SavedSetsPageDriver selectFilterByName(){
		this.driver.findElement( By.xpath( ".//select[@id='filter-type']/option[3]" ) ).click();
		return this;
	}
	
	public final SavedSetsPageDriver filterBy( final String filterByToSet ){
		Preconditions.checkNotNull( filterByToSet );
		
		this.driver.findElement( By.id( "filter-value" ) ).sendKeys( filterByToSet );
		return this;
	}
	
	public final SavedSetsPageDriver filter( final String valueToWaitForInFirstElement ){
		this.driver.findElement( By.id( "filter-go" ) ).click();
		
		Selenium2Utils.Wait.tryWaitForElementContainsByXPath( this.driver, "// *[@id=\"saved-set-table\"]/table/tbody[2]/tr[1]/td[2]/div", valueToWaitForInFirstElement, 1 );
		return this;
	}
	public SavedSetByIdDriver goInto( final int i ){
		this.driver.findElement( By.xpath( "//*[@id=\"saved-set-table\"]/table/tbody[2]/tr[" + i + "]/td[1]/div/a" ) ).click();
		
		return new SavedSetByIdDriver( this.driver );
	}
	
	// non-driver API
	
	public final int countSavedSetsInTable(){
		return this.driver.findElements( By.xpath( "//*[@id=\"saved-set-table\"]/table/tbody[2]/tr" ) ).size();
	}
	
}
