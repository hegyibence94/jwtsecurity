package com.greenfoxacademy.jwtsecurity.security;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

    @Bean
    public WebServerFactoryCustomizer customizer() {
      return container -> {
        if (container instanceof TomcatServletWebServerFactory) {
          TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) container;
          tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
        }
      };
    }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/signup").permitAll()
        .anyRequest().authenticated();
  }
}
