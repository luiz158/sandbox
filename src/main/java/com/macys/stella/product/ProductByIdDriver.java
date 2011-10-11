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
	
	// API
	
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
	
	// API - retrieve
	
	public final String retrieveId(){
		final WebElement element = this.getWebDriver().findElement( By.xpath( "//li[@id=\"itemId\"]" ) );
		return element.getText();
	}
}
