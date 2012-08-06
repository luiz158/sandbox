package org.cardsample.frontend.spring.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Spring Context configuration.
 */
@Configuration
@ImportResource("classpath*:*webContextConfig.xml")
public class ContextConfig {

    public ContextConfig() {
        super();
    }

}
