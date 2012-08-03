package com.macys.stella.product;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.LeftMenuDriver;
import com.macys.stella.common.StellaBaseDriver;
import com.macys.stella.project.ProjectPageDriver;

public final class ProductsPageDriver extends StellaBaseDriver{
	
	public ProductsPageDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	// navigation
	
	/**
	 * - note: if a product with the argument id does not exist, then null is returned <br>
	 */
	public final ProductByIdDriver goIntoEntityById( final int id ){
		WebElement linkToEntity = null;
		try{
			linkToEntity = this.driver.findElement( By.linkText( "" + id ) );
		}
		catch( final NoSuchElementException noElEx ){
			linkToEntity = null;
		}
		
		if( linkToEntity != null ){
			linkToEntity.click();
			return null;
		}
		
		return new ProductByIdDriver( this.getWebDriver() );
	}
	
	/**
	 * - note: if there is no product at the argument index, then null is returned <br>
	 */
	public final ProductByIdDriver goIntoEntityByIndex( final int index ){
		WebElement linkToEntity = null;
		try{
			linkToEntity = this.driver.findElement( By.xpath( ".//*[@id='webproducts-table']/table/tbody[2]/tr[" + index + "]/td/div/a" ) );
		}
		catch( final NoSuchElementException noElEx ){
			linkToEntity = null;
		}
		
		if( linkToEntity == null ){
			return null;
		}
		
		linkToEntity.click();
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), "workflow-section", 2 );
		return new ProductByIdDriver( this.getWebDriver() );
	}
	
	@Override
	public final ProductsPageDriver wait( final int seconds ){
		Selenium2Utils.Wait.waitForElementDisplayedById( this.getWebDriver(), "webproducts-table", 1 );
		return this;
	}
	
	public final ProjectPageDriver openProjectsPage(){
		this.driver.findElement( By.partialLinkText( "Projects" ) ).click();
		
		return new ProjectPageDriver( this.getWebDriver() );
	}
	
	//
	
	public final boolean hasProducts(){
		return true;
	}
	
	// non-API
	
	final LeftMenuDriver activateLeftMenuDriver(){
		return new LeftMenuDriver( this.getWebDriver() );
	}
	
}
