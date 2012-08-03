package org.touchdata.frontend.spring.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath*:*webSecurityConfig.xml" })
@ComponentScan({ "org.touchdata.security", "org.touchdata.frontend.security" })
public class SecurityConfig {
    //
}
