package com.codeup.helpinghand;

import com.codeup.helpinghand.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsLoader userDetailsLoader;

    public SecurityConfiguration(UserDetailsLoader userDetailsLoader) {
        this.userDetailsLoader = userDetailsLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.userDetailsService(userDetailsLoader).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                // DEFINE HOW TO LOGIN -> AND REDIRECTED AFTER LOGIN
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                // DEFINE HOW TO LOGOUT
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                // DEFINE VIEWS WHERE A USER DOESN'T NEED TO BE LOGGED IN
                .and()
                .authorizeRequests()
                .antMatchers("/", "/sign-up", "/aboutus", "/donations")
                .permitAll()
                // DEFINE VIEW WHERE A USER MUST BE LOGGED IN
                .and()
                .authorizeRequests()
                .antMatchers( "/userdashboard", "/requests", "/singlereq/{requestId}", "/reqform", "/donations", "/singledonation/{donationId}", "/donationform")
                .authenticated()
                // DEFINE ADMIN ONLY PAGES
                .and()
                .authorizeRequests()
                .antMatchers("/admindashboard", "/donations/{donationId}/delete", "/editdonation/{donationId}")
                .hasAuthority("ADMIN");
    }
}
