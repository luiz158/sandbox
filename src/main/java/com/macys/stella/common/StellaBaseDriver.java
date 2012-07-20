package com.macys.stella.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.base.AbstractDriver;
import org.selenium.event.DriverChangeEvent;
import org.selenium.util.Selenium2Utils;
import org.selenium.util.SpringContext;

import com.macys.stella.login.LoggedOutPageDriver;
import com.macys.stella.login.LoginPageDriver;

public abstract class StellaBaseDriver extends AbstractDriver{
	private static final String LOGOUT_ID = "logout-link";
	private static final String ERROR_POPUP_ID = "alert-dialog-box_c"; // another option for stella: "alert-dialog-box"
	private static final String ERROR_EMBEDDED_XPATH = "//div[@id='errorMessages']";
	
	public static final String LOGIN_LOGIN_BUTTON_ID = "login_loginBtn";
	
	public StellaBaseDriver( final WebDriver driverToSet ){
		super( driverToSet );
		
		if( SpringContext.context() != null ){
			SpringContext.context().publishEvent( new DriverChangeEvent( this, this ) );
		}
	}
	
	// API
	
	public final LoginPageDriver openLoginPage(){
		this.driver.get( "http://localhost:8080/UI/Common/Html/Login.jsp" );
		
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, LOGIN_LOGIN_BUTTON_ID, 3 );
		return new LoginPageDriver( this.driver );
	}
	
	public final void logoutIfLoggedIn(){
		try{
			final WebElement logoutElement = this.driver.findElement( By.id( LOGOUT_ID ) );
			logoutElement.click();
		}
		catch( final NoSuchElementException e ){
			// do nothing, logout not needed
		}
	}
	public LoggedOutPageDriver logout(){
		final WebElement logoutElement = this.driver.findElement( By.id( LOGOUT_ID ) );
		logoutElement.click();
		return new LoggedOutPageDriver( this.getWebDriver() );
	}
	
	protected final void waitForLoadingMask(){
		/*
		Selenium2Utils.tryWaitForElementDisplayedByXPath( this.driver, XPATH_OF_WAIT_MASK, 1 );
		if( Selenium2Utils.isElementDisplayedByXPath( this.driver, XPATH_OF_WAIT_MASK ) ){
			Selenium2Utils.waitForElementNotDisplayedByXPath( this.driver, XPATH_OF_WAIT_MASK, 2 );
		}
		*/
	}
	
	@Override
	public final boolean isErrorPopupPresent(){
		return Selenium2Utils.isElementDisplayedById( this.driver, ERROR_POPUP_ID );
	}
	
	@Override
	public final boolean isErrorEmbeddedPresent(){
		return Selenium2Utils.isElementDisplayedByXPath( this.driver, ERROR_EMBEDDED_XPATH );
	}
	
}
