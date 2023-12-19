package ru.naumen.taskManager.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.naumen.taskManager.services.UserService;
import ru.naumen.taskManager.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

/*
    @Autowired
    UserServiceImpl userServiceImpl;
*/


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/registration").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/*.css").permitAll()
                .requestMatchers("/dashboard").permitAll()
                .anyRequest().authenticated()

        ).formLogin(form ->form
                .loginPage("/login").defaultSuccessUrl("/dashboard",true).permitAll()
                 )
                .logout((logout) -> logout.logoutSuccessUrl("/login?logout"));
        return http.build();
    }
//TODO поменять роль @HASROLE ROLE
    /*
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserServiceImpl).passwordEncoder(bCryptPasswordEncoder());

    }
    */

}
