package org.touchdata.frontend.security;

import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.ImmutableSet;

/**
 * Helper to access security information, like current user details, if user is in role, is authenticated etc.
 */
public class SecurityHelper {

    /**
     * Gets the current user details.
     * 
     * @return the current user details or null if can't be retrieved.
     */
    public static UserDetails getCurrentUserDetails() {
	final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
	    return (UserDetails) principal;
	}

	return null;
    }

    /**
     * Check if current user has specified role.
     * 
     * @param role
     *            the role to check if user has.
     * @return true if user has specified role, otherwise false.
     */
    public static boolean hasRole(final String role) {
	final UserDetails userDetails = getCurrentUserDetails();
	if (userDetails != null) {
	    for (final GrantedAuthority each : userDetails.getAuthorities()) {
		if (each.getAuthority().equals(role)) {
		    return true;
		}
	    }
	}

	return false;
    }

    /**
     * Check if current user has any role of specified.
     * 
     * @param roles
     *            the array of roles.
     * @return true if has any role, otherwise false.
     */
    public static boolean hasAnyRole(final String... roles) {
	final UserDetails userDetails = getCurrentUserDetails();
	if (userDetails != null) {
	    final Set<String> rolesSet = ImmutableSet.copyOf(roles);
	    for (final GrantedAuthority each : userDetails.getAuthorities()) {
		if (rolesSet.contains(each.getAuthority())) {
		    return true;
		}
	    }
	}

	return false;
    }

    /**
     * Check if current user is anonymous guest.
     * 
     * @return true if user is anonymous guest.
     */
    public static boolean isAnonymous() {
	return getCurrentUserDetails() == null;
    }

    /**
     * Check if current user is authenticated.
     * 
     * @return true if user is authenticated.
     */
    public static boolean isAuthenticated() {
	return getCurrentUserDetails() != null;
    }

    /**
     * Calculates an authorization key for user.
     * 
     * @param userDetails
     *            the user details.
     * @return the calculated authorization key.
     */
    public static String calculateAuthorizationKey(UserDetails userDetails) {
	final String authorizationString = userDetails.getUsername() + ":" + userDetails.getPassword();
	return new String(Base64.encodeBase64(authorizationString.getBytes()));
    }
}
