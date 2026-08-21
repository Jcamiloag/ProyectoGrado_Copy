package com.farfala.backend.User;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.farfala.backend.Plan.Plan;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
    }
)
@JsonIgnoreProperties({
    "authorities",
    "password",
    "accountNonExpired",
    "accountNonLocked",
    "credentialsNonExpired",
    "enabled"
})
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private String username;



    @Column(nullable = false)
    private String lastname;



    private String firstname;



    @Column(nullable = false, unique = true)
    private String email;



    @Column(nullable = false)
    private String password;



    private String phonenumber;



    @Enumerated(EnumType.STRING)
    private Role role;




    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(
                new SimpleGrantedAuthority(role.name())
        );

    }




    @Override
    @JsonIgnore
    public String getPassword() {

        return password;

    }




    @Override
    public String getUsername() {

        return email;

    }




    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {

        return true;

    }




    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {

        return true;

    }




    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {

        return true;

    }




    @Override
    @JsonIgnore
    public boolean isEnabled() {

        return true;

    }





    @JsonIgnore
    @ToString.Exclude
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<Plan> plans;


}