package com.macys.stella.product;

import static com.macys.stella.product.ProductConstants.departament;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Preconditions;
import com.macys.stella.common.StellaBaseDriver;

public final class CreateProductDriver extends StellaBaseDriver{
	
	public CreateProductDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final CreateProductDriver division( final String divisionToSet ){
		Preconditions.checkNotNull( divisionToSet );
		
		this.driver.findElement( By.id( "command.divisionId" ) ).sendKeys( divisionToSet );
		return this;
	}
	public final CreateProductDriver department( final String departmentToSet ){
		Preconditions.checkNotNull( departmentToSet );
		
		this.driver.findElement( By.id( "command.departmentId" ) ).sendKeys( departmentToSet );
		return this;
	}
	public final CreateProductDriver brandName( final String brandNameToSet ){
		Preconditions.checkNotNull( brandNameToSet );
		
		this.driver.findElement( By.id( "vendorTextBox" ) ).sendKeys( brandNameToSet );
		final String xpathExpression = ".//*[@id='products']/div[6]/div[1]/div[2]/ul/li[1]";
		Selenium2Utils.Wait.waitForElementDisplayedByXPath( this.driver, xpathExpression, 5 );
		this.driver.findElement( By.xpath( xpathExpression ) ).click();
		
		return this;
	}
	public final CreateProductDriver projectId( final String projectIdToSet ){
		Preconditions.checkNotNull( projectIdToSet );
		
		this.driver.findElement( By.id( "command.projectId" ) ).sendKeys( projectIdToSet );
		return this;
	}
	
	public final CreateProductDriver name( final String nameToSet ){
		Preconditions.checkNotNull( nameToSet );
		
		this.driver.findElement( By.id( "command.details[0].productName" ) ).sendKeys( nameToSet );
		return this;
	}
	public final CreateProductDriver price( final String priceToSet ){
		Preconditions.checkNotNull( priceToSet );
		
		this.driver.findElement( By.id( "command.details[0].ticketPrice" ) ).sendKeys( priceToSet );
		
		return this;
	}
	
	// API - actions
	
	public final ProductByIdDriver create(){
		this.driver.findElement( By.id( "create" ) ).click();
		
		this.waitForLoadingMask();
		
		return new ProductByIdDriver( this.driver );
	}
	
	// API - shortcuts
	
	public final CreateProductDriver fillRandom(){
		this.division( ProductConstants.division ).department( departament ).brandName( ProductConstants.brandName ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) );
		return this;
	}
	
}
