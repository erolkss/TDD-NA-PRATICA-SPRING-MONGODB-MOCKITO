package br.com.ero.register.services;

import br.com.ero.register.entity.User;
import br.com.ero.register.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        var age = Period.between(user.getDataOfBirth(), LocalDate.now()).getYears();

        if (age >= 18) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Idade não permitida.");
        }
    }
}
