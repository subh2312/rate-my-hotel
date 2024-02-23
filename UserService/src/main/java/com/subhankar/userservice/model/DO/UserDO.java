package com.subhankar.userservice.model.DO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subhankar.userservice.integration.model.AddressDO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    @JsonIgnore
    private String salt;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Transient
    private List<AddressDO> addresses=new ArrayList<>();

    @JsonIgnore
    @Transient
    @Value("${application.pepper}")
    private String pepper;

    @PrePersist
    private void hashPassword() {
        // hash the password
        String genSalt= BCrypt.gensalt();
        setSalt(genSalt);
        String hashedPassword= BCrypt.hashpw(this.password+pepper, this.salt);
        setPassword(hashedPassword);
    }
}
