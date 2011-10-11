package com.macys.stella;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;
import com.macys.stella.product.CreateProductDriver;
import com.macys.stella.savedSet.CreateSavedSetOfProductsDriver;

public final class HomePageDriver extends StellaBaseDriver{
	
	public HomePageDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	final void createAction(){
		this.driver.findElement( By.id( "create-action" ) ).click();
	}
	
	public final HomePageDriver selectProductCreation(){
		this.driver.findElement( By.xpath( ".//select[@id='create-select']/option[1]" ) ).click();
		return this;
	}
	public final HomePageDriver selectSavedSetOfProductsCreation(){
		this.driver.findElement( By.xpath( ".//select[@id='create-select']/option[9]" ) ).click();
		return this;
	}
	
	public final CreateProductDriver createProduct(){
		this.selectProductCreation().createAction();
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, "createproductform", 2 );
		return new CreateProductDriver( this.driver );
	}
	public final CreateSavedSetOfProductsDriver createSavedSetOfProducts(){
		this.selectSavedSetOfProductsCreation().createAction();
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, "create-savedset", 2 );
		return new CreateSavedSetOfProductsDriver( this.driver );
	}
	
	// navigation
	
	@Override
	public final HomePageDriver navigateToCurrent(){
		this.driver.findElement( By.id( "nav-Home" ) ).click();
		return this;
	}
	
}
