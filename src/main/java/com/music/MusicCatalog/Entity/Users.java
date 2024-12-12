package com.music.MusicCatalog.Entity;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class Users {
     @Id
    private String id;

    @NotBlank(message = "L'identifiant est obligatoire")
    @Email(message = "Veuillez fournir une adresse e-mail valide")
    private String login;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    @NotNull(message = "Le statut actif est obligatoire")
    private Boolean active;

    @NotNull(message = "La collection des rôles ne peut pas être nulle")
    @Builder.Default
    private Collection<String> roles = new HashSet<>();

}
