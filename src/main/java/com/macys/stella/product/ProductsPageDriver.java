package com.macys.stella.product;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.LeftMenuDriver;
import com.macys.stella.common.StellaBaseDriver;

public final class ProductsPageDriver extends StellaBaseDriver{
	
	public ProductsPageDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	/**
	 * - note: if a product with the argument id does not exist, then null is returned <br>
	 */
	public final ProductByIdDriver goIntoProductById( final int id ){
		WebElement linkToProduct = null;
		try{
			linkToProduct = this.driver.findElement( By.linkText( "" + id ) );
		}
		catch( final NoSuchElementException noElEx ){
			linkToProduct = null;
		}
		
		if( linkToProduct != null ){
			linkToProduct.click();
			return null;
		}
		
		return new ProductByIdDriver( this.getWebDriver() );
	}
	
	/**
	 * - note: if there is no product at the argument index, then null is returned <br>
	 */
	public final ProductByIdDriver goIntoProductByIndex( final int index ){
		WebElement linkToProduct = null;
		try{
			linkToProduct = this.driver.findElement( By.xpath( ".//*[@id='webproducts-table']/table/tbody[2]/tr[" + index + "]/td/div/a" ) );
		}
		catch( final NoSuchElementException noElEx ){
			linkToProduct = null;
		}
		
		if( linkToProduct == null ){
			return null;
		}
		
		linkToProduct.click();
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), "workflow-section", 2 );
		return new ProductByIdDriver( this.getWebDriver() );
	}
	
	public final LeftMenuDriver activateLeftMenuDriver(){
		return new LeftMenuDriver( this.getWebDriver() );
	}
	
	//
	
	public final boolean hasProducts(){
		return true;
	}
	
}
