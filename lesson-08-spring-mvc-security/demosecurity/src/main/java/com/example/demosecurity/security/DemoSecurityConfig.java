package com.example.demosecurity.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    //add support for JDBC ... no more hardcoded users

    //here we will use a custom data base with 2 table (members, roles)
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve user by username 
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select user_id, pw, active from members where user_id=?");

        //define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select user_id,role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    /* for default data base (users, authorities)
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        return new JdbcUserDetailsManager(dataSource);
    }
    */


    /* we will put these information in the data base
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails john = User.builder()
                           .username("john")
                           .password("{noop}test123")
                           .roles("EMPLOYEE")
                           .build();

        UserDetails mary = User.builder()
                           .username("mary")
                           .password("{noop}test123")
                           .roles("EMPLOYEE", "MANAGER")
                           .build();
    
        UserDetails susan = User.builder()
                            .username("susan")
                            .password("{noop}test123")
                            .roles("EMPLOYEE", "MANAGER", "ADMIN")
                            .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer->
                        configurer 
                            .requestMatchers("/").hasRole("EMPLOYEE")
                            .requestMatchers("/leaders/**").hasRole("MANAGER")//the /** means all sub paths
                            .requestMatchers("systems/**").hasRole("ADMIN")//the /** means all sub paths
                            .anyRequest().authenticated()
        )
            .formLogin(form->
                    form
                            .loginPage("/showMyLoginPage")
                            .loginProcessingUrl("/authenticateTheUser") //no controller request mapping
                            // required for this,we get it for free from spring security
                            .permitAll()
            // )
            // .logout(logout -> 
            //         logout.permitAll()
            // )
            // .exceptionHandling(configurer ->
            //                     configurer.accessDeniedPage("/access-denied")
            );

            return http.build();
    }
}
