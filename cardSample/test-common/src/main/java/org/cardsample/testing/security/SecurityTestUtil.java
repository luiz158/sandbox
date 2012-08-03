package org.cardsample.testing.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.collect.Lists;

public final class SecurityTestUtil {

    private SecurityTestUtil() {
	throw new AssertionError();
    }

    // API

    /**
     * Run next operations as specified user.
     * 
     * @param username
     *            the user username.
     * @param credentials
     *            the user password.
     * @param privilegeNames
     *            the list of user privilege names.
     */
    public static void runAs(final String username, final String credentials, final String... privilegeNames) {
	SecurityContextHolder.getContext().setAuthentication(new RunAsUserToken(username, username, credentials, asAuthorities(privilegeNames), null));
    }

    /**
     * Converts array of roles to collection of authorities.
     * 
     * @param privileges
     *            the array of privileges.
     * @return the collection of authorities.
     */
    public static Collection<GrantedAuthority> asAuthorities(final String[] privileges) {
	final List<GrantedAuthority> authorities = Lists.newArrayList();
	for (final String each : privileges) {
	    authorities.add(new SimpleGrantedAuthority(each));
	}
	return authorities;
    }

}
