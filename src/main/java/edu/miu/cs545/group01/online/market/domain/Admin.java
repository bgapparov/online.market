package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.Role;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Admin extends User{
    public Admin() {
    }

    public Admin(String Email, String Password, Set<Role> roles, UserStatus status) {
        super(Email, Password, roles, status);
    }
}
