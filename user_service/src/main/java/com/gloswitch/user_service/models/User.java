package com.gloswitch.user_service.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "mobile"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "referralcode")
})
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String mobile;
    private String password;
    private String referralCode;
    private Integer parentsid;
    private String image;
    private String firstname;

    private Integer pin;
    private String lastname;

    private java.sql.Date dateofbirth;
    private String passport;
    private String drivinglicence;
    private String nationalid;

    private String remark;
    private String country;
    private String kycstatus;

    private Boolean privacymode;
    private Boolean twofactorvarifications;

    @Column(columnDefinition = "Boolean default false")
    private Boolean preference;

    @Column(columnDefinition = "Boolean default false")
    private Boolean accountstatus;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Date createdAt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Date modifiedAt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Date deletedAt;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;


    public User(String email, String mobile, String password, String firstname, String country) {
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.firstname = firstname;
        this.country = country;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.role.getLibelle()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountstatus;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountstatus;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountstatus;
    }

    @Override
    public boolean isEnabled() {
        return this.accountstatus;
    }
}
