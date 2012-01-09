package com.macys.stella.product;

import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;
import com.macys.stella.util.Ids;

public final class ProductRegistryDriver extends StellaBaseDriver{
	
	public ProductRegistryDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	@Override
	public final ProductRegistryDriver wait( final int seconds ){
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), "product-wc", seconds );
		return this;
	}
	
	public final boolean isEditable(){
		return Selenium2Utils.isElementPresentById( this.getWebDriver(), Ids.EDIT_BUTTON );
	}
	
}
