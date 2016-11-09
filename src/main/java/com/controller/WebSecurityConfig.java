package com.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http
        	.csrf().disable()
            .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/").access("hasRole('USER')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }

    @Autowired
    private DataSource dataSource;
    	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.jdbcAuthentication()
        		.dataSource(dataSource)
        		.passwordEncoder(passwordEncoder())
        		.usersByUsernameQuery("select kayttajatunnus, salasana, enabled from Kayttajat where kayttajatunnus=? and enabled = 1")
        		.authoritiesByUsernameQuery("select k.kayttajatunnus, a.role from Kayttajat k JOIN Kayttooikeudet ko ON (k.id = ko.Kayttajat_id) JOIN authority a ON (a.id = ko.authority_id) WHERE k.kayttajatunnus = ?");

    }
    
    @Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
    
}
