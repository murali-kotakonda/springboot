package demo.config;


import static demo.config.util.ApplicationUserRole.ADMIN;
import static demo.config.util.ApplicationUserRole.CUSTOMER;
import static demo.config.util.ApplicationUserRole.AGENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class BasicWithRoleBasedPermissiomBasedConfig4 extends WebSecurityConfigurerAdapter {

    private static final String CATALOG_WRITE = "catalog:write";
    private static final String CATALOG_READ = "catalog:read";
	private final PasswordEncoder passwordEncoder;

    @Autowired
    public BasicWithRoleBasedPermissiomBasedConfig4(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http
         .csrf().disable() 
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
               
                .antMatchers("/v1/products/**").hasAnyRole(ADMIN.name()) //CODE FOR role based authentication 
                
              .antMatchers(HttpMethod.DELETE, "/v1/catalog/**").hasAuthority(CATALOG_WRITE)
              .antMatchers(HttpMethod.POST, "/v1/catalog/**").hasAuthority(CATALOG_WRITE)
              .antMatchers(HttpMethod.PUT, "/v1/catalog//**").hasAuthority(CATALOG_WRITE)
              .antMatchers(HttpMethod.GET,"/v1/catalog/**").hasAuthority(CATALOG_READ)
              
              //.antMatchers(HttpMethod.GET,"/v1/catalog/**").hasAnyRole(ADMIN.name() ,AGENT.name() )
                
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("password123"))
                //.roles(ADMIN.name()) 
                .authorities("ROLE_"+ADMIN.name())
                .build();

        UserDetails user2 = User.builder()
                .username("user2")
                .password(passwordEncoder.encode("password123"))
               // .roles(AGENT.name())  
                .authorities(CATALOG_READ,CATALOG_WRITE)
                .build();

        UserDetails user3 = User.builder()
                .username("user3")
                .password(passwordEncoder.encode("password123"))
                //.roles(CUSTOMER.name()) 
                .authorities(CATALOG_READ)
                .build();

        return new InMemoryUserDetailsManager(
                user1,
                user2,
                user3
        );

    }
}
