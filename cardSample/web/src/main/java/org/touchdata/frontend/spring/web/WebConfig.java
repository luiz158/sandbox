package org.touchdata.frontend.spring.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Spring Web configuration.
 */
@EnableWebMvc
@Configuration
@ComponentScan("org.touchdata.frontend.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Registers the existing View Controllers for the application
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/login.html");
        registry.addViewController("/register.html");
        registry.addViewController("/login_denied.html");
        registry.addViewController("/login_invalid.html");
        registry.addViewController("/access_denied.html");
    }

    /**
     * Register the {@link JstlView} ViewResolver that will be used by spring mvc and configure it.
     * 
     * @return a new instance of a {@link ViewResolver}
     */
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");
        bean.setExposedContextBeanNames(new String[] { "properties" });

        return bean;
    }

}
