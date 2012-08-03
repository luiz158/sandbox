package org.touchdata.frontend.security;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.touchdata.sec.model.Principal;
import org.touchdata.sec.model.Role;
import org.touchdata.security.client.IAuthenticationRESTTemplate;

import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * - note: the original is DaoAuthenticationProvider <br/>
 * An {@link AuthenticationProvider} implementation that retrieves user details from a {@link UserDetailsService}.
 */
@Component
public class RestAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    @Qualifier("authenticationRESTTemplate")
    private IAuthenticationRESTTemplate authenticationApi;

    // ~ Instance fields ================================================================================================

    private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();

    private SaltSource saltSource;

    public RestAuthenticationProvider() {
	super();
    }

    // ~ Methods ========================================================================================================

    @Override
    @SuppressWarnings("deprecation")
    protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	Object salt = null;

	if (saltSource != null) {
	    salt = saltSource.getSalt(userDetails);
	}

	if (authentication.getCredentials() == null) {
	    logger.debug("Authentication failed: no credentials provided");

	    throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
	}

	final String presentedPassword = authentication.getCredentials().toString();

	if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
	    logger.debug("Authentication failed: password does not match stored value");

	    throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
	}
    }

    @Override
    protected final UserDetails retrieveUser(final String name, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	final String password = authentication.getCredentials().toString();

	UserDetails loadedUser = null;
	try {
	    final ResponseEntity<Principal> createAuthenticationResponse = authenticationApi.authenticate(name, password);
	    if (createAuthenticationResponse.getStatusCode().value() == 401) {
		// temporary - the idea here is to generate the not authorized exception - not by hand, but by returning wrong credentials which in turn will be refused later
		return new User("wrongUsername", "wrongPass", Lists.<GrantedAuthority> newArrayList());
	    }
	    /*
	     * if (createAuthenticationResponse.getStatusCode().value() != 200 && createAuthenticationResponse.getStatusCode().value() != 201) {
	     * logger.warn("Problem in the RESTful authentication process; status code received: " + createAuthenticationResponse.getStatusCode().value()); }
	     */
	    final Principal principalFromREST = createAuthenticationResponse.getBody();

	    Preconditions.checkState(principalFromREST.getName().equals(name));
	    Preconditions.checkState(principalFromREST.getPassword().equals(password));

	    final Set<String> privilegesFromRest = Sets.newHashSet();
	    final Set<Role> roles = principalFromREST.getRoles();
	    for (final Role role : roles) {
		privilegesFromRest.addAll(Collections2.transform(role.getPrivileges(), Functions.toStringFunction()));
	    }
	    final String[] roleStringsAsArray = privilegesFromRest.toArray(new String[privilegesFromRest.size()]);
	    final List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleStringsAsArray);

	    loadedUser = new User(name, password, authorities);
	} catch (final UsernameNotFoundException notFound) {
	    throw notFound;
	} catch (final Exception repositoryProblem) {
	    throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
	}

	return loadedUser;
    }

    /**
     * Sets the PasswordEncoder instance to be used to encode and validate passwords. If not set, the password will be compared as plain text.
     * <p/>
     * For systems which are already using salted password which are encoded with a previous release, the encoder should be of type {@code org.springframework.security.authentication.encoding.PasswordEncoder}. Otherwise,
     * the recommended approach is to use {@code org.springframework.security.crypto.password.PasswordEncoder}.
     * 
     * @param passwordEncoder
     *            must be an instance of one of the {@code PasswordEncoder} types.
     */
    public void setPasswordEncoder(final Object passwordEncoder) {
	Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");

	if (passwordEncoder instanceof PasswordEncoder) {
	    this.passwordEncoder = (PasswordEncoder) passwordEncoder;
	    return;
	}

	if (passwordEncoder instanceof org.springframework.security.crypto.password.PasswordEncoder) {
	    final org.springframework.security.crypto.password.PasswordEncoder delegate = (org.springframework.security.crypto.password.PasswordEncoder) passwordEncoder;
	    this.passwordEncoder = new PasswordEncoder() {
		@Override
		public String encodePassword(final String rawPass, final Object salt) {
		    checkSalt(salt);
		    return delegate.encode(rawPass);
		}

		@Override
		public boolean isPasswordValid(final String encPass, final String rawPass, final Object salt) {
		    checkSalt(salt);
		    return delegate.matches(rawPass, encPass);
		}

		private void checkSalt(final Object salt) {
		    Assert.isNull(salt, "Salt value must be null when used with crypto module PasswordEncoder");
		}
	    };

	    return;
	}

	throw new IllegalArgumentException("passwordEncoder must be a PasswordEncoder instance");
    }

    protected PasswordEncoder getPasswordEncoder() {
	return passwordEncoder;
    }

    /**
     * The source of salts to use when decoding passwords. <code>null</code> is a valid value, meaning the <code>DaoAuthenticationProvider</code> will present <code>null</code> to the relevant
     * <code>PasswordEncoder</code>.
     * <p/>
     * Instead, it is recommended that you use an encoder which uses a random salt and combines it with the password field. This is the default approach taken in the {@code org.springframework.security.crypto.password}
     * package.
     * 
     * @param saltSource
     *            to use when attempting to decode passwords via the <code>PasswordEncoder</code>
     */
    public void setSaltSource(final SaltSource saltSource) {
	this.saltSource = saltSource;
    }

    protected SaltSource getSaltSource() {
	return saltSource;
    }

}
