package demo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class BasicConfig1 extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http
         .authorizeRequests()
         .antMatchers("/", "index", "/css/*", "/js/*")
         .permitAll()
         .anyRequest()
         .authenticated()
         .and()
         .httpBasic();
    }
//Basically http.antMatcher() tells Spring to only configure HttpSecurity if the path matches this pattern.
}
