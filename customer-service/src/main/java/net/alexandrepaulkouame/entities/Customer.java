package net.alexandrepaulkouame.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
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
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères.")
    @Column(length = 255)
    private String firstName;

    @NotNull(message = "Le prénom complet est obligatoire.")
    @Size(min = 2, max = 100, message = "Le prénom doit contenir entre 2 et 100 caractères.")
    @Column(length = 255)
    private String lastName;

    @NotNull(message = "Le numéro de téléphone ne peut pas être nul")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Le numéro de téléphone est invalide")
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, max = 8, message = "Le mot de passe doit contenir 8 caractères.")
    private String password;

    @NotNull(message = "L'email ne peut pas être nul")
    @Email(message = "L'email doit être valide")
    private String email;

    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonSerialize(using = net.alexandrepaulkouame.utils.RoleSetToStringListSerializer.class)
    @JsonDeserialize(using = net.alexandrepaulkouame.utils.StringListToRoleSetDeserializer.class)
    private Set<Role> roles = new HashSet<>();

    private String resetToken;

    private Instant resetTokenExpiration;

    private List<String> documents;

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
