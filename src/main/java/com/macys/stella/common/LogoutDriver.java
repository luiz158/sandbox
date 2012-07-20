package com.macys.stella.common;

import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

public final class LogoutDriver extends StellaBaseDriver{
	
	public LogoutDriver( final WebDriver driverToSet ){
		super( driverToSet );
	}
	
	@Override
	public final void logout(){
		if( Selenium2Utils.isElementPresentByXPath( this.driver, "//*[contains(text(),'You have successfully logged out')]" ) ){
			return;
		}
		super.logout();
	}
	
	// API
	
}
