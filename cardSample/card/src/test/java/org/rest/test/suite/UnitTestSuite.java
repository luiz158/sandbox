package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.common.search.ConstructQueryStringUnitTest;
import org.rest.sec.persistence.service.impl.BusinessCardServiceUnitTest;
import org.rest.sec.persistence.service.impl.ClientCardServiceUnitTest;
import org.rest.util.ParseQueryStringUnitTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessCardServiceUnitTest.class, ClientCardServiceUnitTest.class, ParseQueryStringUnitTest.class, ConstructQueryStringUnitTest.class })
public final class UnitTestSuite {
    //
}
