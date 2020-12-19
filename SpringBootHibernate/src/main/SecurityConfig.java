package main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();
    }
  
    /**
     * @param auth
     * @throws Exception
     you can also simply prefix {noop} to your passwords in order for the DelegatingPasswordEncoder use the NoOpPasswordEncoder to validate these passwords. Notice that NoOpPasswordEncoder is deprecated though, 
     as it is not a good practice to store passwords in plain text.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("{noop}password")
            .roles("USER");
    }
}


abstract class xyz{
	
	public abstract void display();
	
	public void show() {
		
		
	}
	
}