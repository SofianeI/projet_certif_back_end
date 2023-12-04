package com.projetCertif.controller;

import com.projetCertif.dao.entity.User;
import com.projetCertif.dao.entityDto.UserDto;
import com.projetCertif.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserControllerDto {

    @Autowired
    private UserService userService;

    //GET ALL USERS
    @GetMapping("users")
    public List<UserDto> getAllUsers() throws Exception {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users) {
            userDtos.add(UserDto.fromEntity(Optional.ofNullable(user)));
        }
        return userDtos;
    }

    //GET USER BY ID
    @GetMapping("users/{id}")
    public Object getUserById(@PathVariable Long id) throws Exception {
        Optional<User> user = userService.getUserById(id);
        UserDto userDto = UserDto.fromEntity(user);
        if(userDto == null) {
            return  ResponseEntity.notFound().build();
        } else {
            return userDto;
        }
    }

    //CREATE NEW USER
    @PostMapping("users")
    public void addUser(@RequestBody UserDto userdto) throws Exception {
        userService.addUser(UserDto.toEntity(userdto));
    }

    //DELETE USER
    @DeleteMapping("users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if(userService.getUserById(id).isEmpty() == true)
            return ResponseEntity.notFound().build();
        else {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }

    }

    //UPDATE USER
    @PutMapping("users")
    public ResponseEntity update(@RequestBody UserDto userDto) throws Exception {
        User userUpdated = userService.updateUser(UserDto.toEntity(userDto));
        if(userUpdated !=null)
            return ResponseEntity.status(200).build();
        else
            return ResponseEntity.status(403).build();

    }

}
