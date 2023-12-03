package com.projetCertif.service;

import com.projetCertif.dao.entity.User;
import com.projetCertif.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> authenticate(String username, String password) {
        List<User> users = userRepository.findAllByFirstname(username);

        if (users.isEmpty()) {
            return Optional.empty(); // Aucun utilisateur trouvé
        }

        User user = users.get(0);

        if (password.equals(user.getLastname())) {
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
