package com.macys.stella.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.common.base.Preconditions;
import com.macys.stella.HomePageDriver;
import com.macys.stella.common.StellaBaseDriver;

public final class LoginPageDriver extends StellaBaseDriver {

	public LoginPageDriver(final WebDriver driverToSet) {
		super(driverToSet);
	}

	// API

	public final LoginPageDriver name(final String nameToSet) {
		Preconditions.checkNotNull(nameToSet);

		this.driver.findElement(By.id("txtLoginId")).sendKeys(nameToSet);
		return this;
	}

	public final LoginPageDriver password(final String passwordToSet) {
		Preconditions.checkNotNull(passwordToSet);

		this.driver.findElement(By.id("txtPassword")).sendKeys(passwordToSet);
		return this;
	}

	public final HomePageDriver login() {
		this.driver.findElement(By.id("btnLogin")).click();
		return new HomePageDriver(this.driver);
	}

}
