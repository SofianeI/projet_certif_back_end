package com.projetCertif.dao.entityDto;

import com.projetCertif.dao.entity.User;
import lombok.*;

import java.util.Optional;

@Data
@Builder
public class UserDto {

    private int id;
    private String prenom;
    private String nom;
    private String image;


    // FUNCTION WITH AN OPTIONAL PARAMETER OBJECT USER
    public static  UserDto fromEntity(Optional<User> user) {
        if (user == null){
            //TODO throw an exception
            return null;
        }
        return UserDto.builder()
                .id(user.get().getId())
                .prenom(user.get().getFirstname())
                .nom(user.get().getLastname())
                .image(user.get().getPicture())
                .build();
    }

    public static User toEntity(UserDto userDto){
        if(userDto == null){
            //TODO throw an exception
            return  null;
        }
        User user= new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getPrenom());
        user.setLastname(userDto.getNom());
        user.setPicture(userDto.getImage());
        return user;

    }



}
