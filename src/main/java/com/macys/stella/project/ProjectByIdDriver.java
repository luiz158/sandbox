package com.macys.stella.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;
import com.macys.stella.util.Ids;

public final class ProjectByIdDriver extends StellaBaseDriver{
	
	public ProjectByIdDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API - generic
	
	@Override
	public final boolean isHere(){
		return false;
	}
	
	@Override
	public final ProjectByIdDriver wait( final int seconds ){
		Selenium2Utils.Wait.waitForElementFoundById( this.getWebDriver(), "title-buttons", 1 );
		
		return this;
	}
	
	// API - specific
	
	public final String retrieveId(){
		final WebElement element = this.getWebDriver().findElement( By.xpath( "//li[@id=\"itemId\"]" ) );
		return element.getText();
	}
	
	public final boolean isEditable(){
		return Selenium2Utils.isElementPresentById( this.getWebDriver(), Ids.EDIT_BUTTON );
	}
	
	public final ProjectWorkflowDriver expandWorkflow(){
		final WebElement element = this.getWebDriver().findElement( By.id( "workflow-action-project" ) );
		if( !element.isDisplayed() ){
			this.getWebDriver().findElement( By.id( "workflow-toggle-project" ) ).click();
		}
		
		return new ProjectWorkflowDriver( this.getWebDriver() ).wait( 1 );
	}
	
	public final boolean isLive(){
		final String projectIdAndState = this.getProductIdAndState();
		return projectIdAndState.contains( "Live" );
	}
	
	public final String getProductIdAndState(){
		return this.getWebDriver().findElement( By.xpath( ".//*[@id='project-info']/h1/span" ) ).getText();
	}
	
}
