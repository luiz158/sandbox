package org.touchdata.client.template;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.touchdata.security.BasicHttpComponentsClientHttpRequestFactory;
import org.touchdata.security.DigestHttpComponentsClientHttpRequestFactory;

@Component
@Profile("client")
public class RestTemplateFactoryBean implements FactoryBean<RestTemplate>, InitializingBean {
    private RestTemplate restTemplate;

    @Value("${sec.auth.basic}")
    boolean basicAuth;
    @Value("${http.req.timeout}")
    int timeout;

    // API

    @Override
    public RestTemplate getObject() {
	return restTemplate;
    }

    @Override
    public Class<RestTemplate> getObjectType() {
	return RestTemplate.class;
    }

    @Override
    public boolean isSingleton() {
	return true;
    }

    @Override
    public void afterPropertiesSet() {
	final DefaultHttpClient httpClient = new DefaultHttpClient();
	final HttpComponentsClientHttpRequestFactory requestFactory;
	if (basicAuth) {
	    requestFactory = new BasicHttpComponentsClientHttpRequestFactory(httpClient) {
		{
		    setReadTimeout(timeout);
		}
	    };
	} else {
	    requestFactory = new DigestHttpComponentsClientHttpRequestFactory(httpClient) {
		{
		    setReadTimeout(timeout);
		}
	    };
	}
	restTemplate = new RestTemplate(requestFactory);
    }

    //

}
