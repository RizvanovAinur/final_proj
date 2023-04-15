package com.example.final_proj.config;

import com.example.final_proj.services.PersonDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    private final PersonDetailsService personDetailsService;
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Конфигурация работы Spring Security
        http.csrf().disable() //отключение защиты от межсайтовой подделки запросов
                .authorizeHttpRequests() // все страницы должны быть защищены аутентификацией
                .requestMatchers("/authentication", "/error").permitAll() //Всем пользователям доступны страницы атентификации и объект ошибки
                .anyRequest().authenticated() //Для любых других нужно аутентифицироваться
                .and() //Позоляет соединять разные компоненты в рамках конфигурации (настройка аутентификации-ниже, с настройкой доступа-выше)
                .formLogin().loginPage("/authentication") //При заходе на защещенные страницы надо направлять на нашу страницу аутентификации
                .loginProcessingUrl("/process_login") //При нажатии на кнопку при входе данные будут направлятся сюда (встроенная проверка SpringSecurity)
                .defaultSuccessUrl("/index", true) //На какую страницу направлется пользователь после успешной аутентификации (true-что бы
                //в любом случае после УСПЕШНОЙ аутентификации )
                .failureUrl("/authentication"); //Если не правильно то возрват на страницу аутентификации + на форму направляется объект error
                // который там и проверяется (на странице authentication (th:if))
        return http.build();
    }
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(personDetailsService);
    }
}
