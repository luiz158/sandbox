package com.macys.stella.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;

public final class CrossSellCreationDriver extends StellaBaseDriver{
	
	public CrossSellCreationDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final CrossSellCreationDriver byDescription(){
		this.getWebDriver().findElement( By.xpath( "//select[@id=\"search-by\"]/option[1]" ) ).click();
		return this;
	}
	
	public final CrossSellCreationDriver value( final String valueToSet ){
		this.getWebDriver().findElement( By.id( "search-value" ) ).sendKeys( valueToSet );
		return this;
	}
	
	public final CrossSellCreationDriver find(){
		this.getWebDriver().findElement( By.id( "find-button" ) ).click();
		return this;
	}
	
	public final CrossSellCreationDriver select( final int index ){
		this.getWebDriver().findElement( By.xpath( "//*[@id=\"finder-table\"]/table/tbody[2]/tr[" + index + "]/td[3]/div/input" ) ).click();
		return this;
	}
	
	@Override
	public final CrossSellCreationDriver wait( final int seconds ){
		Selenium2Utils.Wait.tryWaitForElementFoundByXPath( this.getWebDriver(), "//*[@id=\"finder-table\"]/table/tbody[2]/tr[1]/td[3]/div/input", seconds );
		return this;
	}
	
	public final ProductCrossSellsDriver done( final int positionOfExpectedCrossSells ){
		this.getWebDriver().findElement( By.id( "add-selected" ) ).click();
		
		Selenium2Utils.Wait.waitForElementFoundByXPath( this.getWebDriver(), ".//*[@id='xsellsitemscontainer-" + positionOfExpectedCrossSells + "']/*", 2 );
		Selenium2Utils.Wait.waitForElementEnabledById( this.driver, "save-button", 1 );
		return new ProductCrossSellsDriver( this.getWebDriver() );
	}
	
	public CrossSellCreationDriver changePosition( final int positionOfCrossSells ){
		this.getWebDriver().findElement( By.xpath( ".//*[@id='xsell-position']/option[" + positionOfCrossSells + "]" ) ).click();
		
		return this;
	}
}
