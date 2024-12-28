package com.example.demov2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demov2.models.User;
import com.example.demov2.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;


    public User saveUser(User user) {
        return repo.save(user);
    }

    public Optional<User> authenticate(String email, String password) {
        // Rechercher l'utilisateur uniquement par email
        Optional<User> user = Optional.ofNullable(repo.findByEmail(email).orElseThrow());

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user; // Mot de passe valide
        } else {
            return Optional.empty(); // Échec de l'authentification
        }
    }


  

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public void deleteUser(Long id) {
        repo.deleteById(id.intValue());
    }

}
