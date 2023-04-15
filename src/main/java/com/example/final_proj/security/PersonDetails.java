package com.example.final_proj.security;

import com.example.final_proj.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    public Person getPerson(){
        return this.person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    //Возврат пароля пользователя конкретного
    @Override
    public String getPassword() {
        return this.person.getPassword();
    }
    // Возварщает логин конкретного пользователя
    @Override
    public String getUsername() {
        return this.person.getLogin();
    }
    // Поля ниже это ограничения, блокировка, активен/нет и т.п.
    // Аккаунт действителен
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // Заблокирован/нет
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // Пароль является действительным
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // Активен/Деактивирован
    @Override
    public boolean isEnabled() {
        return true;
    }
}
