package com.macys.stella.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;

public final class ProductCloningDriver extends StellaBaseDriver{
	
	public ProductCloningDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	//
	/**
	 * - <b>note</b>: because the cloning is a lengthy process (in Selenium terms), and also because there seems to be an intermediary state when the mask disappears and reappears <br>
	 * the logic here will wait for a few elements on the page <br>
	 */
	public final ProductByIdDriver save(){
		this.driver.findElement( By.id( "save-button" ) ).click();
		
		Selenium2Utils.Wait.tryWaitForElementDisplayedById( this.getWebDriver(), "itemId", 1 );
		Selenium2Utils.Wait.tryWaitForElementDisplayedById( this.getWebDriver(), "headerPricing", 1 );
		Selenium2Utils.Wait.tryWaitForElementDisplayedById( this.getWebDriver(), "product-overview-form", 1 );
		
		return new ProductByIdDriver( this.getWebDriver() );
	}
	
}
