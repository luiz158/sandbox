package com.macys.stella.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Preconditions;
import com.macys.stella.HomePageDriver;
import com.macys.stella.common.StellaBaseDriver;

public final class LoginPageDriver extends StellaBaseDriver{
	
	private static final String LOGIN_USERNAME_ID = "login_username";
	private static final String LOGIN_PASSWORD_ID = "login_password";
	
	public LoginPageDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	public final LoginPageDriver username( final String usernameToSet ){
		Preconditions.checkNotNull( usernameToSet );
		
		this.driver.findElement( By.id( LOGIN_USERNAME_ID ) ).sendKeys( usernameToSet );
		return this;
	}
	
	public final LoginPageDriver password( final String passwordToSet ){
		Preconditions.checkNotNull( passwordToSet );
		
		this.driver.findElement( By.id( LOGIN_PASSWORD_ID ) ).sendKeys( passwordToSet );
		return this;
	}
	
	public final HomePageDriver login(){
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, StellaBaseDriver.LOGIN_LOGIN_BUTTON_ID, 1 );
		
		this.driver.findElement( By.id( StellaBaseDriver.LOGIN_LOGIN_BUTTON_ID ) ).click();
		return new HomePageDriver( this.driver );
	}
	
}
