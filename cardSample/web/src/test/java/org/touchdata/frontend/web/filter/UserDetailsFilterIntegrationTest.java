package org.touchdata.frontend.web.filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.touchdata.frontend.security.SecurityHelper;
import org.touchdata.frontend.testing.security.SecurityClientTestUtil;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;

/**
 * Tests for {@link UserDetailsFilter}.
 */
public class UserDetailsFilterIntegrationTest {

    private UserDetailsFilter filter;

    // mocks
    private ServletRequest mockRequest;
    private ServletResponse mockResponse;
    private FilterChain mockChain;

    // Setup

    @Before
    public void before() {
        filter = new UserDetailsFilter();

        mockRequest = new MockHttpServletRequest();
        mockResponse = new MockHttpServletResponse();
        mockChain = new MockFilterChain();
    }

    // Tests

    @Test
    public void givenAnonymous_whenPassTheFilter_thenNoUserDetailsInRequest() throws Exception {
        // given
        SecurityClientTestUtil.runAsAnonymous();

        // when
        filter.doFilter(mockRequest, mockResponse, mockChain);

        // then
        final Iterator<String> iterator = Iterators.forEnumeration(mockRequest.getAttributeNames());
        assertThat(ImmutableSet.copyOf(iterator), not(hasItems("username", "authorizationKey")));
    }

    @Test
    public void givenAuthorized_whenPassTheFilter_thenAuthorizationKeyIsCorrect() throws Exception {
        // given
        final UserDetails userDetails = SecurityClientTestUtil.runAsEnduser();

        // when
        filter.doFilter(mockRequest, mockResponse, mockChain);

        // then
        assertThat((String) mockRequest.getAttribute("authorizationKey"), equalTo(SecurityHelper.calculateAuthorizationKey(userDetails)));
    }
}
