package com.company.readnovel.model;

import com.company.readnovel.model.dto.UserRegistrationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(unique = true, nullable = false)
    @Size(min = 5, message = "Username must have at least 5 characters")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+", message = "Username must contain 1 uppercase, 1 lowercase and 1 number")
    public String username;
    public String password;
    @Email
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Email cannot be empty")
    public String email;
    @NotEmpty(message = "Roles cannot be empty")
    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roles = new HashSet<>();

    @OneToMany
    public List<Novel> novelList;

    public User() {

    }

    public User(UserRegistrationDTO userRegistrationDTO) {
        this.username = userRegistrationDTO.username;
        this.password = userRegistrationDTO.password;
        this.email = userRegistrationDTO.email;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.users.add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.users.remove(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var userRoles = this.roles;
        var authorities = new HashSet<GrantedAuthority>();

        for (var userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.name));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
