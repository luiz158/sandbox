package org.touchdata.frontend.spring.client;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Context configuration.
 */
@Configuration
@ComponentScan({ "org.touchdata.client", "org.touchdata.frontend.client" })
public class ClientConfig {
    //
}
