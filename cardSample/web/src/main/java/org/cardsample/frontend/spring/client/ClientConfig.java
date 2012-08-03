package org.cardsample.frontend.spring.client;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Context configuration.
 */
@Configuration
@ComponentScan({ "org.cardsample.client", "org.cardsample.frontend.client" })
public class ClientConfig {
    //
}
