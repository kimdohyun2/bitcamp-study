package bitcamp.myapp.config.security01;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    public SecurityConfig() {
        System.out.println("SecurityConfig 생성자 호출!");

    }
}
