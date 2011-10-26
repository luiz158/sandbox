package com.macys.stella.product;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;

public final class ProductCrossSellsDriver extends StellaBaseDriver{
	
	public ProductCrossSellsDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final CrossSellCreationDriver startEditAndAddCrossSell(){
		this.getWebDriver().findElement( By.id( "edit-button" ) ).click();
		Selenium2Utils.Wait.waitForElementDisplayedById( this.getWebDriver(), "add-xsell-action", 1 );
		
		this.getWebDriver().findElement( By.id( "add-xsell-action" ) ).click();
		
		return new CrossSellCreationDriver( this.getWebDriver() );
	}
	
	//
	
	public final ProductCrossSellsDriver save(){
		Selenium2Utils.Wait.waitForElementDisplayedById( this.getWebDriver(), "save-button", 1 );
		this.getWebDriver().findElement( By.id( "save-button" ) ).click();
		
		Selenium2Utils.Wait.waitForElementDisplayedById( this.getWebDriver(), "edit-button", 2 );
		return this;
	}
	
	public final boolean hasCrossSells(){
		final List< WebElement > crossSell1Elements = this.getWebDriver().findElements( By.xpath( ".//*[@id='xsellsitemscontainer-1']/*" ) );
		final List< WebElement > crossSell2Elements = this.getWebDriver().findElements( By.xpath( ".//*[@id='xsellsitemscontainer-2']/*" ) );
		final List< WebElement > crossSell3Elements = this.getWebDriver().findElements( By.xpath( ".//*[@id='xsellsitemscontainer-3']/*" ) );
		final boolean noCrossSells = crossSell1Elements.isEmpty() && crossSell2Elements.isEmpty() && crossSell3Elements.isEmpty();
		return !noCrossSells;
	}
	public final boolean hasCrossSells( final int position ){
		final List< WebElement > crossSell1Elements = this.getWebDriver().findElements( By.xpath( ".//*[@id='xsellsitemscontainer-" + position + "']/*" ) );
		return !crossSell1Elements.isEmpty();
	}
	
	//
	
	@Override
	public final ProductCrossSellsDriver wait( final int seconds ){
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), "product-xsells", seconds );
		return this;
	}
	
}
