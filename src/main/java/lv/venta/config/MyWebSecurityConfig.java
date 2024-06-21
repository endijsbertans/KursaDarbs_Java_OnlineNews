package lv.venta.config;

import lv.venta.service.impl.MyUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig {
    @Bean
    public MyUserDetailsServiceImpl userDetailsManager() {
        return new MyUserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider linkWithDB() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsManager());
        provider.setPasswordEncoder(encoder);


        return provider;
    }



    @Bean
    public SecurityFilterChain configureEndpoints(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").hasAuthority("ADMIN")
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/h2-console").permitAll()
                        .requestMatchers("/event/show/all").permitAll()
                        .requestMatchers("/event/remove/{id}").permitAll()//tikai adminam
                        .requestMatchers("/event/add").permitAll()
                        .requestMatchers("/event/update/*").permitAll()
                        .requestMatchers("/article/show/all").permitAll()
                        .requestMatchers("/article/remove/*").permitAll()
                        .requestMatchers("/article/add/**").permitAll()
                        .requestMatchers("/article/update/*").permitAll()
                        .requestMatchers("/article/articleById/*").permitAll()
                        .requestMatchers("/advertisement/show/all").permitAll()
                        .requestMatchers("/advertisement/remove/*").permitAll()
                        .requestMatchers("/advertisement/add").permitAll()
                        .requestMatchers("/advertisement/update/*").permitAll()
                        .requestMatchers("/review/remove/*").permitAll()
                        .requestMatchers("/review/add/**").permitAll()
                        .requestMatchers("/review/update/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                );

        http.formLogin(form -> form.permitAll());
        http.csrf(csrf-> csrf.disable());
        http.headers(frame-> frame.frameOptions(option->option.disable()));

        return http.build();

    }
}
