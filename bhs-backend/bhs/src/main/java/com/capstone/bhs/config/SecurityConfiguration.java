package com.capstone.bhs.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CorsFilter;

import com.capstone.bhs.common.CommonConstant;

@EnableWebSecurity
@EnableConfigurationProperties
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final CorsFilter corsFilter;
	private UserDetailsService userDetailsService;
	private JwtRequestFilter jwtReqeustFilter;

	public SecurityConfiguration(CorsFilter corsFilter, UserDetailsService userDetailsService, JwtRequestFilter jwtReqeustFilter) {
		this.corsFilter = corsFilter;
		this.userDetailsService = userDetailsService;
		this.jwtReqeustFilter = jwtReqeustFilter;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Bean // -- Password Encoder Configuration
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
			.antMatchers(HttpMethod.OPTIONS, "/**")
			.antMatchers("/app/**/*.{js,html}")
			.antMatchers("/i18n/**")
			.antMatchers("/content/**")
			.antMatchers("/test/**")
			.antMatchers("/swagger-ui/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
		.csrf()
			.disable()
			.addFilterBefore(corsFilter, CsrfFilter.class).exceptionHandling()
		.and()
			.logout()
			.logoutUrl("/api/logout").permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/api/authenticate").permitAll()
			.antMatchers("/api/register").permitAll()
			.antMatchers("/api/ad-user/activate").permitAll()
			.antMatchers("/api/ad-user/reset-password/init").permitAll()
			.antMatchers("/api/ad-user/reset-password/finish").permitAll()
			.antMatchers("/api/get-object-home-page").permitAll()
			.antMatchers("/api/view-option-at-home-page").permitAll()
			.antMatchers("/api/get-all-list-categories").permitAll()
			.antMatchers("/api/get-images-by-option-id/{id}").permitAll()
			.antMatchers("/api/get-detail-option-by-id/{id}").permitAll()
			.antMatchers("/swagger-ui/**").authenticated()
			

//			.antMatchers("/api/admin/**").hasRole(CommonConstant.Authorities.ROLE_ADMIN)

			.antMatchers("/api/admin/**").hasAuthority(CommonConstant.Authorities.ROLE_ADMIN)
//			.antMatchers("/api/**").authenticated()
			.antMatchers("/api/**").permitAll()
//			.antMatchers("/api/get-object-home-page").permitAll()
//			.antMatchers("/api/view-option-at-home-page").permitAll()
//			.antMatchers("/api/get-all-list-categories").permitAll()
		.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtReqeustFilter, UsernamePasswordAuthenticationFilter.class);
		// @formatter:on
	}
}
