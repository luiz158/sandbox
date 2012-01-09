package com.macys.stella.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.selenium.base.AbstractTest;

import com.macys.stella.HomePageDriver;
import com.macys.stella.login.LoginPageDriver;
import com.macys.stella.project.ProjectByIdDriver;
import com.macys.stella.project.ProjectShortcut;
import com.macys.stella.util.ContextConstants;

public final class WorkflowTest extends AbstractTest{
	private static final String STATE_FINAL_APPROVAL = "FinalApproval";
	private static final String STATE_CONTENT_CREATION = "ContentCreation";
	
	@After
	public final void after(){
		new LoginPageDriver( this.getWebDriver() ).logoutIfLoggedIn();
	}
	
	// tests
	@Test
	public final void whenProjectIsCreated_thenProjectIsNotLive(){
		// Given
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		
		// When
		final ProjectByIdDriver projectByIdDriver = ProjectShortcut.createAProject( this.home().openProductsPage().openProjectsPage() );
		
		// Then
		assertFalse( projectByIdDriver.isLive() );
	}
	@Test
	public final void whenProjectIsCreated_thenProjectIsInFirstState(){
		// Given
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		
		// When
		final ProjectByIdDriver projectByIdDriver = ProjectShortcut.createAProject( this.home().openProductsPage().openProjectsPage() );
		
		// Then
		assertTrue( projectByIdDriver.expandWorkflow().isInState( STATE_CONTENT_CREATION ) );
	}
	
	@Test
	public final void givenSuperuserIsLoggedIn_whenProjectIsApporvedOnce_thenProjectNoLongerInFirstState(){
		// Given
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		
		// When
		final ProjectByIdDriver projectByIdDriver = ProjectShortcut.createAProject( this.home().openProductsPage().openProjectsPage() );
		
		// Then
		assertEquals( STATE_CONTENT_CREATION, projectByIdDriver.expandWorkflow().getCurrentState() );
	}
	@Test
	public final void givenSuperuserIsLoggedIn_whenProjectIsApporvedOnce_thenNoProblems(){
		// Given
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		final ProjectByIdDriver projectByIdDriver = ProjectShortcut.createAProject( this.home().openProductsPage().openProjectsPage() );
		
		// When
		projectByIdDriver.expandWorkflow().approve();
		
		// Then
		assertFalse( projectByIdDriver.anyProblem() );
	}
	@Test
	public final void givenSuperuserIsLoggedIn_whenProjectIsApporvedOnce_thenProjectInSecondState(){
		// Given
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		final ProjectByIdDriver projectByIdDriver = ProjectShortcut.createAProject( this.home().openProductsPage().openProjectsPage() );
		
		// When
		projectByIdDriver.expandWorkflow().approve();
		
		// Then
		assertEquals( STATE_FINAL_APPROVAL, projectByIdDriver.expandWorkflow().getCurrentState() );
	}
	
	@Test
	public final void givenSuperuserIsLoggedInAndTheProjectHasBeenApprovedOnce_whenProjectIsApporvedAgain_thenNoProblems(){
		// Given
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		final ProjectByIdDriver projectByIdDriver = ProjectShortcut.createAProject( this.home().openProductsPage().openProjectsPage() );
		projectByIdDriver.expandWorkflow().approve();
		
		// When
		projectByIdDriver.expandWorkflow().approve();
		
		// Then
		assertFalse( this.getDriver().anyProblem() );
	}
	@Test
	public final void givenSuperuserIsLoggedInAndTheProjectHasBeenApprovedOnce_whenProjectIsApporvedAgain_thenProjectIsLive(){
		// Given
		this.login( ContextConstants.SUPER_USER, ContextConstants.PASSWORD );
		final ProjectByIdDriver projectByIdDriver = ProjectShortcut.createAProject( this.home().openProductsPage().openProjectsPage() );
		projectByIdDriver.expandWorkflow().approve();
		
		// When
		projectByIdDriver.expandWorkflow().approve().wait( 1 );
		
		// Then
		assertTrue( "project is: " + projectByIdDriver.getProductIdAndState(), projectByIdDriver.isLive() );
	}
	
	//
	
	final void login( final String user, final String pass ){
		new LoginPageDriver( this.getWebDriver() ).openLoginPage().name( user ).password( pass ).login();
	}
	
	final HomePageDriver home(){
		return new HomePageDriver( this.getWebDriver() ).wait( 1 );
	}
	
}
