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
	
	// TODO: do not do this if the navigation is already here
	@Override
	public final HomePageDriver navigateToCurrent(){
		final String idOnHomepage = "nav-Home";
		this.driver.findElement( By.id( idOnHomepage ) ).click();
		
		return this.wait( 1 );
	}
	
	public final ProductsPageDriver openProductsPage(){
		this.driver.findElement( By.id( "nav-Product" ) ).click();
		
		Selenium2Utils.Wait.tryWaitForElementFoundByXPath( this.driver, ".//*[@id='webproducts-table']/table/tbody[2]/tr[1]/td/div/a", 2 );
		return new ProductsPageDriver( this.getWebDriver() );
	}
	
	public final boolean areContentCreationOptionsAvailable(){
		Selenium2Utils.Wait.tryWaitForElementDisplayedById( this.getWebDriver(), "workspace-create", 1 );
		return Selenium2Utils.isElementPresentById( this.getWebDriver(), "workspace-create" );
	}
	
	@Override
	public final HomePageDriver wait( final int seconds ){
		final String idOnHomepage = "nav-Home";
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), idOnHomepage, seconds );
		
		return this;
	}
	
}
