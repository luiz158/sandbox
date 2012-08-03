package com.macys.stella.project;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.macys.stella.common.BaseLoggedInSeleniumTest;

public final class ProjectTest extends BaseLoggedInSeleniumTest{
	
	// tests
	
	// navigations
	
	@Test
	public final void whenNavigatingToProjects_thenNoExceptions(){
		// Given
		this.home().openProductsPage().wait( 1 ).openProjectsPage();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
	@Test
	public final void whenProjectIsCreated_thenNoExceptions(){
		// Given
		final CreateProjectDriver createProjectDriver = this.home().activateLeftMenuDriver().createProject().wait( 1 );
		
		// When
		createProjectDriver.fillRandom().create();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	
}
