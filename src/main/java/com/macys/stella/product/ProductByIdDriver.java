package com.macys.stella.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;

public final class ProductByIdDriver extends StellaBaseDriver{
	
	public ProductByIdDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API - generic
	
	@Override
	public final boolean isHere(){
		return Selenium2Utils.isElementPresentById( this.driver, "globalproduct" );
	}
	
	@Override
	public final ProductByIdDriver wait( final int seconds ){
		Selenium2Utils.Wait.tryWaitForElementFoundById( this.driver, "globalproduct", seconds );
		Selenium2Utils.Wait.tryWaitForElementFoundById( this.driver, "main-content-area", 1 );
		return this;
	}
	
	// API - specific
	
	public final String retrieveId(){
		final WebElement element = this.getWebDriver().findElement( By.xpath( "//li[@id=\"itemId\"]" ) );
		return element.getText();
	}
	// TODO: this is not only relevant for the product by id driver - MOVE
	public final ProductCloningDriver triggerCloning(){
		this.getWebDriver().findElement( By.id( "clone-action" ) ).click();
		
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), "clone-form", 4 );
		Selenium2Utils.Wait.waitForElementDisplayedById( this.getWebDriver(), "clone-form", 2 );
		return new ProductCloningDriver( this.getWebDriver() );
	}
	
	public final ProductCrossSellsDriver openCrossSellsTab(){
		final String topMenuLinkText = "Merchandising";
		final String submenuLinkText = "Cross Sells";
		
		Selenium2Utils.Wait.waitForElementDisplayedByLinkText( this.driver, topMenuLinkText, 2 );
		this.getWebDriver().findElement( By.linkText( topMenuLinkText ) ).click();
		
		Selenium2Utils.Wait.waitForElementDisplayedByLinkText( this.driver, submenuLinkText, 2 );
		this.getWebDriver().findElement( By.linkText( submenuLinkText ) ).click();
		
		return new ProductCrossSellsDriver( this.getWebDriver() ).wait( 2 );
	}
	
}
