package com.projetCertif.dao.entityDto;

import lombok.*;

@Data
@Builder
public class UserDto {

    private int id;
    private String prenom;
    private String nom;
    private String image;


}
