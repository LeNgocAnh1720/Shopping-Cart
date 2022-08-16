package poly.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import poly.store.GlobalIntercreptor;

@Configuration
public class InterConfig implements WebMvcConfigurer{
	@Autowired
	GlobalIntercreptor globalIntercreptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalIntercreptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/rest/**","/admin/**","/assets/**");
	}
}
