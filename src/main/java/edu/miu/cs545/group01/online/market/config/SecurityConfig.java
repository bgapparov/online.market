package edu.miu.cs545.group01.online.market.config;

import edu.miu.cs545.group01.online.market.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
            http
//                    .httpBasic()
//                    .and()
//                    .csrf().disable()
    //                .anonymous()
                    .authorizeRequests()

                    .antMatchers("/**").permitAll()// access=none
                    .antMatchers("/resources/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=1")
                    .loginProcessingUrl("/login")
                    .permitAll()
//                    .loginProcessingUrl("/login")
//                    .defaultSuccessUrl("/", true)
//                    .failureUrl("/login?error=true");
    //                .and()
    //                .logout()
    //                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    //                .logoutSuccessUrl("/login")
    //                .logoutUrl("/logout")
    //                .deleteCookies("JSESSIONID");
        ;
//            http
//                    .sessionManagement()
//                    .maximumSessions(5)
//                    .maxSessionsPreventsLogin(true)
//                    .and()
//                    .sessionFixation();
    }



//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.builder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

}
