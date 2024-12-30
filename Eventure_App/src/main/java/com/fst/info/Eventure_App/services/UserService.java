package com.fst.info.Eventure_App.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fst.info.Eventure_App.models.User;
import com.fst.info.Eventure_App.repositories.UserRepository;

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
        repo.deleteById(id);
    }

    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

}
