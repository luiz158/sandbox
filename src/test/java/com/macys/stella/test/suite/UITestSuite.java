package com.macys.stella.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.macys.stella.SandboxTest;
import com.macys.stella.login.AfterLoginTest;
import com.macys.stella.login.LoginTest;
import com.macys.stella.product.ProductCloneTest;
import com.macys.stella.product.ProductCopyTest;
import com.macys.stella.product.ProductTest;
import com.macys.stella.project.ProjectTest;
import com.macys.stella.savedSet.SavedSetTest;
import com.macys.stella.security.EditabilityTest;
import com.macys.stella.security.SecurityTest;
import com.macys.stella.security.WorkflowTest;

@RunWith( Suite.class )
@SuiteClasses( {// @formatter:off
	LoginTest.class, 
	ProductTest.class, 
	WorkflowTest.class, 
	ProductCloneTest.class, 
	EditabilityTest.class, 
	AfterLoginTest.class, 
	ProductTest.class, 
	LoginTest.class, 
	ProductCopyTest.class, 
	ProjectTest.class, 
	SavedSetTest.class, 
	SecurityTest.class, 
	
	SandboxTest.class
}) // @formatter:on
public class UITestSuite{
	//
}
