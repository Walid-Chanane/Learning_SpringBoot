package com.luv2code.springboot.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
*/
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    //sol 3: use custom tables (sol 1 at the end of the code)
    //we have to define the queries to get the members and the roles from the database
    
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select user_id, pw, active from members where user_id=?");

        //define query to retrieve the role of the user
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select user_id, role from roles where user_id=?");
        
        return jdbcUserDetailsManager;
    }
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
            configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
            );

            //use HTTP basic authentication
            http.httpBasic(Customizer.withDefaults());

            //disable cross site request forgery (CSRF)
            //in general not required for stateless REST APIs that use POST, PUT, DELETE
			//mine: it is required for post, put, delete
            http.csrf(csrf -> csrf.disable());

            return http.build();
    }





    /*sol 2: the users in the database using spring security default tables (autorities and users)
    //add support for JDBC .. no more hardcoded users
    
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    } */

    
    /*sol 1: the users in the program 
    to put users in the code,but we will put the users in the database instead wich is more usefull
     and simple the code will also be given by spring boot so ..
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails john = User.builder()
            .username("john")
            .password("{noop}johncina")
            .roles("EMPLOYEE")
            .build();

        UserDetails mary = User.builder()
            .username("mary")
            .password("{noop}mary")
            .roles("EMPLOYEE", "MANAGER")
            .build();

        UserDetails suzan = User.builder()
            .username("suzan")
            .password("{noop}suzan")
            .roles("EMPLOYEE", "MANAGER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(john, mary, suzan);
    }
    */

    
}
