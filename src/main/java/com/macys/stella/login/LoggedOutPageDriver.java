package com.macys.stella.login;

import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.common.StellaBaseDriver;

public class LoggedOutPageDriver extends StellaBaseDriver{
	
	public static final String LOGGED_OUT_PAGE_ID = "//*[contains(text(),'You have successfully logged out')]";
	
	public LoggedOutPageDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	// API
	
	@Override
	public boolean isHere(){
		if( Selenium2Utils.isElementPresentByXPath( this.driver, LoggedOutPageDriver.LOGGED_OUT_PAGE_ID ) ){
			return true;
		}
		return false;
	}
	
}
