package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.Role;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Buyer extends  User{

    public Buyer() {
    }

    public Buyer(String Email, String Password, Set<Role> roles, Set<UserStatus> status) {
        super(Email, Password, roles, status);
    }
}
