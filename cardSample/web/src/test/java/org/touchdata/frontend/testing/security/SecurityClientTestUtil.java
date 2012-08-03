package org.touchdata.frontend.testing.security;

import static org.touchdata.sec.util.SecurityConstants.ADMIN_EMAIL;
import static org.touchdata.sec.util.SecurityConstants.ADMIN_PASSWORD;
import static org.touchdata.sec.util.SecurityConstants.END_USER_EMAIL;
import static org.touchdata.sec.util.SecurityConstants.END_USER_PASS;

import java.util.Collection;

import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.touchdata.sec.util.SecurityConstants.Privileges;
import org.touchdata.testing.security.SecurityTestUtil;

/**
 * A security util used for testing.
 */
public class SecurityClientTestUtil {

    // API

    /** Run next operations as Anonymous. */
    public static void runAsAnonymous() {
        SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("anonymousUser", "anonymousUser", SecurityTestUtil.asAuthorities(new String[] { "ROLE_ANONYMOUS" })));
    }

    /** Run next operations as Enduser user. */
    public static UserDetails runAsEnduser() {
        final UserDetails user = new User(END_USER_EMAIL, END_USER_PASS, getEnduserAuthorities());
        SecurityContextHolder.getContext().setAuthentication(new RunAsUserToken(END_USER_EMAIL, user, END_USER_PASS, getEnduserAuthorities(), null));
        return user;
    }

    /** Run next operations as Administrator user. */
    public static void runAsAdmin() {
        final UserDetails user = new User(ADMIN_EMAIL, ADMIN_PASSWORD, getAdminAuthorities());
        SecurityContextHolder.getContext().setAuthentication(new RunAsUserToken(END_USER_EMAIL, user, END_USER_PASS, getEnduserAuthorities(), null));
    }

    // util

    /**
     * Gets the collection of Admin authorities.
     * 
     * @return the collection of admin authorities.
     */
    private static Collection<GrantedAuthority> getAdminAuthorities() {
        return SecurityTestUtil.asAuthorities(new String[] { Privileges.ROLE_ADMIN, Privileges.CAN_PRIVILEGE_WRITE, Privileges.CAN_USER_WRITE, Privileges.CAN_PRINCIPAL_WRITE });
    }

    /**
     * Gets the collection of Enduser authorities.
     * 
     * @return the collection of enduser authorities.
     */
    private static Collection<GrantedAuthority> getEnduserAuthorities() {
        return SecurityTestUtil.asAuthorities(new String[] { Privileges.ROLE_ENDUSER, });
    }
}
