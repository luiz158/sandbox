package com.macys.stella.project;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.LeftMenuDriver;
import com.macys.stella.common.StellaBaseDriver;

public final class ProjectPageDriver extends StellaBaseDriver{
	
	public ProjectPageDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	/**
	 * - note: if a product with the argument id does not exist, then null is returned <br>
	 */
	public final ProjectByIdDriver goIntoProductById( final int id ){
		WebElement linkToProject = null;
		try{
			linkToProject = this.driver.findElement( By.linkText( "" + id ) );
		}
		catch( final NoSuchElementException noElEx ){
			linkToProject = null;
		}
		
		if( linkToProject != null ){
			linkToProject.click();
			return null;
		}
		
		return new ProjectByIdDriver( this.getWebDriver() );
	}
	
	/**
	 * - note: if there is no product at the argument index, then null is returned <br>
	 */
	public final ProjectByIdDriver goIntoEntityByIndex( final int index ){
		WebElement linkToEntity = null;
		try{
			linkToEntity = this.driver.findElement( By.xpath( ".//*[@id='projects-table']/table/tbody[2]/tr[" + index + "]/td/div/a" ) );
		}
		catch( final NoSuchElementException noElEx ){
			linkToEntity = null;
		}
		
		if( linkToEntity == null ){
			return null;
		}
		
		linkToEntity.click();
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), "workflow-section", 2 );
		return new ProjectByIdDriver( this.getWebDriver() );
	}
	
	public final LeftMenuDriver activateLeftMenuDriver(){
		return new LeftMenuDriver( this.getWebDriver() );
	}
	
	//
	
	public final boolean hasProducts(){
		return true;
	}
	
}
