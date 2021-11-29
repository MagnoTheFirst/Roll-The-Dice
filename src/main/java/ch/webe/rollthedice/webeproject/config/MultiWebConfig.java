package ch.webe.rollthedice.webeproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class MultiWebConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security.csrf().disable().authorizeRequests().anyRequest().permitAll();
        //security.cors().and().csrf().and().httpBasic().disable().authorizeRequests().anyRequest().permitAll();;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("http://localhost:8080",
                                "http://localhost:8081",
                                "http://localhost:8081/websocket",
                                "http://localhost:8081/*",
                                "http://localhost:8082",
                                "http://localhost:8083",
                                "http://localhost:8084",
                                "http://localhost:8084/account");
            }
        };
    }
}
