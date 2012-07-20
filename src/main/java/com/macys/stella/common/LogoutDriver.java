package com.macys.stella.common;

import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.macys.stella.login.LoggedOutPageDriver;

public final class LogoutDriver extends StellaBaseDriver{
	
	public LogoutDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	/**
	 * - note: gracefully handles the case when this is called but logout has already been done
	 * @return
	 */
	@Override
	public final LoggedOutPageDriver logout(){
		if( Selenium2Utils.isElementPresentByXPath( this.driver, LoggedOutPageDriver.LOGGED_OUT_PAGE_ID ) ){
			return new LoggedOutPageDriver( this.getWebDriver() );
		}
		return super.logout();
	}
	
	// API
	
}
