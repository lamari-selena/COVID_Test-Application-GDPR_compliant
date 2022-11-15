package covidalert.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(HttpSecurity security) throws Exception {
        // TODO : solve issuer url problem when switching to docker-compose
//        security.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        // Temporary solution to get a running environment
        security.authorizeRequests(authorize -> authorize.anyRequest().permitAll());
        security.csrf().disable();
    }


}
