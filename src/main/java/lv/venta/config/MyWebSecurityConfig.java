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

        http.authorizeHttpRequests(auth -> auth


                .requestMatchers("/h2-console/**").hasAuthority("ADMIN")
                .requestMatchers("/").permitAll()
                .requestMatchers("/h2-console").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/event/show/all").permitAll()
                .requestMatchers("/event/remove/{id}").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/event/add").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/event/update/*").hasAnyAuthority("ADMIN","USER")

                .requestMatchers("/article/show/all").permitAll()
                .requestMatchers("/article/remove/*").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/article/add/**").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/article/update/*").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/article/articleById/*").permitAll()

                .requestMatchers("/advertisement/show/all").permitAll()
                .requestMatchers("/advertisement/remove/*").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/advertisement/add").hasAuthority("ADMIN")
                .requestMatchers("/advertisement/update/*").hasAuthority("ADMIN")

                .requestMatchers("/editor/all").hasAuthority("ADMIN")
                .requestMatchers("/editor/remove/*").hasAuthority("ADMIN")
                .requestMatchers("/editor/add").hasAuthority("ADMIN")
                .requestMatchers("/editor/update/*").hasAuthority("ADMIN")

                .requestMatchers("/joke/all").permitAll()
                .requestMatchers("/joke/remove/**").hasAuthority("ADMIN")
                .requestMatchers("/joke/update/**").hasAuthority("ADMIN")
                .requestMatchers("/joke/add").hasAuthority("ADMIN")
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/error/**").permitAll()


                .requestMatchers("/h2-console/**").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/review/remove/*").permitAll()
                .requestMatchers("/review/add/**").permitAll()
                .requestMatchers("/review/update/**").permitAll()
                .requestMatchers("/error/**").permitAll()



                .requestMatchers("weather").permitAll()

        );

        http.formLogin(form -> form.permitAll());
        http.csrf(csrf-> csrf.disable());
        http.headers(frame-> frame.frameOptions(option->option.disable()));

        return http.build();

    }
}