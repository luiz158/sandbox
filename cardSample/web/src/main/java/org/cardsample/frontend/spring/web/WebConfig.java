package org.cardsample.frontend.spring.web;

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
@ComponentScan("org.cardsample.frontend.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Registers the existing View Controllers for the application
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);
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
