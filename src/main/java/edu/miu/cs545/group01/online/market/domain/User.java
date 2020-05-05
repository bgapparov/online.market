package edu.miu.cs545.group01.online.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.cs545.group01.online.market.domain.enums.Role;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue
    private long Id;

    @Column(unique = true)
    @NotBlank(message = "this field is required")
    @Email
    private String Email;

    @JsonIgnore
    private String Password;

    @JsonIgnore
    @ElementCollection
//    @CollectionTable(name = "userRole", joinColumns = @JoinColumn(name = "personId"))
//    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(min = 1)
    private Set<Role> roles;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Size(min = 1)
    private Set<UserStatus> status;

    public User() {
    }

    public User(String Email, String Password, Set<Role> roles, Set<UserStatus> status) {
        super();
        this.Email = Email;
        this.Password = Password;
        this.roles = roles;
        this.status = status;
    }

    public long getId() {
        return Id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<UserStatus> getStatus() {
        return status;
    }

    public void setStatus(Set<UserStatus> status) {
        this.status = status;
    }
}
