package com.macys.stella;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.LeftMenuDriver;
import com.macys.stella.common.StellaBaseDriver;
import com.macys.stella.product.ProductsPageDriver;

public final class HomePageDriver extends StellaBaseDriver{
	
	public HomePageDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final LeftMenuDriver activateLeftMenuDriver(){
		return new LeftMenuDriver( this.getWebDriver() );
	}
	
	// navigation
	
	@Override
	public final HomePageDriver navigateToCurrent(){
		// TODO: do not do this if the navigation is already here
		this.driver.findElement( By.id( "nav-Home" ) ).click();
		return this;
	}
	
	public final ProductsPageDriver openProductsPage(){
		this.driver.findElement( By.id( "nav-Product" ) ).click();
		
		Selenium2Utils.Wait.tryWaitForElementFoundByXPath( this.driver, ".//*[@id='webproducts-table']/table/tbody[2]/tr[1]/td/div/a", 2 );
		return new ProductsPageDriver( this.getWebDriver() );
	}
	
}
