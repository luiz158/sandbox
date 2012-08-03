package com.macys.stella.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.product.CreateProductDriver;
import com.macys.stella.project.CreateProjectDriver;
import com.macys.stella.savedSet.CreateSavedSetOfProductsDriver;

public final class LeftMenuDriver extends StellaBaseDriver{
	
	public LeftMenuDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	//
	
	final void createAction(){
		this.driver.findElement( By.id( "create-action" ) ).click();
	}
	
	public final LeftMenuDriver selectProductCreation(){
		this.driver.findElement( By.xpath( ".//select[@id='create-select']/option[1]" ) ).click();
		return this;
	}
	public final LeftMenuDriver selectProjectCreation(){
		this.driver.findElement( By.xpath( ".//select[@id='create-select']/option[2]" ) ).click();
		return this;
	}
	public final LeftMenuDriver selectSavedSetOfProductsCreation(){
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
	public final CreateProjectDriver createProject(){
		this.selectProjectCreation().createAction();
		// TODO: temporarily replace this with the wait on the driver
		// Selenium2Utils.Wait.waitForElementFoundById( this.driver, "createprojectform", 2 );
		return new CreateProjectDriver( this.driver );
	}
	
}
