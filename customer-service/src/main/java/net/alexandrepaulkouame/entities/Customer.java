package net.alexandrepaulkouame.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Le nom complet est obligatoire.")
    @Size(min = 2, max = 100,message = "Le nom complet doit contenir entre 2 et 100 caractères.")
    @Column(length = 100)
    private String fullName;

    @NotNull(message = "Le numéro de téléphone ne peut pas être nul")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Le numéro de téléphone est invalide")
    @Column(length = 20)
    private String phoneNumber;

    private String password;

    @NotNull(message = "L'email ne peut pas être nul")
    @Email(message = "L'email doit être valide")
    @Column(length = 25)
    private String email;

    private boolean isActive;

    @Size(min = 2, max = 255,message = "L'adresse ne contenir que entre 2 et 255 caractères.")
    @Column(length = 255)
    private String adresse;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    private String resetToken;
    private Instant resetTokenExpiration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
    private Instant createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
