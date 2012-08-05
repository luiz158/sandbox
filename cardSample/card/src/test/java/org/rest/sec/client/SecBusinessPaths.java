package org.rest.sec.client;

import org.rest.common.client.SecPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("client")
public final class SecBusinessPaths {

    @Autowired
    SecPaths secPaths;

    // API

    public final String getPrivilegeUri() {
        return secPaths.getRootUri() + "businesscard";
    }

    public final String getRoleUri() {
        return secPaths.getRootUri() + "role";
    }

    public final String getRootUri() {
        return secPaths.getRootUri();
    }

}
