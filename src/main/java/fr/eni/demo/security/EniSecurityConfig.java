package fr.eni.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class EniSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //quelque soit la requête on doit s'authentifier
                        .requestMatchers(HttpMethod.GET, "/eniecole").permitAll()
                        .requestMatchers(HttpMethod.GET, "/eniecole/employes/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/eniecole/employes").hasRole("ADMIN")
                        .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        http.csrf(csrf-> csrf.disable());

        return http.build();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        //pour interagir avec notre bdd
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("SELECT pseudo, password, 1 FROM users WHERE pseudo = ?");
        //quels sont les rôles et habilitations de l'utilisateur
        users.setAuthoritiesByUsernameQuery("SELECT pseudo, authority FROM users WHERE pseudo = ?");
        return users;
    }

}
