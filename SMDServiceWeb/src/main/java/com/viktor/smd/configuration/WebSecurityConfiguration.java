package com.viktor.smd.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {

    http.csrf().disable().authorizeRequests()
        .antMatchers("/*").permitAll()
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config").permitAll()
        .antMatchers(HttpMethod.POST, "/demo/add").permitAll()
        .antMatchers(HttpMethod.GET, "/demo/all").permitAll()
        .anyRequest().authenticated();
  }
}
