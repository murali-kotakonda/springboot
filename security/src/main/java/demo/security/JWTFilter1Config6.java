package demo.security;

import static demo.config.util.ApplicationUserRole.ADMIN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import jwt.JwtTokenGeneratorFilter;
import jwt.JwtTokenVerifier;

/**
Requirement:
-----------------
Allow /v1/products/** only for user1:
/v1/products/** CALLS CAN BE DONE ONLY by user1 for others it throws 403.
user1 cannot access    /v1/catalog/** API calls
 user2 can access GET,POST,PUT DELETE FOR /v1/catalog/** API calls
 user3 can access GET /v1/catalog/** API calls
 
 */
//@Configuration
//@EnableWebSecurity
public class JWTFilter1Config6 extends WebSecurityConfigurerAdapter {

    private static final String CATALOG_WRITE = "catalog:write";
    private static final String CATALOG_READ = "catalog:read";
    
	private final PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationEntryPoint entryPoint;
	
	//@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
    @Autowired
    public JWTFilter1Config6(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	  http
          .csrf().disable()
          .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
          .addFilterAfter(new JwtTokenVerifier(), JwtTokenGeneratorFilter.class)
          .authorizeRequests()
          
          // .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
          //.antMatchers("/product/**").hasRole("admin")
          
          .antMatchers("/authenticate").permitAll() //for jwt token generation
         
          .antMatchers("/v1/products/**").hasAnyRole(ADMIN.name()) //CODE FOR role based authentication 
          
          .antMatchers(HttpMethod.DELETE, "/v1/catalog/**").hasAuthority(CATALOG_WRITE)
          .antMatchers(HttpMethod.POST, "/v1/catalog/**").hasAuthority(CATALOG_WRITE)
          .antMatchers(HttpMethod.PUT, "/v1/catalog//**").hasAuthority(CATALOG_WRITE)
          .antMatchers(HttpMethod.GET,"/v1/catalog/**").hasAuthority(CATALOG_READ)
         
          .anyRequest()
          .authenticated()
          
          .and().exceptionHandling().authenticationEntryPoint(entryPoint)//optional to use this line ,invoked when token is incorrect
          ;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
    	 PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
     	
        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("password123"))
                //.roles(ADMIN.name()) 
                .authorities("ROLE_"+ADMIN.name()) //add as ROLE_ADMIN
                .build();

        UserDetails user2 = User.builder()
                .username("user2")
                .password(passwordEncoder.encode("password123"))
               // .roles(AGENT.name())  
                .authorities(CATALOG_READ,CATALOG_WRITE) //add as ROLE_AGENT
                .build();
 
        UserDetails user3 = User.builder()
                .username("user3")
                .password(passwordEncoder.encode("password123"))
                //.roles(CUSTOMER.name()) 
                .authorities(CATALOG_READ) //add as ROLE_CUSTOMER
                .build();

        return new InMemoryUserDetailsManager(
                user1,
                user2,
                user3
        );
    }
}