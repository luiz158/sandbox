package com.macys.stella.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.base.AbstractDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.util.Ids;

public final class EditProductCopyDriver extends AbstractDriver{
	
	public EditProductCopyDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final ProductCopyDriver cancel(){
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, Ids.BUTTON_CANCEL2, 1 );
		
		this.driver.findElement( By.id( Ids.BUTTON_CANCEL2 ) ).click();
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, Ids.EDIT_BUTTON, 1 );
		
		return new ProductCopyDriver( this.getWebDriver() );
	}
	
}
