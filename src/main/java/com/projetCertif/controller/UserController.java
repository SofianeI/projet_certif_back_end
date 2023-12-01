package com.projetCertif.controller;

import com.projetCertif.dao.entity.User;
import com.projetCertif.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    //GET ALL USERS
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //GET USER BY ID
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    //POST NEW USER
    @PostMapping("users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User addedUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
    }

    //DELETE USER
    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //UPDATE USER
    @PutMapping("users")
    public ResponseEntity update(@RequestBody User user) {

        User userUpdate =  userService.updateUser(user);
        if(userUpdate !=null)
            return ResponseEntity.status(204).body("User successfully updated");
        else
            return ResponseEntity.status(403).build();

    }

}
