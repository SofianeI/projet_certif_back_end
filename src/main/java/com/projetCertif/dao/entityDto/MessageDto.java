package com.projetCertif.dao.entityDto;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.entity.User;
import lombok.*;
import java.time.LocalDateTime;



@Data
@Builder
public class MessageDto {

    private int id;
    private String contenu;
    private User utilisateur;
    private Channel canal;
    private LocalDateTime date;

}