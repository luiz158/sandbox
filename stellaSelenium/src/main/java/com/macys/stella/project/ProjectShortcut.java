package com.macys.stella.project;

import com.google.common.base.Preconditions;

public final class ProjectShortcut{
	
	private ProjectShortcut(){
		throw new AssertionError();
	}
	
	// API
	
	public static ProjectByIdDriver goIntoRandomProject( final ProjectPageDriver pageDriver ){
		Preconditions.checkNotNull( pageDriver );
		
		final ProjectByIdDriver productDriver = pageDriver.goIntoEntityByIndex( 1 );
		if( productDriver != null ){
			return productDriver;
		}
		
		return pageDriver.activateLeftMenuDriver().createProject().fillRandom().create();
	}
	
	public static ProjectByIdDriver createAProject( final ProjectPageDriver projectsPageDriver ){
		final CreateProjectDriver createProjectDriver = projectsPageDriver.activateLeftMenuDriver().createProject();
		return createProjectDriver.fillRandom().create();
	}
	
}
