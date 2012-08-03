package org.touchdata.frontend.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.userdetails.UserDetails;
import org.touchdata.frontend.security.SecurityHelper;

/**
 * This filter is responsible to fetch user details like username and authorization key and put them into response attributes.
 */
public class UserDetailsFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) {
	//
    }

    @Override
    public void destroy() {
	//
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
	final UserDetails userDetails = SecurityHelper.getCurrentUserDetails();
	if (userDetails != null) {
	    final String authorizationKey = SecurityHelper.calculateAuthorizationKey(userDetails);

	    request.setAttribute("username", userDetails.getUsername());
	    request.setAttribute("authorizationKey", authorizationKey);
	}

	chain.doFilter(request, response);
    }

}
