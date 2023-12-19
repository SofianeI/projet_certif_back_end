package com.projetCertif.service;

import com.projetCertif.config.PasswordEncoderUtil;
import com.projetCertif.dao.entity.User;
import com.projetCertif.dao.entityDto.UserLoginRequest;
import com.projetCertif.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    //deprecated
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
        List<User> users = userRepository.findAllByUsername(username);

        if (users.isEmpty()) {
            return Optional.empty(); // Aucun utilisateur trouvé
        }

        Optional<User> optionalUser = Optional.ofNullable(users.get(0));

        /*if (password.equals(user.getLastname())) {
            return Optional.of(user);
        }*/
        if (optionalUser.isPresent()) {
            String hashedPassword = optionalUser.get().getPassword();
            boolean passwordMatch = PasswordEncoderUtil.matches(password, hashedPassword);

            if (passwordMatch) {
                return optionalUser;
            }
        }

        return Optional.empty();
    }

    public User registerUser(String username, String firstname, String lastname, String hashedPassword) {
        User user = new User();
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public void uploadImageProfile(Long id, MultipartFile image) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));;
        user.setPicture(image.getBytes());
        userRepository.save(user);
    }
    public User login(UserLoginRequest userLoginRequest)  {
        return userRepository.findbyUsernameAndPassword(userLoginRequest.getUsername(), userLoginRequest.getPassword());
    }
}
