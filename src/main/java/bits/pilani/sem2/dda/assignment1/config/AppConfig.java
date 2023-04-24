package bits.pilani.sem2.dda.assignment1.config;

import bits.pilani.sem2.dda.assignment1.support.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 *
 * @author yogeshk
 */
@Configuration
@EnableJpaRepositories(basePackages = {"bits.pilani.sem2.dda.assignment1"})
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public AccessDeniedHandler configAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
