package com.ssafy.fit.config;

import com.ssafy.fit.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
	// 요기에다가
	
	//CORS 에러를 해결하기 위해서 컨트롤러에서 세분화 하여 처리할 수도 있지만
	//전역설정처럼 여기서 한방에 처리할 수도 있음.. 
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");//.allowedMethods("GET", "POST");
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/api-user/tokenLogin",
						"/api-user/id-duplicate", "/api-user/signup");
	}
}
	
	
