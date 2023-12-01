package com.projetCertif.dao.entityDto;

import com.projetCertif.dao.entity.User;
import lombok.*;

@Data
@Builder
public class UserDto {

    private int id;
    private String prenom;
    private String nom;
    private String image;

        public static  UserDto fromEntity(User user) {
        if (user == null){
            //TODO throw an exception
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .prenom(user.getFirstname())
                .nom(user.getLastname())
                .image(user.getPicture())
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
