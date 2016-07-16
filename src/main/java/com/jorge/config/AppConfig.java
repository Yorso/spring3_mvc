/**
 * This is a configuration class
 * 
 */

package com.jorge.config;

import java.util.Locale;

//import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
//import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.i18n.CookieLocaleResolver;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration // This declares it as a Spring configuration class
@EnableWebMvc // This enables Spring's ability to receive and process web
				// requests. Necessary for interceptors too.
@ComponentScan(basePackages = { "com.jorge.controller" }) // This scans the
															// com.jorge.controller
															// package for
															// Spring components
// @Import({ DatabaseConfig.class, SecurityConfig.class }) //If you are using a
// Spring application without a 'ServletInitializer' class,
// you can include other configuration classes from your primary configuration
// class

public class AppConfig extends WebMvcConfigurerAdapter { // Extend from
															// WebMvcConfigurerAdapter
															// is necessary for
															// interceptors

	/*
	 * If we aren't going to use Tiles, uncomment jspViewResolver() method and
	 * comment tilesConfigurer() and tilesViewResolver() methods
	 * 
	 * @Bean public ViewResolver jspViewResolver(){ InternalResourceViewResolver
	 * resolver = new InternalResourceViewResolver();
	 * resolver.setViewClass(JstlView.class);
	 * resolver.setPrefix("/WEB-INF/jsp/"); // These folders must be created on
	 * /src/main/webapp/ resolver.setSuffix(".jsp"); return resolver; }
	 */

	/**
	 * TILES
	 */
	// Declare Tiles configuration file
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		final String[] definitions = { "/WEB-INF/tiles.xml" };
		tilesConfigurer.setDefinitions(definitions);
		return tilesConfigurer;
	}

	// Declare Tiles as a view resolver
	@Bean
	public ViewResolver tilesViewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		return resolver;
	}

	/**
	 * INTERCEPTORS
	 * 
	 * The interceptor methods are executed at the corresponding moments of the
	 * request workflow
	 */
	// Declaring the interceptors as a bean (it's an example)
	@Bean
	public HandlerInterceptor performanceInterceptor() {
		PerformanceInterceptor interceptor;
		interceptor = new PerformanceInterceptor();
		return interceptor;
	}

	// Define a LocaleChangeInterceptor interceptor to allow the current language to be changed	with a lang URL parameter
	@Bean
	public HandlerInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}
	
	// Registering interceptors
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(performanceInterceptor()); // Example
		registry.addInterceptor(localeChangeInterceptor()); // Registering interceptor to allow the current language to be changed	with a lang URL parameter
		// registry.addInterceptor(performanceInterceptor()).addPathPatterns("/home", "/user/*");// To restrict the interceptor to specific URLs, add path
														   									     // patterns to the interceptor registration.
														   										 // In this example, the interceptor methods will be executed for /home ,
																								 // /user/list , and /user/add but not for /contact 
	}

	/**
	 * INTERNATIONALIZATION AND PROPERTIES
	 *
	 * @Bean(name ="messageSource") The bean name of message source must be
	 *            messageSource public MessageSource anyName() { .... }
	 * 
	 *            Or keep the method name as messageSource(), by default bean
	 *            name will be derived by method name (it is our example)
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages");
		//messageSource.setBasename("/i18n/messages"); // How to set another path to store properties files
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	// Store the user language selection in a cookie and declare the default language
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("en"));
		return localeResolver;
	}
	
	/*
	 * @Bean 
	 * public LocaleResolver localeResolver(){ CookieLocaleResolver
	 * 		resolver = new CookieLocaleResolver(); 
	 * 		resolver.setDefaultLocale(new Locale("en")); 
	 *      resolver.setCookieName("myLocaleCookie");
	 * 		resolver.setCookieMaxAge(4800); 
	 * 		return resolver; 
	 * } 
	 * 
	 * @Override
	 * public void addInterceptors(InterceptorRegistry registry) { 
	 * 		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	 * 		interceptor.setParamName("mylocale");
	 * 		registry.addInterceptor(interceptor); 
	 * }
	 */

}