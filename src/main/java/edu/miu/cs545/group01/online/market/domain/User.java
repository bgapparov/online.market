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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "this field is required")
    @Email
    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"))
//    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(min = 1)
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @NotBlank
    private String name;

    public User() {
    }

    public User(String name, String Email, String Password, Set<Role> roles, UserStatus status) {
        super();
        this.name = name;
        this.email = Email;
        this.password = Password;
        this.roles = roles;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
