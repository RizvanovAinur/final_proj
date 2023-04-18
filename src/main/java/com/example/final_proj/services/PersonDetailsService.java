package com.example.final_proj.services;

import com.example.final_proj.models.Person;
import com.example.final_proj.repository.PersonRepository;
import com.example.final_proj.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonDetailsService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Получаем пользователя из таблицы по логину с формы аутентификации
        Optional<Person> person = personRepository.findByLogin(username);
        //Если пользователя не найден
        if(person.isEmpty()) {
            //Выбрасываем исключение, что данный пользователь не найден
            //Данное исключение будет поймано SpringSecurity и сообщение
            //Будет выведено на страницу
            throw new UsernameNotFoundException("Пользователь не найден!");
        }
        return new PersonDetails(person.get());
    }
}
