package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import app.util.MultiTenancyInterceptor;

/*Esta clase extiende de una clase abstracto de WebMvcConfigurerAdapter
que permite sobreescribir un metodo para enviar un interceptor por peticion*/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MultiTenancyInterceptor());
	}
}
