package com.macys.stella.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;
import com.macys.stella.util.Ids;

public final class ProductCopyDriver extends StellaBaseDriver{
	
	public ProductCopyDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final boolean isEditable(){
		return Selenium2Utils.isElementPresentById( this.getWebDriver(), Ids.EDIT_BUTTON );
	}
	
	@Override
	public final ProductCopyDriver wait( final int seconds ){
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), "product-copy", seconds );
		return this;
	}
	
	public final EditProductCopyDriver startEdit(){
		this.getWebDriver().findElement( By.id( Ids.EDIT_BUTTON ) ).click();
		Selenium2Utils.Wait.waitForElementDisplayedById( this.getWebDriver(), "add-copy-attribute", 1 );
		
		return new EditProductCopyDriver( this.getWebDriver() );
	}
	
}
