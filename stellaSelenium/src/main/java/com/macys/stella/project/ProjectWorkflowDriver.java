package com.macys.stella.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.base.AbstractDriver;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Preconditions;

public final class ProjectWorkflowDriver extends AbstractDriver{
	
	public ProjectWorkflowDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final ProjectByIdDriver approve(){
		this.getWebDriver().findElement( By.linkText( "APPROVE" ) ).click();
		
		Selenium2Utils.Wait.tryWaitForElementNotFoundByLinkText( this.getWebDriver(), "APPROVE", 1 );
		Selenium2Utils.Wait.tryWaitForElementFoundByLinkText( this.getWebDriver(), "REJECT", 1 );
		return new ProjectByIdDriver( this.getWebDriver() );
	}
	// TODO: obviously refactor this into something else than a String
	public final boolean isInState( final String nameOfState ){
		Preconditions.checkNotNull( nameOfState );
		
		return this.getCurrentState().equals( nameOfState );
	}
	
	@Override
	public final ProjectWorkflowDriver wait( final int seconds ){
		// Selenium2Utils.Wait.waitForElementDisplayedById( this.getWebDriver(), "workflow-action-project", 2 );
		Selenium2Utils.Wait.waitForElementEnabledById( this.getWebDriver(), "workflow-action-project", 2 );
		return this;
	}
	
	public final String getCurrentState(){
		final String text = this.getWebDriver().findElement( By.xpath( ".//*[@id='workflow-action-project']/div[1]/form/div[2]" ) ).getText();
		return text.substring( text.indexOf( '\n' ) + 1 ).trim();
	}
	
	// utils
	
}
