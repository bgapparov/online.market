package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.Role;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Seller extends User {

    public Seller() {
    }

    public Seller(String Email, String Password, UserStatus status) {
        super(Email, Password, new HashSet<>(Arrays.asList(Role.SELLER)), status);
    }

}
