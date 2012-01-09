package com.macys.stella.project;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Preconditions;
import com.macys.stella.common.StellaBaseDriver;

public final class CreateProjectDriver extends StellaBaseDriver{
	
	public CreateProjectDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	// API - actions
	
	public final ProjectByIdDriver create(){
		this.driver.findElement( By.id( "create" ) ).click();
		
		this.waitForLoadingMask();
		
		return new ProjectByIdDriver( this.driver );
	}
	
	// fill data
	
	public final CreateProjectDriver name( final String name ){
		Preconditions.checkNotNull( name );
		
		this.driver.findElement( By.id( "createProjectCommand.projectName" ) ).sendKeys( name );
		return this;
	}
	public final CreateProjectDriver description( final String description ){
		Preconditions.checkNotNull( description );
		
		this.driver.findElement( By.id( "createProjectCommand.projectDescription" ) ).sendKeys( description );
		return this;
	}
	public final CreateProjectDriver startDate( final String startDate ){
		Preconditions.checkNotNull( startDate );
		
		this.driver.findElement( By.id( "start-date" ) ).sendKeys( startDate );
		return this;
	}
	public final CreateProjectDriver endDate( final String endDate ){
		Preconditions.checkNotNull( endDate );
		
		this.driver.findElement( By.id( "end-date" ) ).sendKeys( endDate );
		return this;
	}
	public final CreateProjectDriver approvalDate( final String date ){
		Preconditions.checkNotNull( date );
		
		this.driver.findElement( By.id( "approval-date" ) ).sendKeys( date );
		return this;
	}
	public final CreateProjectDriver turnInDate( final String date ){
		Preconditions.checkNotNull( date );
		
		this.driver.findElement( By.id( "turnin-date" ) ).sendKeys( date );
		return this;
	}
	
	// shortcuts
	
	public final CreateProjectDriver fillRandom(){
		this.name( randomAlphabetic( 6 ) ).description( randomAlphabetic( 6 ) ).startDate( "11/16/2011" ).endDate( "11/18/2011" ).approvalDate( "11/17/2011" ).turnInDate( "11/19/2011" );
		return this;
	}
	
	//
	
	@Override
	public final CreateProjectDriver wait( final int seconds ){
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, "create-project", seconds );
		return this;
	}
	
}
