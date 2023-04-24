package bits.pilani.sem2.dda.assignment1.config;

import bits.pilani.sem2.dda.assignment1.support.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomUserDetailService userDetailsService;

    public static final String[] ENDPOINTS_WHITELIST = {
        "/css/**",
        "/js/**",
        "/images/**",
        "/",
        "/login",
        "/home",
        "/register",
        "/reset-password",
        "/reset-password/**",
        "/student-application/**"
    };
    
    public static final String[] POST_ENDPOINTS_WHITELIST = {
        "/student-application/**", 
        "/register/**",
        "/reset-password/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeHttpRequests((request) -> request
            .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
            .requestMatchers(HttpMethod.POST, POST_ENDPOINTS_WHITELIST).permitAll()
            .requestMatchers("/student/**").hasRole("STUDENT")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated())
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .failureUrl("/login?error=loginFailed")
            .defaultSuccessUrl("/home")
            .permitAll()
            .and().logout().logoutSuccessUrl("/").permitAll()
            .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .accessDeniedPage("/home?error=accessDenied");

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
      return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
