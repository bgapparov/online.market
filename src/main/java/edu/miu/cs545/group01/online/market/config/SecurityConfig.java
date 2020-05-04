package edu.miu.cs545.group01.online.market.config;

import edu.miu.cs545.group01.online.market.domain.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
            http.httpBasic()
                    .and()
                    .csrf().disable()
    //                .anonymous()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/sections/**").hasRole(Role.ADMIN.toString())
                    .antMatchers(HttpMethod.PUT, "/sections/**").hasRole(Role.ADMIN.toString())
                    .antMatchers(HttpMethod.DELETE, "/sections/**").hasRole(Role.ADMIN.toString())
                    .antMatchers(HttpMethod.GET, "/sections/**").hasAnyRole(Role.ADMIN.toString(), Role.SELLER.toString(), Role.BUYER.toString())

                    .antMatchers("/**").anonymous()// access=none
                    .antMatchers("/login*").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
//                    .loginProcessingUrl("/login")
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error=true");
    //                .and()
    //                .logout()
    //                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    //                .logoutSuccessUrl("/login")
    //                .logoutUrl("/logout")
    //                .deleteCookies("JSESSIONID");
            http
                    .sessionManagement()
                    .maximumSessions(3)
                    .maxSessionsPreventsLogin(true)
                    .and()
                    .sessionFixation();
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
