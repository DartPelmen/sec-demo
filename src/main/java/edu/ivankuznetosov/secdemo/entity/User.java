package edu.ivankuznetosov.secdemo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Entity(name = "accounts")
public class User implements UserDetails {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private boolean isExpired;
    @Column
    private boolean isCredentialExpired;
    @Column
    private boolean isLocked;
    @Column
    private boolean isEnabled;

    @ManyToMany
    @JoinTable(name = "account_roles",
            joinColumns =@JoinColumn(name = "roleId"),inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isEnabled = true;
        isExpired = false;
        isLocked = false;
        isCredentialExpired = false;
        id = new Random().nextLong();
    }

    public User() {
        id = 1L;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorites = roles.stream().map(role -> (GrantedAuthority) role::getRoleName).collect(Collectors.toCollection(ArrayList::new));
        return authorites;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public boolean isCredentialExpired() {
        return isCredentialExpired;
    }

    public void setCredentialExpired(boolean credentialExpired) {
        isCredentialExpired = credentialExpired;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
