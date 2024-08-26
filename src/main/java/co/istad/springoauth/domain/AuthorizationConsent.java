package co.istad.springoauth.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "authorizationConsents")
@IdClass(AuthorizationConsent.AuthorizationConsentId.class)
@Setter
@Getter
@NoArgsConstructor
public class AuthorizationConsent {
    @Id
    private String registeredClientId;
    @Id
    private String principalName;
    @Column(length = 1000)
    private String authorities;

    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class AuthorizationConsentId implements Serializable {
        private String registeredClientId;
        private String principalName;

    }
}

